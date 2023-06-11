package bsi.pcs.organo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query(value = "SELECT * FROM pedido p INNER JOIN fornecedor f on (f.cnpj = ?2 and p.data_entrega = ?1)", 
			  nativeQuery = true)
	public Pedido findByDataEntregaAndFornecedorCnpj(Date dataEntrega, String fornecedorCnpj);
	
	@Query(value = "SELECT * FROM pedido p INNER JOIN fornecedor f on (f.cnpj = ?1 and f.fornecedor_id = p.fornecedor_id)", 
			  nativeQuery = true)
	public List<Pedido> findByFornecedorCnpj(String cnpj);
	
	@Query(value = "SELECT * FROM pedido p INNER JOIN comprador c on (c.cpf = ?1 and c.comprador_id = p.comprador_id)", 
			  nativeQuery = true)
	public List<Pedido> findByCompradorCpf(String cpf);
}
