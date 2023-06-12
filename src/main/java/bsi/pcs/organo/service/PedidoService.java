package bsi.pcs.organo.service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.model.Comprador;
import bsi.pcs.organo.model.Endereco;
import bsi.pcs.organo.model.Fornecedor;
import bsi.pcs.organo.model.Item;
import bsi.pcs.organo.model.Pedido;
import bsi.pcs.organo.model.Produto;
import bsi.pcs.organo.model.Status;
import bsi.pcs.organo.repository.CompradorRepository;
import bsi.pcs.organo.repository.EnderecoRepository;
import bsi.pcs.organo.repository.FornecedorRepository;
import bsi.pcs.organo.repository.ItemRepository;
import bsi.pcs.organo.repository.PedidoRepository;
import bsi.pcs.organo.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private CompradorRepository compradorRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private NotificationService notificationService;
	
	@Cacheable(value = "pedido", key = "#pedidoId", unless = "#result == null")
	public Pedido retornarById(Long pedidoId) {
		return this.pedidoRepository.findById(pedidoId).get();
	}

	@CachePut(value = "pedido", key = "#pedidoId")
	@CacheEvict("fornecedorPedidos")
	public Pedido atualizarStatus(Long pedidoId, String status) {
		Optional<Pedido> pedidoEncontrado = this.pedidoRepository.findById(pedidoId);
		Status stat = Status.valueOf(status);
		pedidoEncontrado.get().setStatus(stat);
		this.pedidoRepository.save(pedidoEncontrado.get());
		return pedidoEncontrado.get();
	}

	public Object retornar(Date dataEntrega, String fornecedorCnpj) {
		return this.pedidoRepository.findByDataEntregaAndFornecedorCnpj(dataEntrega, fornecedorCnpj);
	}

	@CacheEvict("fornecedorPedidos")
	public void registrar(Pedido pedido, String compradorCpf, String fornecedorCnpj) throws IOException {
		Comprador compradorEncontrado = this.compradorRepository.findByCpf(compradorCpf);
		Fornecedor fornecedorEncontrado = this.fornecedorRepository.getByCnpj(fornecedorCnpj);
		Endereco enderecoEncontrado = this.enderecoRepository.findByCepAndCompradorCpf(pedido.getEndereco().getCep(), compradorCpf);
		pedido.setEndereco(enderecoEncontrado);
		pedido.setCompradorAssociado(compradorEncontrado);
		pedido.setFornecedorAssociado(fornecedorEncontrado);
		
		for(Item item : pedido.getItens()) {
			Produto produtoEncontrado = this.produtoRepository.findByFornecedorCnpjAndNome(fornecedorCnpj, item.getProduto().getNome());
			produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade() - item.getQuantidade());
			this.produtoRepository.save(produtoEncontrado);
			item.setProduto(produtoEncontrado);
			this.itemRepository.save(item);
		}
		
		this.pedidoRepository.save(pedido);
		this.enviarEmail(compradorEncontrado.getEmail(), fornecedorEncontrado.getEmail());
		this.enviarSms(compradorEncontrado.getCelular(), fornecedorEncontrado.getTelefoneMovel());
	}
	
	private void enviarEmail(String emailComprador, String emailFornecedor) throws IOException {
		String contentComprador = "Seu pedido acaba de ser criado. Acompanhe o status do seu pedido na sua página de perfil.";
		String assuntoComprador = "Recebemos seu pedido!";
		String contentFornecedor = "Um pedido acabou de chegar para você. Para mais detalhes, verifique seus pedidos em sua página de perfil.";
		String assuntoFornecedor = "Um pedido chegou para você!";
		String emailFrom = "organomercadodeorganicos@gmail.com";
		this.notificationService.sendEmail(emailFrom, emailComprador, contentComprador, assuntoComprador);
		this.notificationService.sendEmail(emailFrom, emailFornecedor, contentFornecedor, assuntoFornecedor);
	}
	
	private void enviarSms(String celularComprador, String celularFornecedor) {
		String contentComprador = "Organo - Recebemos seu pedido!";
		String contentFornecedor = "Organo - Um pedido acabou de chegar para você!";
		if(celularComprador != null) this.notificationService.sendSms("+55" + celularComprador, contentComprador);
		if(celularFornecedor != null) this.notificationService.sendSms("+55" + celularFornecedor, contentFornecedor);
	}

}
