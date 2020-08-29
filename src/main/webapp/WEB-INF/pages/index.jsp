<%@ page isELIgnored="false" %>
<%@ page language ="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Главная</title>
</head>
<body>

<a href="/web/list_client">Клиенты</a>
<a href="/web/list_disk?all=1">Носители</a>
<a href="/web/list_film">Фильмы</a>

<br>
<br>
<b>Список заказов</b>
<a href="/web/">Показать все</a>
<a href="/web/active">Показать активные</a>
<br><br>

<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>Клиент</th>
            <th>Диск</th>
            <th>Стоимость</th>
            <th>Дата взятия</th>
            <th>Дата возврата</th>
            <th>Возвращен</th>
            <th>Действие 1</th>
            <th>Действие 2</th>
            <th>Действие 3</th>
        </tr>
        <c:forEach items="${list}" var="ordr">
            <tr>
                <td>
                    <c:out value="${ordr.client.name}"/>
                    <c:out value="${ordr.client.email}"/>
                </td>
                <td>
                    <c:out value="${ordr.disk.name}"/>
                    <c:out value="${ordr.disk.type}"/>
                    <a href="/web/list_film_for_disk?diskId=${ordr.disk.diskId}">Список фильмов</a>
                </td>
                <td>
                    <c:out value="${ordr.disk.price}"/>
                </td>
                <td>
                    <c:out value="${ordr.requestTime}"/>
                </td>
                <td>
                    <c:out value="${ordr.returnTime}"/>
                </td>
                <td>
                    <c:out value="${ordr.isReturned}"/>
                </td>
                <td>
                    <a href="/web/update_ordr?ordrId=${ordr.ordrId}">Изменить</a><br>
                </td>
                <td>
                    <a href="/web/delete_ordr?ordrId=${ordr.ordrId}">Удалить</a><br>
                </td>
                <td>
                    <c:if test="${ordr.isReturned}">
                        <a href="/web/return_request?ordrId=${ordr.ordrId}&isReturned=0">Отменить возврат</a><br>
                    </c:if>
                    <c:if test="${not ordr.isReturned}">
                        <a href="/web/return_request?ordrId=${ordr.ordrId}&isReturned=1">Зафиксировать возврат</a><br>
                    </c:if>
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
<a href="/web/add_ordr">Новый заказ</a><br>

</body>
</html>
