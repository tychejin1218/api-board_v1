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

    <h1>upload file 목록</h1>
    <c:forEach var="file" items="${files}">
    <ul>
        <li><span> <c:out value="${file.fileName}"></c:out></span></li>
        <img src="/loadImage?fullName=${file.fullName}"/>

    </ul>
    </c:forEach>
</body>


</html>