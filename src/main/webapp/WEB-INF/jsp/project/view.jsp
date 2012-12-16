<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags/task" prefix="task" %>
<html>
<head>
    <title>Manage Projects</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css"/>">
</head>
<body>
<div class="container">
    <%--@elvariable id="project" type="org.github.alahijani.zprojects.model.Project"--%>

    <div class="project">
        <h2 class="title"><c:out value="${project.title}"/></h2>

        <div class="code"><c:out value="${project.code}"/></div>
        <div class="description"><c:out value="${project.description}"/></div>
    </div>

    <sec:authorize access="hasRole('admin')">
        <h2 class="edit">Edit Project</h2>

        <div>
            <form:form modelAttribute="project" action="/project/${project.id}" method="post">

                <p>
                    <form:label path="title" for="title" cssErrorClass="error"><spring:message
                            code="project.title"/></form:label><br/>
                    <form:input path="title"/>
                    <form:errors path="title"/>
                </p>

                <p>
                    <form:label path="code" for="code" cssErrorClass="error"><spring:message
                            code="project.code"/></form:label><br/>
                    <form:input path="code"/>
                    <form:errors path="code"/>
                </p>

                <p>
                    <form:label path="description" for="description" cssErrorClass="error"><spring:message
                            code="project.description"/></form:label><br/>
                    <form:textarea path="description"/>
                    <form:errors path="description"/>
                </p>

                <input value="Save Changes" type="submit">
            </form:form>
        </div>
    </sec:authorize>

    <task:list/>

    <%--<span><c:out value="${globalMessage}" escapeXml="true"/></span>--%>
</div>
</body>
</html>