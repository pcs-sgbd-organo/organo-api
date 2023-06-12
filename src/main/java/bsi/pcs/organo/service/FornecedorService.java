package bsi.pcs.organo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.model.Endereco;
import bsi.pcs.organo.model.Fornecedor;
import bsi.pcs.organo.model.Horario;
import bsi.pcs.organo.model.Item;
import bsi.pcs.organo.model.Pedido;
import bsi.pcs.organo.model.Produto;
import bsi.pcs.organo.model.RelatorioDeVendas;
import bsi.pcs.organo.repository.EnderecoRepository;
import bsi.pcs.organo.repository.FornecedorRepository;
import bsi.pcs.organo.repository.HorarioRepository;
import bsi.pcs.organo.repository.ItemRepository;
import bsi.pcs.organo.repository.PedidoRepository;
import bsi.pcs.organo.repository.ProdutoRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private HorarioRepository horarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Fornecedor> listFornecedores() {
		return this.fornecedorRepository.findAll();
	}
	
	public void cadastrar(Fornecedor fornecedor) {		
		this.fornecedorRepository.save(fornecedor);
		
		if(!fornecedor.getEnderecos().isEmpty()) {
			for(Endereco endereco : fornecedor.getEnderecos()) {
				endereco.setFornecedor(fornecedor);
				this.enderecoRepository.save(endereco);
			}
		}
		
		if(!fornecedor.getHorarios().isEmpty()) {
			for(Horario horario : fornecedor.getHorarios()) {
				horario.setFornecedor(fornecedor);
				this.horarioRepository.save(horario);
			}
		}
	}

	@Cacheable(value = "fornecedor", key="#cnpj", unless = "#result == null")
	public Fornecedor retornar(String cnpj) {
		Fornecedor fe = this.fornecedorRepository.getByCnpj(cnpj);
		return fe;
	}
	
	@CachePut(value = "fornecedor", key = "#cnpj")
	public void atualizar(Fornecedor fornecedor) {
		Fornecedor fornecedorEncontrado = this.fornecedorRepository.getByCnpj(fornecedor.getCnpj());
		fornecedorEncontrado.setEmail(fornecedor.getEmail());
		fornecedorEncontrado.setNomeFantasia(fornecedor.getNomeFantasia());
		fornecedorEncontrado.setSenha(fornecedor.getSenha());	
		this.fornecedorRepository.save(fornecedorEncontrado);
		
		if(!fornecedor.getEnderecos().isEmpty()) {
			for(Endereco ee : fornecedor.getEnderecos()) {
				Endereco enderecoEncontrado = this.enderecoRepository.findByCepRuaNumeroComplementoFornecedorId(ee.getCep(), ee.getRua(), ee.getNumero(), ee.getComplemento(), fornecedorEncontrado.getId());
				if(enderecoEncontrado == null) {
					ee.setFornecedor(fornecedorEncontrado);
					this.enderecoRepository.save(ee);
					this.enderecoRepository.delete(fornecedorEncontrado.getEnderecos().get(0));
				}
			}
		}
	}

	public Fornecedor autenticar(Fornecedor fornecedor) {
		Fornecedor fornecedorEncontrado = this.fornecedorRepository.getByEmail(fornecedor.getEmail());
		if(fornecedor.getSenha().equals(fornecedorEncontrado.getSenha())) return fornecedorEncontrado;
		return null;
	}
	
	@Cacheable(value = "fornecedorProdutos", key="#cnpj", unless = "#result == null")
	public List<Produto> listarProdutos(String cnpj) {
		List<Produto> produtosEncontrados =  this.produtoRepository.findByFornecedorCnpj(cnpj);		
		return produtosEncontrados;
	}
	
	@Cacheable(value = "fornecedorPedidos", key="#cnpj", unless = "#result == null")
	public List<Pedido> listarPedidos(String cnpj) {
		List<Pedido> pedidosEncontrados = this.pedidoRepository.findByFornecedorCnpj(cnpj);
		return pedidosEncontrados;
	}
	
	public RelatorioDeVendas gerarRelatorioDeVendas(Fornecedor fornecedor) {
		RelatorioDeVendas relatorio = new RelatorioDeVendas();
		String cnpj = fornecedor.getCnpj();
		
		List<Pedido> pedidos = this.listarPedidos(cnpj);
		List<Produto> produtos = this.listarProdutos(cnpj);
		List<Item> itens = this.itemRepository.findByFornecedorCnpj(cnpj);
		
		float totalGanhoPedidos = 0;
		
		for(Pedido pedido : pedidos) {
			totalGanhoPedidos += pedido.getValor();
		}
		
		relatorio.setTotalDePedidos(pedidos.size());
		relatorio.setTotalItensVendidos(itens.size());
		relatorio.setTotalProdutosCadastrados(produtos.size());
		relatorio.setGanhoTotalDePedidos(totalGanhoPedidos);
		relatorio.setFornecedor(fornecedor.getNomeFantasia());
		
		return relatorio;
	}
}
