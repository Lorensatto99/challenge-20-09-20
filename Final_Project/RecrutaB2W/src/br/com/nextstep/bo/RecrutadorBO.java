package br.com.nextstep.bo;

import java.util.List;

import br.com.nextstep.beans.Recrutador;
import br.com.nextstep.dao.RecrutadorDAO;

/**
 * Esta classe é responsável por conter as regras de negócio de tudo aquilo que diz respeito ao Recrutador.
 * É ela que possui os métodos que se comunicam com o Front-End e também com o DAO, para que estes não interajam diretamente.<br>
 * Todos os métodos foram declarado como static para não haver necessidade de instânciar a classe na hora de usar estes métodos.
 * @author Guilherme Rodriguero de Souza
 * @see br.com.nextstep.dao.RecrutadorDAO;
 * @see br.com.nextstep.beans.Recrutador; 
 */

public class RecrutadorBO {

	/**
	 * Este método irá verificar se o ID que foi inserido para o Recrutador é válido de acordo com o estabelecido 
	 * no Banco de Dados, e apagará um Recrutador.
	 * @param id Indica qual linha do Banco de Dados que será apagada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */

	public static String apagarRecrutador(int id) throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		RecrutadorDAO dao = new RecrutadorDAO();


		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para o Recrutador é válido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisará um Recrutador.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna um Recrutador totalmente preenchido.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */

	public static Recrutador mostraRecrutador(int id) throws Exception{
		if(id < 1) {
			return new Recrutador();
		}
		
		RecrutadorDAO dao = new RecrutadorDAO();
		Recrutador recrutador = dao.getById(id);
		
		dao.fechar();
		
		return recrutador;
	}
	
	/**
	 * Este método irá pesquisar uma lista de  Recrutadores.
	 * @return Retorna uma lista de Recrutadores totalmente preenchido.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */

	public static List<Recrutador> mostraRecrutador() throws Exception{
			
		RecrutadorDAO dao = new RecrutadorDAO();
			
		List<Recrutador> listaRecrutadores = dao.getAll();
		
		dao.fechar();
		
		return listaRecrutadores;
		
		
	}
	
	/**
	 * Este método irá verificar se o email e senha que foram inseridos para o Recrutador são válidos de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisará uma Recrutador.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna um Candidato totalmente preenchido.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
	 * @author Eduardo Vinícius Benigno da Costa
	 * @throws Exception
	 */

	public static Recrutador mostraLogin(String email,String senha) throws Exception{
		
		if(email == null) {
			return new Recrutador();
		}
		
		RecrutadorDAO dao = new RecrutadorDAO();
		Recrutador recrutador = dao.getByLogin(email,senha);
		
		dao.fechar();
		
		return recrutador;
	}
	
	/**
	 * Este método irá verificar se o email e a senha que foram inseridos para o Recrutador são válidos de acordo com o estabelecido 
	 * no Banco de Dados, e atualizará um Recrutador.
	 * @param email Indica qual Recrutador terá sua senha alterada.
	 * @param novaSenha Indica a nova Senha que será cadastrada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */
	
	public static String atualizaSenha(String email, String senha, Recrutador recrutador) throws Exception{

		if(recrutador.getEmail() == null) {
			return "Email nulo";
		}
		
		if(recrutador.getSenha() == null) {
			return "Senha nula";
		}
				
		RecrutadorDAO dao = new RecrutadorDAO();

		dao.modifySenha(recrutador.getEmail(),recrutador.getSenha());
		
		return "Email atualizado";
				
	}

	/**
	 * Este método irá verificar se os dados que foram inseridos para o Recrutador são válidos de acordo com o estabelecido 
	 * no Banco de Dados, e cadastrará um Recrutador, onde:<br>
	 * Nome <= 100 and > 0<br>
	 * Email <= 100 and > 0<br>
	 * Senha <= 16 and > 0<br> 
	 * @param recrutador Este objeto deve conter todas as informações do Recrutador.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */
	
	public static String add(Recrutador recrutador) throws Exception {
		
		if(recrutador.getNome().length() > 100) {
			return "Nome excede 100 caracteres";
		}
		
		if(recrutador.getEmail().length() > 100) {
			return "Email excede 100 caracteres";
		}
		
		if(recrutador.getSenha().length() > 16) {
			return "Senha excede 16 caracteres";
		}
		
		if(recrutador.getNome() == null) {
			return "Nome inválido";
		}
		
		if(recrutador.getEmail() == null) {
			return "Email inválido";
		}
		
		if(recrutador.getSenha() == null) {
			return "Senha inválido";
		}
		
		recrutador.setEmail(recrutador.getEmail().toLowerCase());
		
		RecrutadorDAO dao = new RecrutadorDAO();
		dao.add(recrutador);
		dao.fechar();
		return "Cadastrado";
	}
}
