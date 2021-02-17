package br.com.nextstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.nextstep.beans.AudioVideo;
import br.com.nextstep.beans.Candidato;
import br.com.nextstep.beans.Chatbot;
import br.com.nextstep.beans.Recrutador;
import br.com.nextstep.beans.Vaga;
import br.com.nextstep.conexao.ConectaBanco;
import br.com.nextstep.util.PadraoDAO;

/**
 * Nesta classe, por meio do Design Pattern Data Access Object, manipularemos a tabela T_RBW_CANDIDATO, a classe principal deste sistema, a qual possui CD_CANDIDATO como chave primária.
 * Todos os candidatos serão armazenados nesta tabela, e ela também faz referência com todas as demais tabelas dos sistema
 * por meio de chave estrangeira.<br>
 * Chaves Estrangeiras:<br>
 * NR_VAGA<br>
 * CD_AUDIO_VIDEO<br>
 * CD_CHATBOT<br>
 * CD_RECRUTADOR<br>
 * 
 * Estas FKs foram criadas passíveis de receber valores nulos, tendo em vista que elas somente serão manipuladas no momento
 * em que o Candidato realizar o respectivo teste.
 * 
 * Criamos três atributos para armazenar os componentes do JDBC.
 * 
 * @author Rogerio Pizzo dos Santos
 * @author William Butler Poletto
 * @author Eduardo Vinícius Benigno da Costa
 * @version 1.0
 * @see br.com.nextstep.beans.Candidato
 * @see br.com.nextstep.bo.CandidatoBO
 * @see br.com.nextstep.util.PadraoDAO
 */

public class CandidatoDAO implements PadraoDAO<Candidato> {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * O método construtor atua estabelecendo uma conexão com o  Banco de Dados
	 * @author Eduardo Vinícius Benigno da Costa
	 * @throws Exception
	 */
	
	public CandidatoDAO() throws Exception{
		con = ConectaBanco.conectar();
	}
	
	/**
	 * Este método efetua o desligamento de conexão com o Banco de Dados.
	 * @author Eduardo Vinícius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public void fechar() throws Exception{
		con.close();
	}
	
	/**
	 * Método para inserir uma linha na tabela T_RBW_CANDIDATO.<br>
	 * A coluna CD_CANDIDATO é a chave primária e está sendo inserida através de uma SEQUENCE.<br>
	 * As colunas CD_AUDIO_VIDEO, CD_CHATBOT, CD_RECRUTADOR e NR_VAGA são opcionais pois só serão preenchidas quando o Candidato realizar estas atividade.<br>
	 * A quantidade máxima de caracteres das colunas VARCHAR são:<br>
	 * NR_CPF = 11<br>
	 * NM_CANDIDATO = 100<br>
	 * DS_EMAIL = 100<br>
	 * NM_SENHA = 16<br>
	 * @param objeto Este objeto deve conter todas as informações do Candidato.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Rogerio Pizzo dos Santos
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Candidato
	 * @see br.com.nextstep.bo.CandidatoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public int add(Candidato objeto) throws Exception{

		stmt = con.prepareStatement("INSERT INTO T_RBW_CANDIDATO (CD_CANDIDATO, NR_CPF, DT_NASCIMENTO, NR_MEDALHA, NM_CANDIDATO, NM_SENHA, DS_EMAIL) VALUES(SQ_RBW_CANDIDATO.NEXTVAL, ?, TO_DATE(?,'YYYY-MM-DD'), ?, ?, ?, ?)");
		
		stmt.setString(1, objeto.getCpf());
		stmt.setString(2, objeto.getDataNascimento());
		stmt.setInt(3, 0);
		stmt.setString(4, objeto.getNome());
		stmt.setString(5, objeto.getSenha());
		stmt.setString(6, objeto.getEmail());
		
		return stmt.executeUpdate();
	}
	
	/**
	 * Método para deletar uma linha na tabela T_RBW_CANDIDATO.<br>
	 * A linha que será deletada deve ser indicada pelo seu ID. 
	 * @param id ID da linha do Banco de Dados que será removida.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author William Butler Poletto
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Candidato
	 * @see br.com.nextstep.bo.CandidatoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public int deleteById(int id) throws Exception{
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM T_RBW_CANDIDATO WHERE CD_CANDIDATO=?");
		stmt.setInt(1, id);
		
		return stmt.executeUpdate();
		
	}
	
	/**
	 * Método para atualizar uma linha na tabela T_RBW_CANDIDATO.<br>
	 * Este método tem como função alterar o email de um Candidato já existente.
	 * @param id ID da linha do Banco de Dados que será alterada.
	 * @param objeto Este objeto deve conter o novo email do Candidato.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Rogerio Pizzo dos Santos
	 * @see br.com.nextstep.beans.Candidato
	 * @see br.com.nextstep.bo.CandidatoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	public int modifyEmail(int id, String email) throws Exception {
		stmt = con.prepareStatement("UPDATE T_RBW_CANDIDATO SET DS_EMAIL=? WHERE CD_CANDIDATO=?");
		stmt.setString(1, email);
		stmt.setInt(2, id);
		
		return stmt.executeUpdate();
	}
	
	/**
	 * Método para atualizar uma linha na tabela T_RBW_CANDIDATO.<br>
	 * Este método tem como função inserir no Candidato um código de Chatbot, o qual é feito após o Candidato realizar esta atividade.
	 * @param cpf Indica qual linha do Banco de Dados que será alterada.
	 * @param objeto Este objeto deve conter os dados do Chatbot realizado pelo Candidato.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author William Butler Poletto
	 * @see br.com.nextstep.beans.Candidato
	 * @see br.com.nextstep.bo.CandidatoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	public int modifyChatbot(int cpf, Chatbot objeto) throws Exception {
		stmt = con.prepareStatement("UPDATE T_RBW_CANDIDATO SET CD_CHATBOT=? WHERE NR_CPF=?");
		stmt.setInt(1, objeto.getId());
		stmt.setInt(2, cpf);
		
		return stmt.executeUpdate();
	}
	
	/**
	 * Método para atualizar uma linha na tabela T_RBW_CANDIDATO.<br>
	 * Este método tem como função inserir no Candidato um código de AudioVideo, o qual é feito após o Candidato realizar esta atividade.
	 * @param cpf Indica qual linha do Banco de Dados que será alterada.
	 * @param objeto Este objeto deve conter a URL do áudio ou vídeo realizado pelo Candidato.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author William Butler Poletto
	 * @see br.com.nextstep.beans.Candidato
	 * @see br.com.nextstep.bo.CandidatoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	public int modifyAudioVideo(int cpf, AudioVideo objeto) throws Exception {
		stmt = con.prepareStatement("UPDATE T_RBW_CANDIDATO SET CD_AUDIO_VIDEO=? WHERE NR_CPF=?");
		stmt.setInt(1, objeto.getId());
		stmt.setInt(2, cpf);
		
		return stmt.executeUpdate();
	}
	
	/**
	 * Método para buscar uma linha na tabela T_RBW_CANDIDATO.<br>
	 * Este método tem como função buscar e mostrar um Candidato já existente.
	 * @param id ID da linha do Banco de Dados que será pesquisada.
	 * @return Retorna um objeto Candidato preenchido com suas respectivas informações.
	 * @author Eduardo Vinícius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Candidato
	 * @see br.com.nextstep.bo.CandidatoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public Candidato getById(int id) throws Exception{
		
		stmt = con.prepareStatement("SELECT * FROM T_RBW_CANDIDATO "
								+ "INNER JOIN T_RBW_VAGA "
								+ "ON T_RBW_CANDIDATO.NR_VAGA = T_RBW_VAGA.NR_VAGA "
								+ "INNER JOIN T_RBW_RECRUTADOR "
								+ "ON T_RBW_CANDIDATO.CD_RECRUTADOR = T_RBW_RECRUTADOR.CD_RECRUTADOR"
								+ "WHERE CD_CANDIDATO=?");
		stmt.setInt(1, id);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			
			return new Candidato(
					
					rs.getInt("CD_CANDIDATO"),
					rs.getString("NM_CANDIDATO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DT_NASCIMENTO"),
					new Vaga(
							rs.getInt("NR_VAGA"),
							rs.getString("NM_VAGA"),
							rs.getString("DS_VAGA"),
							rs.getDouble("VL_SALARIO")
							),
					rs.getInt("NR_MEDALHA"),
				    rs.getString("NR_CPF"),
					new Recrutador(
							rs.getInt("CD_RECRUTADOR"),
							rs.getString("NM_RECRUTADOR"),
							rs.getString("DS_EMAIL")
					));
			
//			return new Candidato(
//					
//					rs.getInt("CD_CANDIDATO"),
//					rs.getString("NM_CANDIDATO"),
//					rs.getString("DS_EMAIL"),
//					rs.getString("NM_SENHA"),
//					rs.getString("DT_NASCIMENTO"),
//					new Vaga(
//							rs.getInt("NR_VAGA"),
//							rs.getString("NM_VAGA"),
//							rs.getString("DS_VAGA"),
//							rs.getDouble("VL_SALARIO")
//							),
//					rs.getInt("NR_MEDALHA"),
//				    rs.getString("NR_CPF"),
//					new Recrutador(
//							rs.getInt("CD_RECRUTADOR"),
//							rs.getString("NM_RECRUTADOR"),
//							rs.getString("DS_EMAIL")
//							),
//					new Chatbot(
//							rs.getInt("CD_CHATBOT"),
//							rs.getString("DS_RESPOSTA")
//							),
//					new AudioVideo(
//							rs.getString("FL_AUDIO"),
//							rs.getString("FL_VIDEO"),
//							rs.getInt("CD_AUDIO_VIDEO")
//							)
//					);
					
		}
		
		return new Candidato();
	}
/**
 * Método para buscar um Candidato na tabela T_RBW_CANDIDATO, com base no seu Email e Senha
 * @param email Email do Candidato
 * @param senha Senha do Candidato
 * @return Retorna verdadeiro caso este login exista, e falso se não existir
 * @throws Exception
 */
	
	public Candidato getByLogin(String email, String senha) throws Exception{
		
		stmt = con.prepareStatement("SELECT * FROM T_RBW_CANDIDATO "
					+ "INNER JOIN T_RBW_VAGA "
					+ "ON T_RBW_CANDIDATO.NR_VAGA = T_RBW_VAGA.NR_VAGA "
					+ "INNER JOIN T_RBW_RECRUTADOR "
					+ "ON T_RBW_CANDIDATO.CD_RECRUTADOR = T_RBW_RECRUTADOR.CD_RECRUTADOR "
					+ "WHERE T_RBW_CANDIDATO.DS_EMAIL='" + email +  
					"' AND T_RBW_CANDIDATO.NM_SENHA='" + senha +"'"
				);
		

	
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Candidato(
					
					rs.getInt("CD_CANDIDATO"),
					rs.getString("NM_CANDIDATO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DT_NASCIMENTO"),
					new Vaga(
							rs.getInt("NR_VAGA"),
							rs.getString("NM_VAGA"),
							rs.getString("DS_VAGA"),
							rs.getDouble("VL_SALARIO")
							),
					rs.getInt("NR_MEDALHA"),
				    rs.getString("NR_CPF"),
					new Recrutador(
							rs.getInt("CD_RECRUTADOR"),
							rs.getString("NM_RECRUTADOR"),
							rs.getString("DS_EMAIL")
					));					
		}
		return new Candidato();
	}

	/**
	 * Método para buscar todas as linhas na tabela T_RBW_CANDIDATO.<br>
	 * Este método tem como função buscar e mostrar todos os candidatos existentes. 
	 * @return Retorna uma lista de objetos Candidato com todas as informações presentes no Banco de Dados.
	 * @author Eduardo Vinícius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Candidato
	 * @see br.com.nextstep.bo.CandidatoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public List<Candidato> getAll() throws Exception {

		stmt = con.prepareStatement("SELECT * FROM T_RBW_CANDIDATO "
				+ "INNER JOIN T_RBW_VAGA "
				+ "ON T_RBW_CANDIDATO.NR_VAGA = T_RBW_VAGA.NR_VAGA "
				+ "INNER JOIN T_RBW_RECRUTADOR "
				+ "ON T_RBW_CANDIDATO.CD_RECRUTADOR = T_RBW_RECRUTADOR.CD_RECRUTADOR");
	
		
		rs = stmt.executeQuery();
		
		List<Candidato> listaMedalhaZero = new ArrayList<Candidato>();
		List<Candidato> listaMedalhaUm = new ArrayList<Candidato>();
		List<Candidato> listaMedalhaDois = new ArrayList<Candidato>();

		
		while(rs.next()) {
			
			Candidato candidato = new Candidato(
					
					rs.getInt("CD_CANDIDATO"),
					rs.getString("NM_CANDIDATO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DT_NASCIMENTO"),
					new Vaga(
							rs.getInt("NR_VAGA"),
							rs.getString("NM_VAGA"),
							rs.getString("DS_VAGA"),
							rs.getDouble("VL_SALARIO")
							),
					rs.getInt("NR_MEDALHA"),
				    rs.getString("NR_CPF"),
					new Recrutador(
							rs.getInt("CD_RECRUTADOR"),
							rs.getString("NM_RECRUTADOR"),
							rs.getString("DS_EMAIL")
					));
			
//			Candidato candidato = new Candidato(
//					
//					rs.getInt("CD_CANDIDATO"),
//					rs.getString("NM_CANDIDATO"),
//					rs.getString("DS_EMAIL"),
//					rs.getString("NM_SENHA"),
//					rs.getString("DT_NASCIMENTO"),
//					new Vaga(
//							rs.getInt("NR_VAGA"),
//							rs.getString("NM_VAGA"),
//							rs.getString("DS_VAGA"),
//							rs.getDouble("VL_SALARIO")
//							),
//					rs.getInt("NR_MEDALHA"),
//				    rs.getString("NR_CPF"),
//					new Recrutador(
//							rs.getInt("CD_RECRUTADOR"),
//							rs.getString("NM_RECRUTADOR"),
//							rs.getString("DS_EMAIL")
//							),
//					new Chatbot(
//							rs.getInt("CD_CHATBOT"),
//							rs.getString("DS_RESPOSTA")
//							),
//					new AudioVideo(
//							rs.getString("FL_AUDIO"),
//							rs.getString("FL_VIDEO"),
//							rs.getInt("CD_AUDIO_VIDEO")
//							)
//					);
			
			if (candidato.getNumeroMedalha() == 0) {
				listaMedalhaZero.add(candidato);
			} else if (candidato.getNumeroMedalha() == 1) {
				listaMedalhaUm.add(candidato);
			} else if (candidato.getNumeroMedalha() == 2) {
				listaMedalhaDois.add(candidato);
			}
		}
		
		List<Candidato> listaCandidatos = new ArrayList<Candidato>();
		listaCandidatos.addAll(listaMedalhaDois);
		listaCandidatos.addAll(listaMedalhaUm);
		listaCandidatos.addAll(listaMedalhaZero);
		
		return listaCandidatos;
	}
	
}
