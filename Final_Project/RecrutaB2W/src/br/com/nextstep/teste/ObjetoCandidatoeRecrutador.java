package br.com.nextstep.teste;

import br.com.nextstep.beans.Candidato;
import br.com.nextstep.beans.Recrutador;
import br.com.nextstep.bo.CandidatoBO;
import br.com.nextstep.bo.RecrutadorBO;

public class ObjetoCandidatoeRecrutador {
	
	public static void main(String[] args) throws Exception{
	
		Candidato candidato = CandidatoBO.mostraLogin("juliano.pereira@hotmail.com", "12345");
		System.out.println(candidato);
		
		Recrutador recrutado = RecrutadorBO.mostraLogin("rc_rodrigo@rodrigo.com", "1234");
		System.out.println(recrutado);
		
		
	}
	
	

}
