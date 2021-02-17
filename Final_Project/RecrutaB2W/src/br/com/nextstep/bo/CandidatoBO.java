package br.com.nextstep.bo;

import java.util.List;

import br.com.nextstep.beans.Candidato;
import br.com.nextstep.dao.CandidatoDAO;

/**
 * Esta classe é responsável por conter as regras de negócio de tudo aquilo que diz respeito ao Candidato.
 * É ela que possui os métodos que se comunicam com o Front-End e também com o DAO, para que estes não interajam diretamente.<br>
 * Todos os métodos foram declarado como static para não haver necessidade de instânciar a classe na hora de usar estes métodos.
 * @author Eduardo Vinícius Benigno da Costa
 * @see br.com.nextstep.dao.CandidatoDAO;
 * @see br.com.nextstep.beans.Candidato; 
 */

public class CandidatoBO {

	/**
	 * Este método irá verificar se o ID que foi inserido para o Candidato é válido de acordo com o estabelecido 
	 * no Banco de Dados, e apagará uma Candidato.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @author Eduardo Vinícius Benigno da Costa
	 * @see br.com.nextstep.dao.CandidatoDAO;
	 * @see br.com.nextstep.beans.Candidato; 
	 * @throws Exception
	 */
	
	public static String apagarCandidato(int id) throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		CandidatoDAO dao = new CandidatoDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para o Candidato é válido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisará uma Candidato.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna um Candidato totalmente preenchido.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vinícius Benigno da Costa
	 * @throws Exception
	 */
	
	public static Candidato mostraCandidato(int id) throws Exception{
		if(id < 1) {
			return new Candidato();
		}
		
		CandidatoDAO dao = new CandidatoDAO();
		Candidato candidato = dao.getById(id);
		
		dao.fechar();
		
		return candidato;
	}
	
	/**
	 * Este método irá verificar se o email e senha que foram inseridos para o Candidato são válidos de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisará uma Candidato.
	 * @param email Indica qual linha do Banco de Dados que será pesquisada.
	 * @param senha Indica a senha do Candidato.
	 * @return Retorna um Candidato totalmente preenchido.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vinícius Benigno da Costa
	 * @throws Exception
	 */

	public static Candidato mostraLogin(String email,String senha) throws Exception{
		
		if(email == null) {
			return new Candidato();
		}
		
		CandidatoDAO dao = new CandidatoDAO();
		Candidato candidato = dao.getByLogin(email,senha);
		
		dao.fechar();
		
		return candidato;
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para o Candidato é válido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisará uma lista de Candidatos.
	 * @return Retorna uma lista de Candidatos totalmente preenchidos.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vinícius Benigno da Costa
	 * @throws Exception
	 */

	public static List<Candidato> mostraCandidato() throws Exception{
			
		CandidatoDAO dao = new CandidatoDAO();
			
		List<Candidato> listaCandidatos = dao.getAll();
		
		dao.fechar();
		
		return listaCandidatos;
		
		
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para o Candidato é válido de acordo com o estabelecido 
	 * no Banco de Dados, e atualizará um Candidato.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @param candidato Carrega todas as informações do Candiato a ser atualizado.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vinícius Benigno da Costa
	 * @throws Exception
	 */
	
	public static String atualizaEmail(int id, Candidato candidato) throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		if(candidato.getEmail() == null) {
			return "Email nulo";
		}
				
		CandidatoDAO dao = new CandidatoDAO();

		dao.modifyEmail(id, candidato.getEmail());
		
		return "Email atualizado";
	
				
	}
	
	/**
	 * Este método irá verificar se os dados que foram inseridos para o Candidato são válidos de acordo com o estabelecido 
	 * no Banco de Dados, e irá inserir um Candidato, onde:<br>
	 * Cpf < 11<br>
	 * @param candidato Este objeto deve conter todas as informações do Candidato.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vinícius Benigno da Costa
	 * @throws Exception
	 */
	
	public static String add(Candidato candidato) throws Exception {

		if(candidato.getCpf().length() < 11) {
			return "CPF inválido";
		} else if (candidato.getVaga() == null) {
			return "Uma vaga é necessária para adicionar o candidato";
		}
				
		CandidatoDAO dao = new CandidatoDAO();

		dao.add(candidato);
		dao.fechar();
		return "Cadastrado";

	}

}
