package br.com.nextstep.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.nextstep.dao.CandidatoDAO;

public class Ranking implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Candidato> candidatos;

	public Ranking(List<Candidato> candidatos) {
		super();
		this.candidatos = candidatos;
	}

	public Ranking() {
		super();
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	
	public void addCandidato(Candidato candidato) {
		if (candidatos == null) {
			candidatos = new ArrayList<Candidato>();
		}
		
		candidatos.add(candidato);
	}
	
	public void removeCandidato(int id) throws Exception {
		if (candidatos != null) {
			Candidato candidato = new CandidatoDAO().getById(id);
			if (candidato != null) {
				candidatos.remove(candidato);
			}
		}
	}
	
}
