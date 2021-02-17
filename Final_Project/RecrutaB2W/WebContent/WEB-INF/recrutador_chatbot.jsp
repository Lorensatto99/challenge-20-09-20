<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<!DOCTYPE html>
<html>
<head>
<%@ include file="./snippets/imports/libs-head.jsp"%>

<link rel="stylesheet" href="./css/chatbot-recrutador.css">

<title>Recruta B2W</title>
</head>
<body>

	<%@ include file="./snippets/header_recrutador.jsp"%>
	
	<main class="general">
		<section class="content-infos">
			<div class="content-infos__text">
				<div class="content-infos__text--visible">
					<h2>Respostas do(a) ${candidato.nome}</h2>
					
					 <c:forEach var="resposta" items="${chatbot.respostas}" varStatus="id">
						
						<p><strong>Pergunta</strong>: ${resposta.key}</p><br/>
						<p><strong>Resposta</strong>: ${resposta.value}</p><br/>
					
			        </c:forEach>

				</div>
			</div>
			
		</section>
	</main>
	<%@ include file="./snippets/footer.html" %>
	<%@ include file="./snippets/imports/libs-footer.jsp" %>

</body>

</html>