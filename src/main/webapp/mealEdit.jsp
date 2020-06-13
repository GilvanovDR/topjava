<%--
  ~ GilvanovDR (c) 2020.
  --%>

<%--
  ~ GilvanovDR (c) 2020.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Add new meal</title>
</head>
<body>
<form method="POST" action='meals'>
    ID : <input type="text" readonly="readonly" name="Id"
                value="<c:out value="${meal.id}" />"/> <br/>
    dateTime : <input
        type="datetime-local" name="dateTime"
        value="<c:out value="${meal.dateTime}"/>"/> <br/>
    description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/> <br/>
    calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />"/> <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>