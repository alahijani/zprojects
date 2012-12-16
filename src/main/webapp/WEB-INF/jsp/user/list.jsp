<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--@elvariable id="users" type="java.util.List<org.github.alahijani.zprojects.model.User>"--%>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css"/>">
</head>
<body>
<div class="container">
    <div class="entities">
        <table class="grid">
            <thead>
            <tr>
                <td><spring:message code="user.fullName"/></td>
                <td><spring:message code="user.username"/></td>
                <td><spring:message code="user.enabled"/></td>
                <td><spring:message code="user.admin"/></td>
                <td>Actions</td>
            </tr>
            </thead>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.fullName}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.enabled}"/></td>
                    <td><c:out value="${user.admin}"/></td>
                    <td><a href="<c:url value="/user/${user.id}"/>" class="button">Details</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty users}">
                <tr>
                    <td colspan="5" class="centered">No data.</td>
                </tr>
            </c:if>
        </table>

        <sec:authorize access="hasRole('admin')">
            <a href="<c:url value="/user/new"/>" class="button">Create...</a>
        </sec:authorize>
    </div>
</div>
</body>
</html>