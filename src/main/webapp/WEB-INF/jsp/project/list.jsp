<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--@elvariable id="projects" type="java.util.List<org.github.alahijani.zprojects.model.Project>"--%>
<html>
<head>
    <title>Manage Projects</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css"/>">
</head>
<body>
<div class="container">
    <div class="entities">
        <table class="grid">
            <thead>
            <tr>
                <td><spring:message code="project.title"/></td>
                <td><spring:message code="project.code"/></td>
                <td><spring:message code="project.description"/></td>
                <td>Actions</td>
            </tr>
            </thead>

            <c:forEach var="project" items="${projects}">
                <tr>
                    <td><c:out value="${project.title}"/></td>
                    <td><c:out value="${project.code}"/></td>
                    <td><c:out value="${project.description}"/></td>    <%--todo can be too long!--%>
                    <td><a href="<c:url value="/project/${project.id}"/>" class="button">Details</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty projects}">
                <tr>
                    <td colspan="4" class="centered">No data.</td>
                </tr>
            </c:if>
        </table>

        <sec:authorize access="hasRole('admin')">
            <a href="<c:url value="/project/new"/>" class="button">Create Project...</a>
        </sec:authorize>
    </div>
</div>
</body>
</html>