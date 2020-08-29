<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список фильмов</title>
</head>
<body>
<a href="/web/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Информация</th>
            <th>Действие 1</th>
            <th>Действие 2</th>
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
                    <a href="/web/update_film?filmId=${film.filmId}">Изменить</a><br>
                </td>
                <td>
                    <a href="/web/delete_film?filmId=${film.filmId}">Удалить</a><br>
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
<a href="/web/add_film">Добавить фильм</a><br>

</body>
</html>
