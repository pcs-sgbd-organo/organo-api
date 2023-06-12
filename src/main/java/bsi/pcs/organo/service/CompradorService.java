package bsi.pcs.organo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.model.Comprador;
import bsi.pcs.organo.model.Endereco;
import bsi.pcs.organo.model.Pedido;
import bsi.pcs.organo.repository.CompradorRepository;
import bsi.pcs.organo.repository.EnderecoRepository;
import bsi.pcs.organo.repository.PedidoRepository;

@Service
public class CompradorService {

	@Autowired
	private CompradorRepository compradorRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
		
	public void cadastrar(Comprador comprador) {
		this.compradorRepository.save(comprador);
		
		if(!comprador.getEnderecos().isEmpty()) {
			for(Endereco endereco : comprador.getEnderecos()) {
				endereco.setComprador(comprador);
				this.enderecoRepository.save(endereco);
			}
		}
	}
	
	@Cacheable(value = "comprador", key="#cpf", unless = "#result == null")
	public Comprador retornar(String cpf) {
		Comprador ce = this.compradorRepository.findByCpf(cpf);
		return ce;
	}
	
	@CachePut(value = "comprador", key = "#cpf")
	public void atualizar(Comprador comprador) {
		Comprador compradorEncontrado = this.compradorRepository.findByCpf(comprador.getCpf());
		compradorEncontrado.setEmail(comprador.getEmail());
		compradorEncontrado.setNome(comprador.getNome());
		compradorEncontrado.setSobrenome(comprador.getSobrenome());
		compradorEncontrado.setSenha(comprador.getSenha());
		this.compradorRepository.save(compradorEncontrado);
		
		if(!comprador.getEnderecos().isEmpty()) {
			for(Endereco ee : comprador.getEnderecos()) {
				Endereco enderecoEncontrado = this.enderecoRepository.findByCepRuaNumeroComplementoCompradorId(ee.getCep(), ee.getRua(), ee.getNumero(), ee.getComplemento(), compradorEncontrado.getId());
				if(enderecoEncontrado == null) {
					ee.setComprador(compradorEncontrado);
					this.enderecoRepository.save(ee);
					this.enderecoRepository.delete(compradorEncontrado.getEnderecos().get(0));
				}
			}
		}
	}
	
	public Comprador autenticar(Comprador comprador) {
		Comprador compradorEncontrado = this.compradorRepository.getByEmail(comprador.getEmail());
		if(comprador.getSenha().equals(compradorEncontrado.getSenha())) return compradorEncontrado;
		return null;
	}

	public List<Pedido> listarPedidos(String cpfComprador) {
		List<Pedido> pedidosEncontrados = this.pedidoRepository.findByCompradorCpf(cpfComprador);
		for(Pedido pedido : pedidosEncontrados) {
			pedido.setCompradorAssociado(null);
		}
		
		return pedidosEncontrados;
	}
}
