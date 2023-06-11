package bsi.pcs.organo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query(value = "SELECT * FROM produto p INNER JOIN fornecedor f on (p.fornecedor_id = f.fornecedor_id and f.cnpj = ?1) inner join item i on (p.produto_id = i.produto_id)", 
			  nativeQuery = true)
	public List<Item> findByFornecedorCnpj(String fornecedorCnpj);
}
