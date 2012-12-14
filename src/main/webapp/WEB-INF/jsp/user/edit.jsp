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
    <c:choose>
        <c:when test="${empty user.id}">
            <h2>Create User</h2>
        </c:when>
        <c:otherwise>
            <h2>Edit User</h2>
        </c:otherwise>
    </c:choose>

    <form:form modelAttribute="user" action="." method="post">
        <p>
            <form:label path="username" for="username" cssErrorClass="error"><spring:message code="user.username"/></form:label><br/>
            <form:input path="username"/>
            <form:errors path="username"/>
        </p>

        <p>
            <form:label path="fullName" for="fullName" cssErrorClass="error"><spring:message code="user.fullName"/></form:label><br/>
            <form:input path="fullName"/>
            <form:errors path="fullName"/>
        </p>

        <input value="Save" type="submit">
    </form:form>

    <%--<span><c:out value="${globalMessage}" escapeXml="true"/></span>--%>
</div>
</body>
</html>