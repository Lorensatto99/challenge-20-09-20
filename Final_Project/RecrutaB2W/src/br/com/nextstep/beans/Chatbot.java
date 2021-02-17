package br.com.nextstep.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Chatbot implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
    private Map<String, String> respostas
    	= new LinkedHashMap<String, String>();
    
	//CONSTRUTORES
	public Chatbot() {}
	
	
	public Chatbot(int id, Map<String, String> respostas) {
		super();
		this.id = id;
		this.respostas = respostas;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Map<String, String> getRespostas() {
		return respostas;
	}


	public void setRespostas(Map<String, String> respostas) {
		this.respostas = respostas;
	}
	
	public void addRespostas(String s1, String s2) {
		this.respostas.put(s1, s2);
	}

	@Override
	public String toString() {
		return "Chatbot [id=" + id + ", respostas=" + respostas + "]";
	}

	
	
}	
