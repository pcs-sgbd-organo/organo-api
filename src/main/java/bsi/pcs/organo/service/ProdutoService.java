package bsi.pcs.organo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.model.Produto;
import bsi.pcs.organo.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@CacheEvict("fornecedorProdutos")
	public void cadastrar(Produto produto) {
		this.produtoRepository.save(produto);
	}
	
	public Produto retornar(String cnpjFornecedor, String nomeProduto) {
		Produto pe = this.produtoRepository.findByFornecedorCnpjAndNome(cnpjFornecedor, nomeProduto);
		return pe;
	}

	@Cacheable(value = "produto", key = "#id", unless = "#result == null")
	public Produto retornarById(Long id) {
		Optional<Produto> pe = this.produtoRepository.findById(id);
		return pe.get();
	}

	@CachePut(value = "produto", key = "#id")
	@CacheEvict("fornecedorProdutos")
	public Produto atualizar(Produto produto) {
		Produto produtoEncontrado = this.produtoRepository.findByFornecedorCnpjAndNome(produto.getFornecedor().getCnpj(), produto.getNome());
		produtoEncontrado.setNome(produto.getNome());
		produtoEncontrado.setFotoUrl(produto.getFotoUrl());
		produtoEncontrado.setPreco(produto.getPreco());
		produtoEncontrado.setValidade(produto.getValidade());
		produtoEncontrado.setQuantidade(produto.getQuantidade());
		this.produtoRepository.save(produtoEncontrado);
		return produtoEncontrado;
	}

	@Caching(evict = { 
			  @CacheEvict("produto"), 
			  @CacheEvict("fornecedorProdutos") })
	public void deletarProduto(Long produtoId) {
		Optional<Produto> pe = this.produtoRepository.findById(produtoId);
		Produto produtoEncontrado = pe.get();
		produtoEncontrado.setDeleted(true);
		this.produtoRepository.save(produtoEncontrado);
	}
}
