package bsi.pcs.organo.model;

import java.util.ArrayList;
import java.util.List;

public enum InfoHorarios {
	MANHA("Manhã, de 9h às 12h"), TARDE("Tarde, de 13h às 17h"), NOITE("Noite, de 18h às 22h");

	private final String infoHorarios;
	
	private InfoHorarios(String infoHorarios) {
		this.infoHorarios = infoHorarios;
	}
	
	public String getInfoHorarios() {
		return infoHorarios;
	}
	
	public static List<String> getAllInfoHorarios() {
		List<String> allInfo = new ArrayList<>();
		for(InfoHorarios ih : InfoHorarios.values()) {
			allInfo.add(ih.getInfoHorarios());
		}
		return allInfo;
	}
}
