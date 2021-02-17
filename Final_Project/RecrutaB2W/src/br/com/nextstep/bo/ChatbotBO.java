package br.com.nextstep.bo;

import br.com.nextstep.beans.Chatbot;
import br.com.nextstep.dao.ChatbotDAO;

/**
 * Esta classe é responsável por conter as regras de negócio de tudo aquilo que diz respeito ao Chatbot.
 * É ela que possui os métodos que se comunicam com o Front-End e também com o DAO, para que estes não interajam diretamente.<br>
 * Todos os métodos foram declarado como static para não haver necessidade de instânciar a classe na hora de usar estes métodos.
 * @author Celso Lorensatto da Silva Filho
 * @see br.com.nextstep.dao.ChatbotDAO;
 * @see br.com.nextstep.beans.Chatbot; 
 */

public class ChatbotBO {

	/**
	 * Este método irá verificar se os dados que foram inseridos para o Chatbot são válidos de acordo com o estabelecido 
	 * no Banco de Dados, e irá inserir um Chatbot, onde:<br>
	 * Respostas não pode ser nulo<br>
	 * @param candidato Este objeto deve conter todas as informações do Candidato.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.ChatbotDAO;
 	 * @see br.com.nextstep.beans.Chatbot;
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public static int novoChatbot(Chatbot chat) throws Exception{

		if(chat.getRespostas() == null) {
			return 0;
		}
		
		ChatbotDAO dao = new ChatbotDAO();

		int linhasAlteradas = dao.add(chat);
		dao.fechar();
		return linhasAlteradas;
	}

	/**
	 * Este método irá verificar se o ID que foi inserido para o Chatbot é válido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisará uma Chatbot.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna um Chatbot totalmente preenchido.
	 * @see br.com.nextstep.dao.ChatbotDAO;
 	 * @see br.com.nextstep.beans.Chatbot;
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public static Chatbot pesquisarChatbot(int id) throws Exception{
		if(id < 1) {
			//throw new RuntimeException("ID invï¿½lido");
			return new Chatbot();
		}
		
		ChatbotDAO dao = new ChatbotDAO();
		Chatbot chatbot = dao.getById(id);
		
		dao.fechar();
		
		return chatbot;
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para o Chatbot é válido de acordo com o estabelecido 
	 * no Banco de Dados, e atualizará um Chatbot.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @param chat Carrega todas as informações do Chatbot a ser atualizado.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.ChatbotDAO;
 	 * @see br.com.nextstep.beans.Chatbot;
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public static int atualizaChatbot(int id, Chatbot chat)throws Exception{
		if(id < 1) {
			return 0;
		}
		
		if(chat.getRespostas() == null) {
			return 0;
		}
		
		ChatbotDAO dao = new ChatbotDAO();

		int linhasAlteradas = dao.modifyConversa(chat, id);
		dao.fechar();
		
		return linhasAlteradas;
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para o Chatbot é válido de acordo com o estabelecido 
	 * no Banco de Dados, e apagará uma Chatbot.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.ChatbotDAO;
 	 * @see br.com.nextstep.beans.Chatbot;
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public static String apagarChatbot(int id) throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		ChatbotDAO dao = new ChatbotDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	}
	
}
