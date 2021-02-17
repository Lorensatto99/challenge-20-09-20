<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
<%@ include file="./snippets/imports/libs-head.jsp"%>
<title>Recruta B2W</title>
<link rel="stylesheet" href="./css/video.css">
<link rel="stylesheet" href="./css/drag-drop.css">

</head>

<body>

	<%@ include file="./snippets/header_candidato.jsp"%>

	<div class="general">
		<div class="video">
			<div class="texto">
				<div class="txt-1">
					<h2>Vídeo Introdutório</h2>
					<p>Nos conte mais sobre você e da pessoa que você é:</p>
					<p>Você poderá nos mandar um vídeo se descrevendo dizendo
						coisas como:</p>
					<li>
						<ul>Descrever o momento mais importante da sua vida;
						</ul>
						<ul>Dizer quais são as 3 coisas mais importantes pra você;
						</ul>
						<ul></ul>
					</li>
				</div>
				<div class="txt-2">
					<p>
						* O vídeo deve estar nos <u>formatos de mp4, mkv, avi</u> com a <u>resolução
							mínima de 1280x720(HD)</u> e com o som audível, <u>tamanho
							máximo de 100mb.</u>
					</p>
				</div>
			</div>
			<div class="video__container">
				<form action="audioVideo" method="get">
					<div class="drop-zone">

						Select a file to upload:<input type="file" id="demo" size="50" name="url">
					</div>
					<button type="submit" class="btn-enviar" action="audioVideo"
						method="post" onclick="myFunction()">Enviar</button>
				</form>
			</div>
			${msg}
		</div>
	</div>


	<%@ include file="./snippets/footer.html"%>


	<%@ include file="./snippets/imports/libs-footer.jsp"%>
	<script type="text/javascript" src="./js/video.js"></script>
	<script type="text/javascript" src="./js/index.js"></script>
</body>

</html>