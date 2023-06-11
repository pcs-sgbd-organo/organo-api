package bsi.pcs.organo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsi.pcs.organo.model.Fornecedor;
import bsi.pcs.organo.model.Produto;
import bsi.pcs.organo.service.FornecedorService;
import bsi.pcs.organo.service.ProdutoService;

@RestController
@RequestMapping("/produto/{cnpjFornecedor}")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;

	@PostMapping("/cadastrar")
	public ResponseEntity<?> create(@RequestBody(required = true) Produto produto,
									@PathVariable(required = true) String cnpjFornecedor) {
		
		Fornecedor fornecedorEncontrado = this.fornecedorService.retornar(cnpjFornecedor);
		produto.setFornecedor(fornecedorEncontrado);
		
		if(this.produtoService.retornar(produto.getFornecedor().getCnpj(), produto.getNome()) != null) {
			return ResponseEntity.badRequest().body("Produto já está cadastrado");
		}
		
		this.produtoService.cadastrar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso."); 
		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<?> update(@RequestBody(required = true) Produto produto) {
		
		if(this.produtoService.retornar(produto.getFornecedor().getCnpj(), produto.getNome()) == null) {
			return ResponseEntity.badRequest().body("Produto informado não existe");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.atualizar(produto)); 
		
	}
	
	@GetMapping("/{produtoId}")
	public ResponseEntity<?> getProduto(@PathVariable(required = true) Long produtoId) {
	
		Produto produto = this.produtoService.retornarById(produtoId);
		if(produto == null) return ResponseEntity.badRequest().body("Os dados informados não estão associados a nenhum produto.");
		
		return ResponseEntity.status(HttpStatus.OK).body(produto);
	}
	
	@DeleteMapping("/deletar/{produtoId}")
	public ResponseEntity<?> deleteProduto(@PathVariable(required = true) Long produtoId) {
		Produto produto = this.produtoService.retornarById(produtoId);
		if(produto == null) return ResponseEntity.badRequest().body("Os id informado não estão associados a nenhum produto.");
		
		this.produtoService.deletarProduto(produtoId);
		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
		
	}
	
}
