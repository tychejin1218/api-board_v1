<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Spring Boot Application with JSP</title>

</head>

<body>

<h2>CUSTOM LOGOUT PAGE</h2>

<form method="post">
    <h3>Logout</h3>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit" class="btn">LogOut</button>
</form>
</body>


</html>