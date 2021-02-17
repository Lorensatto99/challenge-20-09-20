<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>Chatbot</title>
 <link rel="stylesheet" type="text/css" href="./css/global.css">
 <link rel="stylesheet" type="text/css" href="./css/chatbot.css" />
 <link href="./img/B2W_Digital_logo.png" rel="icon" type="image/x-icon" />
 
 <%@ include file="./snippets/imports/libs-head.jsp"%>
</head>
<body>

	<%@ include file="./snippets/header_candidato.jsp"%>
	
	<main>
		<div class="general">

			<form action ="chat" class="chatbot">
				<div class="conversa">
					<p>${pergunta}</p>
				</div>
				<input type="text" id="pergunta" name="resposta" class="campo"
					placeholder="Digite aqui"></input>
				
				
			</form>
		</div>
	</main>
	
	<%@ include file="./snippets/footer.html" %>
	<%@ include file="./snippets/imports/libs-footer.jsp" %>
  
	<script type="text/javascript" src="./js/chatbot2.js"></script> 
	
  	
</body>
</html>