package bsi.pcs.organo.model;

public enum Status {
	EM_ABERTO("Em_Aberto"), INICIADO("Iniciado"), CANCELADO("Cancelado"), CONCLUIDO("Concluido");

	private final String statusPedido;
	
	private Status(String status) {
		this.statusPedido = status;
	}
	
	public String getStatusPedido() {
		return statusPedido;
	}
}
