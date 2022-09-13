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

    <h1>file upload</h1>
    <form method="post" action="/upload" enctype="multipart/form-data">
        <input type="file" name="uploadFile" multiple="multiple">
        <input type="submit">
    </form>

</body>


</html>