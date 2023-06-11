package bsi.pcs.organo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

}
