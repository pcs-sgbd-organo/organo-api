package bsi.pcs.organo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query(value = "SELECT * FROM endereco e where (e.cep = ?1 and e.rua = ?2 and e.numero = ?3 and e.complemento = ?4 and e.comprador_id = ?5)", 
	  nativeQuery = true)
	public Endereco findByCepRuaNumeroComplementoCompradorId(String cep, String rua, int numero, String complemento, Long compradorId);
	
	@Query(value = "SELECT * FROM endereco e INNER JOIN comprador c on (e.cep = ?1 and e.comprador_id = c.comprador_id and c.cpf = ?2)", 
			  nativeQuery = true)
	public Endereco findByCepAndCompradorCpf(String cep, String compradorCpf);
	
	@Query(value = "SELECT * FROM endereco e where (e.cep = ?1 and e.rua = ?2 and e.numero = ?3 and e.complemento = ?4 and e.fornecedor_id = ?5)", 
			  nativeQuery = true)
			public Endereco findByCepRuaNumeroComplementoFornecedorId(String cep, String rua, int numero, String complemento, Long fornecedorId);
}
