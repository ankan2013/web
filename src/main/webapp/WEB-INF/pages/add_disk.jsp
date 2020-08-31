<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление носителя</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Добавление носителя</b><br><br>
<form:form method="post" modelAttribute="disk" action="add_disk_request">
  <b>Название</b><br>
  <form:input path="name"/><br>
  <b>Тип</b><br>
  <form:input path="type"/><br>
  <b>Стоимость проката</b><br>
  <form:input path="price" value="0" required="true"/><br>
  <br>
  <form:button value="add_disk_request">Добавить</form:button>
</form:form>
</body>
</html>
