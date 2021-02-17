<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./snippets/imports/libs-head.jsp"%>
<title>Recruta B2W</title>
<link rel="stylesheet" href="./css/recrutador_main.css">
<link rel="stylesheet" href="./css/ranking.css">
</head>
<body>

	<%@ include file="./snippets/header_recrutador.jsp"%>

<div class="general">
    <div class="ranking">
      <div class="ranking__title">
        <h1 class="ranking__title--content">Pontuação Geral</h1>
      </div>
      
      <div class="ranking__table">
        <table class="tabela">
          <tr class="col">
            <th>Posição</th>
            <th>Nome</th>
            <th>Data de Nascimento</th>
            <th>CPF</th>
            <th>Nome da vaga</th>
            <th>Descrição da vaga</th>
            <th>Salário</th>
            <th>Email</th>
            <th>Medalha</th>
          </tr>
     	<c:forEach var="cand" items="${listaCandidatos}" varStatus="id">
          <tr class="pos">
            <td>${id.count}</td>
            <td>${cand.nome}</td>
            <td>${cand.dataNascimento}</td>
            <td>${cand.cpf}</td>
            <td>${cand.vaga.nomeVaga}</td>
            <td>${cand.vaga.descVaga}</td>
            <td>${cand.vaga.salario}</td>
            <td>${cand.email}</td>
            <c:choose> 
            	<c:when test="${cand.numeroMedalha == 0}"> 
            		<td><img src="./img/medalha-bronze.png"></td>
            	</c:when>
	            <c:when test="${cand.numeroMedalha == 1}"> 
            		<td><img src="./img/medalha-prata.png"></td>
            	</c:when>
         		<c:when test="${cand.numeroMedalha == 2}"> 
            		<td><img src="./img/medalha-ouro.png"></td>
            	</c:when>
            </c:choose>
          </tr>
         </c:forEach>
        </table>
      </div>
    </div>
  </div>
  
	<%@ include file="./snippets/footer.html" %>
	<%@ include file="./snippets/imports/libs-footer.jsp" %>
</body>

</html>