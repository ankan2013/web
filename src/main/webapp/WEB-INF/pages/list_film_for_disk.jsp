<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список фильмов</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Список фильмов на носителе ${disk.name} ${disk.type}</b><br><br>
<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Информация</th>
            <th>Действие</th>
        </tr>
        <c:forEach items="${list}" var="film">
            <tr>
                <td>
                    <c:out value="${film.name}"/>
                </td>
                <td>
                    <c:out value="${film.info}"/>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/delete_film_for_disk?filmId=${film.filmId}&diskId=${disk.diskId}">Удалить</a><br>
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
<a href="<%=request.getContextPath()%>/add_film_for_disk?diskId=${disk.diskId}">Добавить фильм</a><br>

</body>
</html>
