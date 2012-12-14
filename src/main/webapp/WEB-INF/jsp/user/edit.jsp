<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fo" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css"/>">
</head>
<body>
<div class="container">
    <form:form modelAttribute="user" action="." method="post">
        <p>
            <form:label path="username" for="username"/>
            <fo:input path="username"/>
            <form:errors path="username"/>
        </p>

        <p>
            <form:label path="fullName" for="fullName"/>
            <fo:input path="fullName"/>
            <form:errors path="fullName"/>
        </p>

        <input value="Save" type="submit">
    </form:form>
</div>
</body>
</html>