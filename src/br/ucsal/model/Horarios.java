package br.ucsal.model;

import java.time.LocalTime;

public class Horarios {
	
	private Integer id;
	private LocalTime hora_inicio;
	private LocalTime hora_fim;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalTime getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public LocalTime getHora_fim() {
		return hora_fim;
	}
	public void setHora_fim(LocalTime hora_fim) {
		this.hora_fim = hora_fim;
	}
	
	

}
