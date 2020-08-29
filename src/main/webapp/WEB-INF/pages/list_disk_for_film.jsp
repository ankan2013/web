<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список дисков</title>
</head>
<body>
<a href="/web/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<a href="/web/list_disk_for_film?filmId=${filmId}&all=1">Показать все</a>
<a href="/web/list_disk_for_film?filmId=${filmId}&all=0">Показать свободные</a>
<br><br>
<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Тип</th>
            <th>Стоимость проката</th>
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
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${list.size() == 0}">
    <br>
    Список пуст
</c:if>
<br>

</body>
</html>
