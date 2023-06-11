package bsi.pcs.organo.model;

public enum MetodoPagamento {
	CARTAO_CREDITO("Cartao_Credito"), CARTAO_DEBITO("Cartao_debito"), PIX("Pix"), DINHEIRO("Dinheiro");

	private final String metodoPagamento;
	
	private MetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	
	public String getMetodoPagamento() {
		return metodoPagamento;
	}
}
