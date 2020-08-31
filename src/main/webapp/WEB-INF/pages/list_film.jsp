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
<b>Список фильмов</b><br><br>
<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Информация</th>
            <th>Действие 1</th>
            <th>Действие 2</th>
            <th>Действие 3</th>
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
                    <a href="<%=request.getContextPath()%>/update_film?filmId=${film.filmId}">Изменить</a><br>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/delete_film?filmId=${film.filmId}">Удалить</a><br>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/list_disk_for_film?filmId=${film.filmId}&all=1">Показать носители</a><br>
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
<a href="<%=request.getContextPath()%>/add_film">Добавить фильм</a><br>

</body>
</html>
