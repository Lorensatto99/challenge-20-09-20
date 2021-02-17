package br.com.nextstep.bo;

import br.com.nextstep.beans.AudioVideo;
import br.com.nextstep.dao.AudioVideoDAO;

/**
 * Esta classe é responsável por conter as regras de negócio de tudo aquilo que diz respeito ao AudioVideo.
 * É ela que possui os métodos que se comunicam com o Front-End e também com o DAO, para que estes não interajam diretamente.<br>
 * Todos os métodos foram declarado como static para não haver necessidade de instânciar a classe na hora de usar estes métodos.
 * @author Rogerio Pizzo dos Santos
 * @see br.com.nextstep.dao.AudioVideoDAO;
 * @see br.com.nextstep.beans.AudioVideo; 
 */

public class AudioVideoBO {

	/**
	 * Este método irá verificar se o ID que foi inserido para a AudioVideo é válido de acordo com o estabelecido 
	 * no Banco de Dados, e apagará uma AudioVideo.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.AudioVideoDAO;
 	 * @see br.com.nextstep.beans.AudioVideo;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String apagarAudioVideo(int id) throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para a AudioVideo é válido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisará uma AudioVideo.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @return Retorna um AudioVideo contendo um ID e uma URL de áudio ou vídeo
	 * @see br.com.nextstep.dao.AudioVideoDAO;
 	 * @see br.com.nextstep.beans.AudioVideo;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static AudioVideo pesquisarAudioVideo(int id) throws Exception{
		if(id < 1) {
			//throw new RuntimeException("ID invï¿½lido");
			return new AudioVideo();
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();
		AudioVideo resposta = dao.getById(id);
		
		dao.fechar();
		
		return resposta;
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para a AudioVideo é válido de acordo com o estabelecido 
	 * no Banco de Dados, e atualizará um áudio.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @param av Carrega a URL do áudio a ser atualizado.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.AudioVideoDAO;
 	 * @see br.com.nextstep.beans.AudioVideo;
 	 * @author Rogerio Pizzo dos Santos
	 * @throws Exception
	 */
	
	public static String atualizaAudio(int id, AudioVideo av)throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		if(av.getPathAudio() == null) {
			return "Arquivo inexistente de áudio";
		}
		
		if(av.getPathAudio().length() > 200) {
			return "O número de caracteres excede 200";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.modifyAudio(id, av);
		dao.fechar();
		
		return "Arquivo de áudio Atualizado";
	}
	
	/**
	 * Este método irá verificar se o ID que foi inserido para a AudioVideo é válido de acordo com o estabelecido 
	 * no Banco de Dados, e atualizará um vídeo.
	 * @param id Indica qual linha do Banco de Dados que será pesquisada.
	 * @param av Carrega a URL do vídeo a ser atualizado.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.AudioVideoDAO;
 	 * @see br.com.nextstep.beans.AudioVideo;
 	 * @author Rogerio Pizzo dos Santos
	 * @throws Exception
	 */
	
	public static String atualizaVideo(int id, AudioVideo av)throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		if(av.getPathVideo() == null) {
			return "Arquivo inexistente de vídeo";
		}
		
		if(av.getPathVideo().length() > 200) {
			return "O número de caracteres excede 200";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.modifyVideo(id, av);
		dao.fechar();
		
		return "Arquivo de vídeo Atualizado";
	}
	
	/**
	 * Este método irá verificar se os dados que foram inseridos para a Vaga são válidos de acordo com o estabelecido 
	 * no Banco de Dados, e irá inserir um áudio ou vídeo, onde:<br>
	 * PathAudio <= 200 and > 0<br>
	 * PathVideo <= 200 and > 0<br>
	 * @param av Este objeto deve conter todas as informações do AudioVideo.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o método.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String novoAudioVideo(AudioVideo av) throws Exception{
		
		if(av.getPathVideo() == null && av.getPathAudio() == null) {
			return "Arquivo inexistente de áudio ou vídeo";
		}
		if(av.getPathAudio() != null) {
			if (av.getPathAudio().length() > 200) {
				return "O número de caracteres excede 200";
			}
		}
		if(av.getPathVideo() != null) {
			if (av.getPathVideo().length() > 200) {
				return "O número de caracteres excede 200";
			}
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.add(av);
		dao.fechar();
		return "Cadastrado";
	}

}
