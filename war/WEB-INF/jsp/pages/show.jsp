<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Palestra GAE - Blog</title>
</head>
<body>
<h1>Blog</h1>

<h2>${artigo.titulo}</h2>

<p>${artigo.texto}</p>

<c:forEach items="${comentarioList}" var="comentario">
	<div>
	<p>${comentario.texto}</p>
	</div>
</c:forEach>

<form method="post" action="/${artigo.id}">
	<input type="text" name="comentario.texto" />
	<input type="submit" value="Comentar" />
</form>

</body>
</html>