<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<form action="users" method="post">
    <p><input name="user" type="radio" value="1" checked> ADMIN1</p>
    <p><input name="user" type="radio" value="2"> USER2</p>
    <p><input type="submit" value="Выбрать"></p>
</form>
</body>
</html>