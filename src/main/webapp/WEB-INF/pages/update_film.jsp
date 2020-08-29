<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение фильма</title>
</head>
<body>
<a href="/web/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<form:form method="post" modelAttribute="film" action="update_film_request">
  <form:hidden path="filmId"/>
  <b>Название</b><br>
  <form:input path="name" value="${film.name}"/><br>
  <b>Информация</b><br>
  <form:input path="info" value="${film.email}"/><br>
  <br>
  <form:button value="update_film_request">Изменить</form:button>
</form:form>
</body>
</html>
