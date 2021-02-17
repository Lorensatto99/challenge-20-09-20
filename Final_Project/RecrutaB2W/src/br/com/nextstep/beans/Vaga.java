package br.com.nextstep.beans;

import java.io.Serializable;

public class Vaga implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nomeVaga;
	private String descVaga;
	private double salario;

	
	//CONSTRUTORES
	public Vaga(int id, String nomeVaga, String descVaga, double salario) {
		super();
		this.id = id;
		this.nomeVaga = nomeVaga;
		this.descVaga = descVaga;
		this.salario = salario;
	}
	
	public Vaga() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeVaga() {
		return nomeVaga;
	}
	public void setNomeVaga(String nomeVaga) {
		this.nomeVaga = nomeVaga;
	}
	public String getDescVaga() {
		return descVaga;
	}
	public void setDescVaga(String descVaga) {
		this.descVaga = descVaga;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Vaga [id=" + id + ", nomeVaga=" + nomeVaga + ", descVaga=" + descVaga + ", salario=" + salario + "]";
	}
			
}
