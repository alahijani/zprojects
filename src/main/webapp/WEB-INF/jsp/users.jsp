<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Manage Users</title>
</head>
<body>
<table>
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
</table>
</body>
</html>