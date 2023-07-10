package bsi.pcs.organo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "produto")
@JsonInclude(Include.NON_NULL)
public class Produto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produto_id")
	private Long id;
	private String nome;
	private float preco;
	private int quantidade;
	private Date validade;
	@Column(name = "foto_url")
	private String fotoUrl;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	private boolean deleted;
	
	public Produto() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Long getId() {
		return id;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	
	public void setQuantidade(int qtd) {
		this.quantidade = qtd;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public boolean equals(Object obj) {
		Produto p = (Produto) obj;

		if(this.getId().equals(p.getId()) &&
				this.getFornecedor().equals(p.getFornecedor()) &&
				this.getFotoUrl().equals(p.getFotoUrl()) &&
				this.getNome().equals(p.getNome()) &&
				this.getPreco() == p.getPreco() &&
				this.getQuantidade() == p.getQuantidade() &&
				(this.getValidade().compareTo(p.getValidade()) == 0) &&
				(this.isDeleted() == p.isDeleted())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Id: " + this.getId() + "\nNome: " + this.getNome() + "\nPreço: " + this.getPreco() + "\nQuantidade: " + this.getQuantidade() + "\nFoto: " + this.getFotoUrl() + "\nValidade: " + this.getValidade() + "\nDeletado: " + this.isDeleted() + "\nFornecedor: " + this.getFornecedor() + "\n"; 
	}
}
