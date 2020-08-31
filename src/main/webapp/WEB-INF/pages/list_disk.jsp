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
<b>Список носителей</b><br><br>
<a href="<%=request.getContextPath()%>/list_disk?all=1">Показать все</a>
<a href="<%=request.getContextPath()%>/list_disk?all=0">Показать носители не в прокате</a>
<br><br>
<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Тип</th>
            <th>Стоимость проката</th>
            <th>Действие 1</th>
            <th>Действие 2</th>
            <th>Действие 3</th>
        </tr>
        <c:forEach items="${list}" var="disk">
            <tr>
                <td>
                    <c:out value="${disk.name}"/>
                </td>
                <td>
                    <c:out value="${disk.type}"/>
                </td>
                <td>
                    <c:out value="${disk.price}"/>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/update_disk?diskId=${disk.diskId}">Изменить</a><br>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/delete_disk?diskId=${disk.diskId}">Удалить</a><br>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/list_film_for_disk?diskId=${disk.diskId}">Список фильмов</a><br>
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
<a href="<%=request.getContextPath()%>/add_disk">Добавить носитель</a><br>

</body>
</html>
