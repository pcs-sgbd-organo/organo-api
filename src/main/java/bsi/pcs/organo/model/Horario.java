package bsi.pcs.organo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "horario")
public class Horario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "horario_id")
	private Long id;
	@NotNull
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	@NotNull
	@Column(name = "horario_selecionado")
	private InfoHorarios horarioSelecionado;
	
	public Long getId() {
		return id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public InfoHorarios getHorarioSelecionado() {
		return horarioSelecionado;
	}
	public void setHorarioSelecionado(InfoHorarios horarioSelecionado) {
		this.horarioSelecionado = horarioSelecionado;
	}
	
	
}
