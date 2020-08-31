<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение клиента</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Обновление клиента</b><br><br>
<form:form method="post" modelAttribute="client" action="update_client_request">
  <form:hidden path="clientId"/>
  <b>Имя</b><br>
  <form:input path="name" value="${client.name}"/><br>
  <b>Почтовый адрес</b><br>
  <form:input path="email" value="${client.email}"/><br>
  <b>Телефон</b><br>
  <form:input path="phone" value="${client.phone}"/><br>
  <br>
  <form:button value="update_client_request">Изменить</form:button>
</form:form>
</body>
</html>
