<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление заказа</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Добавление заказа</b><br><br>
<form:form method="post" modelAttribute="ordr_form" action="add_ordr_request">
  <b>Выберите клиента из списка</b><br>
  <form:select path="clientId">
    <c:forEach items="${client_list}" var="client">
      <form:option value="${client.clientId}" selected="true">
        ${client.name} ${client.email}
      </form:option>
    </c:forEach>
  </form:select><br>
  <br>
  <b>Выберите диск из списка</b><br>
  <form:select path="diskId">
    <c:forEach items="${disk_list}" var="disk">
      <form:option value="${disk.diskId}" selected="true">
        ${disk.name} ${disk.type}
      </form:option>
    </c:forEach>
  </form:select><br>
  <br>
  <b>Введите дату взятия в формате YYYY-MM-DD</b><br>
  <form:input path="requestTime"/><br>
  <b>Введите дату возврата в формате YYYY-MM-DD</b><br>
  <form:input path="returnTime"/><br>
  <br>
  <form:button value="add_ordr_request">Добавить</form:button>
</form:form>
</body>
</html>
