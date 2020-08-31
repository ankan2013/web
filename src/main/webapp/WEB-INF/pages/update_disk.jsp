<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение носителя</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Обновление носителя</b><br><br>
<form:form method="post" modelAttribute="disk" action="update_disk_request">
  <form:hidden path="diskId"/>
  <b>Название</b><br>
  <form:input path="name" value="${disk.name}"/><br>
  <b>Тип</b><br>
  <form:input path="type" value="${disk.type}"/><br>
  <b>Стоимость проката</b><br>
  <form:input path="price" value="${disk.price}" required="true"/><br>
  <br>
  <form:button value="update_disk_request">Изменить</form:button>
</form:form>
</body>
</html>
