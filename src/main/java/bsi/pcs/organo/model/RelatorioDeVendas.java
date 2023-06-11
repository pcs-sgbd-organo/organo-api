package bsi.pcs.organo.model;

public class RelatorioDeVendas {
	
	private int totalDePedidos;
	private float ganhoTotalDePedidos;
	private int totalProdutosCadastrados;
	private int totalItensVendidos;
	private String fornecedor;
	
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public int getTotalDePedidos() {
		return totalDePedidos;
	}
	public void setTotalDePedidos(int totalDePedidos) {
		this.totalDePedidos = totalDePedidos;
	}
	public float getGanhoTotalDePedidos() {
		return ganhoTotalDePedidos;
	}
	public void setGanhoTotalDePedidos(float ganhoTotalDePedidos) {
		this.ganhoTotalDePedidos = ganhoTotalDePedidos;
	}
	public int getTotalProdutosCadastrados() {
		return totalProdutosCadastrados;
	}
	public void setTotalProdutosCadastrados(int totalProdutosCadastrados) {
		this.totalProdutosCadastrados = totalProdutosCadastrados;
	}
	public int getTotalItensVendidos() {
		return totalItensVendidos;
	}
	public void setTotalItensVendidos(int totalItensVendidos) {
		this.totalItensVendidos = totalItensVendidos;
	}
	
}
