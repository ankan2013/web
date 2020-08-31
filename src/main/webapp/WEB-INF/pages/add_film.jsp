<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление фильма</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Добавление фильма</b><br><br>
<form:form method="post" modelAttribute="film" action="add_film_request">
  <b>Название</b><br>
  <form:input path="name"/><br>
  <b>Информация</b><br>
  <form:input path="info"/><br>
  <br>
  <form:button value="add_film_request">Добавить</form:button>
</form:form>
</body>
</html>
