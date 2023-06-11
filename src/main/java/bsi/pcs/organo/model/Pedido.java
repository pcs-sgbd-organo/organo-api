package bsi.pcs.organo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "pedido")
@JsonInclude(Include.NON_NULL)
public class Pedido implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id")
	private Long id;
	private float valor;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "comprador_id")
	private Comprador compradorAssociado;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedorAssociado;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataEntrega;
	@Column(name = "status_pedido")
	private Status status;
	private MetodoPagamento metodoPagamento;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pedido")
	private List<Item> itens;
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	@Column(name = "horario_entrega")
	private InfoHorarios horarioEntrega;
	@Column(name = "forma_entrega")
	private InfoEntrega formaEntrega;
	
	public Pedido() {}
	
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public Comprador getCompradorAssociado() {
		return compradorAssociado;
	}

	public void setCompradorAssociado(Comprador compradorAssociado) {
		this.compradorAssociado = compradorAssociado;
	}

	public Fornecedor getFornecedorAssociado() {
		return fornecedorAssociado;
	}

	public void setFornecedorAssociado(Fornecedor fornecedorAssociado) {
		this.fornecedorAssociado = fornecedorAssociado;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}	
	
	public Long getId() {
		return id;
	}

	public InfoHorarios getHorarioEntrega() {
		return horarioEntrega;
	}

	public void setHorarioEntrega(InfoHorarios horarioEntrega) {
		this.horarioEntrega = horarioEntrega;
	}

	public InfoEntrega getFormaEntrega() {
		return formaEntrega;
	}

	public void setFormaEntrega(InfoEntrega formaEntrega) {
		this.formaEntrega = formaEntrega;
	}
}
