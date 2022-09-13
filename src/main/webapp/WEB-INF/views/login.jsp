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

<h2>CUSTOM LOGIN PAGE</h2>

<c:if test="${param.error != null}">
<div>
    <h2>Invalid Username or password</h2>
    <h2>${param.error}</h2>
</div>
</c:if>

<c:if test="${param.logout != null}">
    <div>
        <h2>You have been logged out.</h2>
    </div>
</c:if>

<form method="post">
    <p>
        <label for="username">Username</label>
        <input type="text" name="username" id="username"/>
    </p>

    <p>
        <label for="password">Password</label>
        <input type="password" name="password" id="password"/>
    </p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit" class="btn">Log in</button>
</form>
</body>


</html>