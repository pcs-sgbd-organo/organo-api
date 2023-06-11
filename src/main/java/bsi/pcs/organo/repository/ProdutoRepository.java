package bsi.pcs.organo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query(value = "SELECT * FROM produto p INNER JOIN fornecedor f on (f.cnpj = ?1 and p.nome = ?2)", 
			  nativeQuery = true)
	public Produto findByFornecedorCnpjAndNome(String fornecedorCnpj, String nomeProduto);
	
	public Optional<Produto> findById(Long id);
	
	@Query(value = "SELECT * FROM produto p INNER JOIN fornecedor f on (f.cnpj = ?1 and f.fornecedor_id = p.fornecedor_id)"
			+ "and p.deleted = false", 
			  nativeQuery = true)
	public List<Produto> findByFornecedorCnpj(String cnpj);
}
