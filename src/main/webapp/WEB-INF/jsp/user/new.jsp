<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css"/>">
</head>
<body>
<div class="container">
    <%--@elvariable id="user" type="org.github.alahijani.zprojects.model.User"--%>
    <h2>Create User</h2>

    <form:form modelAttribute="user" action="/user/new" method="post">

        <p>
            <form:label path="fullName" for="fullName" cssErrorClass="error"><spring:message code="user.fullName"/></form:label><br/>
            <form:input path="fullName"/>
            <form:errors path="fullName"/>
        </p>
        
        <p>
            <form:label path="username" for="username" cssErrorClass="error"><spring:message code="user.username"/></form:label><br/>
            <form:input path="username"/>
            <form:errors path="username"/>
        </p>

        <p>
            <form:label path="password" for="password" cssErrorClass="error"><spring:message code="user.password"/></form:label><br/>
            <form:password path="password"/>
            <form:errors path="password"/>
        </p>

        <p>
            <form:label path="enabled" for="enabled" cssErrorClass="error"><spring:message code="user.enabled"/></form:label><br/>
            <form:checkbox path="enabled"/>
            <form:errors path="enabled"/>
        </p>

        <p>
            <form:label path="admin" for="admin" cssErrorClass="error"><spring:message code="user.admin"/></form:label><br/>
            <form:checkbox path="admin"/>
            <form:errors path="admin"/>
        </p>

        <input value="Create" type="submit">
    </form:form>

</div>
</body>
</html>