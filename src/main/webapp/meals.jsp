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
        <th >IDs</th>
        <th>dateTime</th>
        <th>description</th>
        <th>calories</th>
        <th colspan=2>Action</th>
    </tr>
    <c:forEach var="Meal" items="${Meals}">
        <tr style="color:<c:if test="${Meal.excess}"> red </c:if> <c:if test="${!Meal.excess}"> green </c:if>">
            <td >${Meal.id}</td>
            <td> <javatime:format pattern="yyyy-MM-dd HH:mm" value="${Meal.dateTime}"/></td>
            <td>${Meal.description}</td>
            <td>${Meal.calories}</td>
            <td><a href="meals?action=edit&Id=<c:out value="${Meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&Id=<c:out value="${Meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="meals?action=insert">Add meal</a></p>
</body>
</html>