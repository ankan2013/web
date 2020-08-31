<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение заказа</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Обновление заказа</b><br><br>
<form:form method="post" modelAttribute="ordr_form" action="update_ordr_request">
  <form:input type="hidden" path="ordrId" value="${ordr_form.ordrId}"/>
  <b>Клиент</b><br>
  <form:select path="clientId">
    <c:forEach items="${client_list}" var="client">
      <form:option value="${client.clientId}" selected="true">
        ${client.name} ${client.email}
      </form:option>
    </c:forEach>
  </form:select><br>
  <br>
  <b>Диск</b><br>
  <form:select path="diskId">
    <c:forEach items="${disk_list}" var="disk">
      <form:option value="${disk.diskId}" selected="true">
        ${disk.name} ${disk.type}
      </form:option>
    </c:forEach>
  </form:select><br>
  <br>
  <b>Дата взятия в формате YYYY-MM-DD</b><br>
  <form:input path="requestTime" value="${ordr.requestTime}"/><br>
  <b>Дата возврата в формате YYYY-MM-DD</b><br>
  <form:input path="returnTime" value="${ordr.returnTime}"/><br>
  <br>
  <form:button value="update_ordr_request">Изменить</form:button>
</form:form>
</body>
</html>
