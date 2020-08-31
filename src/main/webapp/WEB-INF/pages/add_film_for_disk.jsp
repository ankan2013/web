<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление фильма</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">На главную</a>
<br><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<br>
<b>Добавление фильма на носитель ${disk.name} ${disk.type}</b><br><br>
<form:form method="post" modelAttribute="film_for_disk" action="add_film_for_disk_request">
  <b>Выберите фильм из списка</b><br>
  <br>
  <form:input type="hidden" path="diskId" value="${film_for_disk.diskId}"/>
  <form:select path="filmId">
    <c:forEach items="${list}" var="film">
      <form:option value="${film.filmId}" selected="true">
        ${film.name} ${film.info}
      </form:option>
    </c:forEach>
  </form:select><br>
  <br>
  <form:button value="add_film_for_disk_request">Добавить</form:button>
</form:form>
</body>
</html>
