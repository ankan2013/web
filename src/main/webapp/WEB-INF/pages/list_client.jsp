<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список клиентов</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Список клиентов</b><br><br>
<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>Имя</th>
            <th>Почтовый адрес</th>
            <th>Телефон</th>
            <th>Действие 1</th>
            <th>Действие 2</th>
        </tr>
        <c:forEach items="${list}" var="client">
            <tr>
                <td>
                    <c:out value="${client.name}"/>
                </td>
                <td>
                    <c:out value="${client.email}"/>
                </td>
                <td>
                    <c:out value="${client.phone}"/>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/update_client?clientId=${client.clientId}">Изменить</a><br>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/delete_client?clientId=${client.clientId}">Удалить</a><br>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${list.size() == 0}">
    <br>
    Список пуст
</c:if>
<br>
<a href="<%=request.getContextPath()%>/add_client">Добавить клиента</a><br>

</body>
</html>
