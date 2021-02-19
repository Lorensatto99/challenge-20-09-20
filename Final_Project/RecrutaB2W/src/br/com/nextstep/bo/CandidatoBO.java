package br.com.nextstep.bo;

import java.util.List;

import br.com.nextstep.beans.Candidato;
import br.com.nextstep.dao.CandidatoDAO;

/**
 * Esta classe � respons�vel por conter as regras de neg�cio de tudo aquilo que diz respeito ao Candidato.
 * � ela que possui os m�todos que se comunicam com o Front-End e tamb�m com o DAO, para que estes n�o interajam diretamente.<br>
 * Todos os m�todos foram declarado como static para n�o haver necessidade de inst�nciar a classe na hora de usar estes m�todos.
 * @author Eduardo Vin�cius Benigno da Costa
 * @see br.com.nextstep.dao.CandidatoDAO;
 * @see br.com.nextstep.beans.Candidato; 
 */

public class CandidatoBO {

	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para o Candidato � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e apagar� uma Candidato.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @see br.com.nextstep.dao.CandidatoDAO;
	 * @see br.com.nextstep.beans.Candidato; 
	 * @throws Exception
	 */
	
	public static String apagarCandidato(int id) throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		CandidatoDAO dao = new CandidatoDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para o Candidato � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisar� uma Candidato.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um Candidato totalmente preenchido.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vin�cius Benigno da Costa
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
	 * Este m�todo ir� verificar se o email e senha que foram inseridos para o Candidato s�o v�lidos de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisar� uma Candidato.
	 * @param email Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @param senha Indica a senha do Candidato.
	 * @return Retorna um Candidato totalmente preenchido.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vin�cius Benigno da Costa
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
	 * Este m�todo ir� verificar se o ID que foi inserido para o Candidato � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisar� uma lista de Candidatos.
	 * @return Retorna uma lista de Candidatos totalmente preenchidos.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @throws Exception
	 */

	public static List<Candidato> mostraCandidato() throws Exception{
			
		CandidatoDAO dao = new CandidatoDAO();
			
		List<Candidato> listaCandidatos = dao.getAll();
		
		dao.fechar();
		
		return listaCandidatos;
		
		
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para o Candidato � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e atualizar� um Candidato.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @param candidato Carrega todas as informa��es do Candiato a ser atualizado.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @throws Exception
	 */
	
	public static String atualizaEmail(int id, Candidato candidato) throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		if(candidato.getEmail() == null) {
			return "Email nulo";
		}
				
		CandidatoDAO dao = new CandidatoDAO();

		dao.modifyEmail(id, candidato.getEmail());
		
		return "Email atualizado";
	
				
	}
	
	/**
	 * Este m�todo ir� verificar se os dados que foram inseridos para o Candidato s�o v�lidos de acordo com o estabelecido 
	 * no Banco de Dados, e ir� inserir um Candidato, onde:<br>
	 * Cpf < 11<br>
	 * @param candidato Este objeto deve conter todas as informa��es do Candidato.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.CandidatoDAO;
 	 * @see br.com.nextstep.beans.Candidato;
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @throws Exception
	 */
//	
	public static String add(Candidato candidato) throws Exception {

		if(candidato.getCpf().length() < 11) {
			return "CPF inv�lido";
		} else if (candidato.getVaga() == null) {
			return "Uma vaga � necess�ria para adicionar o candidato";
		}
				
		CandidatoDAO dao = new CandidatoDAO();

		dao.add(candidato);
		dao.fechar();
		return "Cadastrado";

	}

}
