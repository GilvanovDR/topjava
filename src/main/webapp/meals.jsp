<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%--
  ~ GilvanovDR (c) 2020.
  --%>

<%--
  ~ GilvanovDR (c) 2020.
  --%>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<table border="1">
    <caption>Meals</caption>
    <tr>
        <th>dateTime</th>
        <th>description</th>
        <th>calories</th>
        <th>IDs</th>
    </tr>
    <c:forEach var="Meal" items="${Meals}">
        <tr style="color:<c:if test="${Meal.excess}"> red </c:if> <c:if test="${!Meal.excess}"> green </c:if>">
            <td> <javatime:format pattern="yyyy-MM-dd HH:mm" value="${Meal.dateTime}"/></td>
            <td>${Meal.description}</td>
            <td>${Meal.calories}</td>
            <td>${Meal.id}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>