<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление клиента</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Добавление клиента</b><br><br>
<form:form method="post" modelAttribute="client" action="add_client_request">
  <b>Имя</b><br>
  <form:input path="name"/><br>
  <b>Почтовый адрес</b><br>
  <form:input path="email"/><br>
  <b>Телефон</b><br>
  <form:input path="phone"/><br>
  <br>
  <form:button value="add_сlient_request">Добавить</form:button>
</form:form>
</body>
</html>
