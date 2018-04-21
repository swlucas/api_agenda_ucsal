package br.ucsal.model;

import java.time.LocalDate;

public class Agendamento {
	
	private Integer id;
	private String assunto;
	private String descricao;
	private LocalDate data;
	private Horarios horarios;
	private Professor professor;
	private Aluno aluno;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Horarios getHorarios() {
		return horarios;
	}
	public void setHorarios(Horarios horarios) {
		this.horarios = horarios;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	

}
