<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib tagdir="/WEB-INF/tags/task" prefix="task" %>
<%--@elvariable id="user" type="org.github.alahijani.zprojects.model.User"--%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css"/>">
</head>
<body>
<h1><spring:message code="welcome"/></h1>

<div class="user">
    <div class="fullName"><c:out value="${user.fullName}"/></div>
</div>

<sec:authorize access="hasRole('admin')">
    <a href="<c:url value="/user/"/>"><spring:message code="user.manage"/></a>
    <a href="<c:url value="/project/"/>"><spring:message code="project.manage"/></a>
</sec:authorize>

<h2><spring:message code="task.yours"/></h2>
<task:list/>
<p/>
<%--<a href="<c:url value="/user/${user.id}/"/>"><spring:message code="page.user"/></a>--%>
<a href="<c:url value="/logout"/>"><spring:message code="logout"/></a>

</body>
</html>
