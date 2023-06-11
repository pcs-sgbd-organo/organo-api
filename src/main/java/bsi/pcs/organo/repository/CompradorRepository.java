package bsi.pcs.organo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.model.Comprador;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

	public Comprador findByCpf(String cpf);
	
	public Comprador getByEmail(String email);
	
}
