<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Manage Tasks</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css"/>">
</head>
<body>
<div class="container">
    <%--@elvariable id="task" type="org.github.alahijani.zprojects.model.Task"--%>
    <h2>Create Task</h2>

    <form:form modelAttribute="task" action="/project/${task.project.id}/task/new" method="post">

        <p>
            <form:label path="title" for="title" cssErrorClass="error"><spring:message
                    code="task.title"/></form:label><br/>
            <form:input path="title"/>
            <form:errors path="title"/>
        </p>

        <p>
            <form:label path="description" for="description" cssErrorClass="error"><spring:message
                    code="task.description"/></form:label><br/>
            <form:textarea path="description"/>
            <form:errors path="description"/>
        </p>

        <p>
            <form:label path="assignee" for="assignee" cssErrorClass="error"><spring:message
                    code="task.assignee"/></form:label><br/>
            <form:select path="assignee">
                <form:options path="assignee"/>
            </form:select>
            <form:errors path="assignee"/>
        </p>

        <input value="Create" type="submit">
    </form:form>

</div>
</body>
</html>