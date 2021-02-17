package br.com.nextstep.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;

import br.com.nextstep.beans.AudioVideo;
import br.com.nextstep.beans.Candidato;
import br.com.nextstep.beans.Chatbot;
import br.com.nextstep.bo.AudioVideoBO;
import br.com.nextstep.bo.CandidatoBO;
import br.com.nextstep.bo.ChatbotBO;
import br.com.nextstep.bo.RecrutadorBO;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/ranking", "/login","/logout", "/paginacao", "/chat", "/audioVideo"})
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ServletContext ctx;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			switch (request.getRequestURI()) {
		
				case "/RecrutaB2W/login":
					realizaLogin(request, response);
					break;
				case "/RecrutaB2W/paginacao":
					paginacao(request, response);
					break;
				case "/RecrutaB2W/chat":
					realizaChatbot(request,response);
					break;
				case "/RecrutaB2W/audioVideo":
					System.out.println("Passo audioVideo");
					enviaAudioVideo(request, response);
					break;
				default:
					response.sendRedirect("index.jsp");
			} 
			

		} catch (Exception e) {
			e.printStackTrace();
		}
			
	
	}
	
	private void enviaAudioVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AudioVideo av = new AudioVideo();
		String path = request.getParameter("url");
		System.out.println("Path: " + path);
		av.setPathVideo(path);
		
		if(AudioVideoBO.novoAudioVideo(av) == "Cadastrado") {
			request.getRequestDispatcher("./WEB-INF/candidato_video.jsp").forward(request, response);
			request.setAttribute("msg", "Enviado!!");
		}
		

	}

	private void paginacao(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getParameter("pag").equals("candidato_ranking.jsp")) {
			mostraCandidatos(request, response, "candidato_ranking.jsp");
		} else if (request.getParameter("pag").equals("recrutador_ranking.jsp")) {
			mostraCandidatos(request, response, "recrutador_ranking.jsp");
		} else if (request.getParameter("pag").equals("recrutador_candidatos.jsp")) {
			mostraCandidatos(request, response, "recrutador_candidatos.jsp");
		} else if (request.getParameter("pag").equals("candidato_chatbot.jsp")) {
			// parÃ¢metro pergunta foi mandado vazio aqui para que o nó inicial de bem-vindo seja mostrado ao clicar na aba Chatbot, caso contrário ele não seria mostrado
			response.sendRedirect("chat?resposta=");	
		} else if (request.getParameter("pag").equals("recrutador_chatbot.jsp")) {
			mostraChatbot(request, response);	

		} else {
		request.getRequestDispatcher("./WEB-INF/" + request.getParameter("pag")).forward(request, response);		
		}
	}

	private void realizaChatbot(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Resposta do candidato
		String resposta = request.getParameter("resposta");
		// Pergunta do chatbot
		String pergunta = recebeWatson(resposta);
		request.setAttribute("pergunta", pergunta);
		request.getRequestDispatcher("./WEB-INF/candidato_chatbot.jsp").forward(request, response);
	}
	
	private String recebeWatson(String resposta) throws Exception {
		
		ctx = getServletContext();

		IamAuthenticator authenticator = new IamAuthenticator("QjJRIw8chC1WJ5Ae99A3neY0QzQeq_0L829-Mqaqc9ab");
		Assistant assistant = new Assistant("2020-11-31", authenticator);
		assistant.setServiceUrl("https://api.us-south.assistant.watson.cloud.ibm.com/instances/214ecc19-d036-4afb-a3d2-a0efeabca4bd");
		
		String chatbotSessionId = null;
		
		Chatbot chatbot = null;
		
		if (ctx.getAttribute("ChatbotSessionId") == null) {
			CreateSessionOptions sessionOptions = new CreateSessionOptions.Builder("ebfbc50b-c9f5-455f-8513-6fb54cba070a").build();

			
			SessionResponse sessionResponse = assistant.createSession(sessionOptions).execute().getResult();
					
			ctx.setAttribute("ChatbotSessionId", sessionResponse.getSessionId());
			
			chatbotSessionId = sessionResponse.getSessionId();
			
			chatbot = new Chatbot();
			
		} else {
			chatbotSessionId = (String) ctx.getAttribute("ChatbotSessionId");
			chatbot = (Chatbot) ctx.getAttribute("chatbot");
		}
			
		MessageInput input = new MessageInput.Builder()
				  .messageType("text")
				  .text(resposta)
				  .build();
		
		MessageOptions MsgOptions = new MessageOptions.Builder("ebfbc50b-c9f5-455f-8513-6fb54cba070a", chatbotSessionId)
				  .input(input)
				  .build();

		MessageResponse msgResponse = assistant.message(MsgOptions).execute().getResult();
		
		String novaPergunta = msgResponse.getOutput().getGeneric().get(0).text();

		String ultimaPergunta = (String) ctx.getAttribute("ultimaPergunta");
		
		// caso tenha um input do usuário, ou seja, quando ele responder
		// o nó de bem-vindo
		if (!resposta.equals("")) {	
			// adiciona no dicionário a Ãºltima pergunta feita, junto
			// com a sua resposta
			chatbot.addRespostas(ultimaPergunta, resposta);
		}
		
		// Depois de adicionada no mapa,
		// seta a Ãºltima pergunta como a pergunta atual (nova)
		ctx.setAttribute("ultimaPergunta", novaPergunta);
		
		ctx.setAttribute("chatbot", chatbot);
		
		if (novaPergunta.equals("Onde você mora atualmente, e quanto tempo levaria para chegar ao escritório da B2W?")) {
			int linhasAlteradas = ChatbotBO.novoChatbot(chatbot);
			
			return novaPergunta + " Agora a conversa serao salva no banco de dados." + "Linhas alteradas: " + linhasAlteradas;
		}

		return novaPergunta;
		
	}
	


	private void mostraChatbot(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// o objeto chatbot já está na memória, na verdade
		// não precisaria buscar no banco de dados, mas
		// estou forçando a busca pois vamos precisar buscar ele
		// no momento de criar a sessão. Isto é temporário
		
		Chatbot chatbotDoBancoDeDados = ChatbotBO.pesquisarChatbot(3);
		
		request.setAttribute("chatbot", chatbotDoBancoDeDados);
		request.getRequestDispatcher("./WEB-INF/recrutador_chatbot.jsp").forward(request,response);
		
	}

	private void realizaLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		
		
		String email = request.getParameter("cpmail");
		String senha = request.getParameter("cppass");
		
		 if (email.substring(0,3).equals("rc_")) {
			 
			 if(RecrutadorBO.mostraLogin(email, senha).getId() != 0) {
				 
				HttpSession session = request.getSession();
				
				session.setAttribute("recrutador", RecrutadorBO.mostraLogin(email, senha));
				session.setMaxInactiveInterval(3600);
				 
				request.getRequestDispatcher("./WEB-INF/recrutador_index.jsp").forward(request,response);	 
			 }
			 
			 else {
				 request.setAttribute("msgErro", "Login inválido!");
				 request.getRequestDispatcher("./login.jsp").forward(request, response);
			 }
	        

		 } 
		 
		 else {
			 if(CandidatoBO.mostraLogin(email, senha).getId() != 0) {
				 
				HttpSession session = request.getSession();
				
				session.setAttribute("candidato", CandidatoBO.mostraLogin(email, senha));

				session.setMaxInactiveInterval(3600);
				 
				 request.getRequestDispatcher("./WEB-INF/candidato_index.jsp").forward(request,response);	 
			 }
			 
			 else {
				 request.setAttribute("msgErro", "Login inválido!");
				 request.getRequestDispatcher("./login.jsp").forward(request, response);
			 }
		 }
		
		 
	}

	private void mostraCandidatos(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
		
		List<Candidato> listaCandidatos = CandidatoBO.mostraCandidato();
		
		if (listaCandidatos.isEmpty() == true) {
			request.getRequestDispatcher("./WEB-INF/" + path).forward(request, response);
		} else {
			request.setAttribute("listaCandidatos", listaCandidatos);
			request.getRequestDispatcher("./WEB-INF/" + path).forward(request, response);
		}
	}
		
	
	}
