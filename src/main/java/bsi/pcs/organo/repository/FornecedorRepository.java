package bsi.pcs.organo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	public Fornecedor getByCnpj(String cnpj);
	
	public Fornecedor getByEmail(String email);

}
