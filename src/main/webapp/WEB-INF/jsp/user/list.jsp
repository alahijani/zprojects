<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <td>Full name</td>
                <td>Username</td>
                <td>Actions</td>
            </tr>
            </thead>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.fullName}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.id}"/></td>
                </tr>
            </c:forEach>
            <c:if test="${empty users}">
                <tr>
                    <td colspan="3" class="centered">No data.</td>
                </tr>
            </c:if>
        </table>

        <a href="<c:url value="/user/new"/>" class="button">Create...</a>
    </div>
</div>
</body>
</html>