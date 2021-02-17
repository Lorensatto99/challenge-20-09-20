package br.com.nextstep.bo;

import br.com.nextstep.beans.Vaga;
import br.com.nextstep.dao.VagaDAO;

/**
 * Esta classe é responsável por conter as regras de negócio de tudo aquilo que diz respeito à Vaga.
 * É ela que possui os métodos que se comunicam com o Front-End e também com o DAO, para que estes não interajam diretamente.<br>
 * Todos os métodos foram declarado como static para não haver necessidade de instânciar a classe na hora de usar estes métodos.
 * @author William Butler Poletto
 * @see br.com.nextstep.dao.VagaDAO;
 * @see br.com.nextstep.beans.Vaga; 
 */

public class VagaBO {

	/**
	 * Este método irá verificar se os dados que foram inseridos para a Vaga são válidos de acordo com o estabelecido 
	 * no Banco de Dados, e irá inserir uma Vaga, onde:<br>
	 * Salário <= 99999.99 and > 0<br>
	 * DescVaga <= 400 and > 0<br>
	 * NomeVaga <= 50 and > 0<br>
	 * @param vaga Este objeto deve conter todas as informações do Vaga.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String novaVaga(Vaga vaga) throws Exception{

		if(vaga.getNomeVaga() == null) {
			return "Nome da vaga não foi preenchido";
		}
		
		if(vaga.getNomeVaga().length() > 50) {
			return "Nome da vaga ultrapassou os limites";
		}
		
		if(vaga.getSalario() < 0) {
			return "Insira valores positivos";
		}

		if(vaga.getSalario() > 99999.99) {
			return "Não ultrapasse 5 dígitos";
		}
		
		if(vaga.getDescVaga() == null) {
			return "Descrição da Vaga não foi preenchida";
		}
		if(vaga.getDescVaga().length() > 400) {
			return "Descrição da vaga ultrapassou os limites";
		}
		
		VagaDAO dao = new VagaDAO();

		dao.add(vaga);
		dao.fechar();
		return "Cadastrado";
	}

	/**
	 * Este método irá verificar se o ID que foi inserido para a Vaga é válido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisará uma Vaga.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna uma Vaga totalmente preenchida.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static Vaga pesquisarVaga(int id) throws Exception{
		if(id < 1) {
			//throw new RuntimeException("ID invï¿½lido");
			return new Vaga();
		}
		
		VagaDAO dao = new VagaDAO();
		Vaga resposta = dao.getById(id);
		
		dao.fechar();
		
		return resposta;
	}
	
	/**
	 * Este método irá verificar se o ID e a descrição que foram inseridos para a Vaga são válidso de acordo com o estabelecido 
	 * no Banco de Dados, e atualizará uma Vaga.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @param novaDescricao Indica qual será a nova descrição da Vaga.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String atualizarVaga(int id, String novaDescricao)throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		if(novaDescricao == null) {
			return "Descrição inexistente";
		}
		
		if(novaDescricao.length() > 400) {
			return "Descrição ultrapassou os limites de 400 caracteres";
		}
		
		VagaDAO dao = new VagaDAO();

		dao.modifyDesc(novaDescricao, id);
		dao.fechar();
		
		return "Conversa de chatbot atualizada";
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para a Vaga é válido de acordo com o estabelecido 
	 * no Banco de Dados, e apagará uma Vaga.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String apagarVaga(int id) throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		VagaDAO dao = new VagaDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	}
	
}
