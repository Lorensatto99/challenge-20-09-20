<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <%@ include file="./snippets/imports/libs-head.jsp" %>
  <title>Recruta B2W</title>
  <link rel="stylesheet" href="./css/ranking.css">


</head>

<body>

  <%@ include file="./snippets/header_candidato.jsp" %>
INN
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
            <th>Medalha</th>
          </tr>
     	<c:forEach var="cand" items="${listaCandidatos}" varStatus="id">
          <tr class="pos">
            <td>${id.count}</td>
            <td>${cand.nome}</td>
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
  <%@ include file="./snippets/imports/libs-footer.jsp"%>
  <script type="text/javascript" src="js/index.js"></script>
</body>

</html>