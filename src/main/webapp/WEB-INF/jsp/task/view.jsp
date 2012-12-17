<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Manage Tasks</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css"/>">
</head>
<body>
<div class="container">
    <%--@elvariable id="task" type="org.github.alahijani.zprojects.model.Task"--%>
    <%--@elvariable id="allUsers" type="java.util.Map<java.lang.String, org.github.alahijani.zprojects.model.User>"--%>

    <div class="task">
        <h2 class="title"><c:out value="${task.title}"/></h2>

        <div class="project">
            <spring:message code="task.project"/>:
            <a href="<c:url
                    value="/project/${task.project.id}"/>"><c:out
                    value="${task.project.title}"/></a>
        </div>
        <c:if test="${!empty task.assignee}">
            <div class="assignee">
                <spring:message code="task.assignee"/>:
                <a href="<c:url
                        value="/user/${task.assignee.id}"/>"><c:out
                        value="${task.assignee.fullName}"/></a>
            </div>
        </c:if>
        <div class="description"><c:out value="${task.description}"/></div>
    </div>

    <sec:authorize access="hasRole('admin')">
        <h2 class="edit">Edit Task</h2>

        <div>
            <form:form modelAttribute="task" action="/project/${task.project.id}/task/${task.id}" method="post">

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
                        <form:option value="${null}" label="--- Select ---"/>
                        <form:options items="${allUsers}"/>
                    </form:select>
                    <form:errors path="assignee"/>
                </p>

                <input value="Save Changes" type="submit">
            </form:form>
        </div>
    </sec:authorize>

    <%-- this is only for UI. actual authorization is performed in the form action --%>
    <c:if test="${pageContext.request.remoteUser == task.assignee.username}">
        <form:form modelAttribute="task" action="/project/${task.project.id}/task/${task.id}/assignee" method="post">
            <p>
                <form:label path="assignee" for="assignee" cssErrorClass="error"><spring:message
                        code="task.assignee"/></form:label><br/>
                <form:select path="assignee">
                    <form:option value="${null}" label="--- Select ---"/>
                    <form:options items="${allUsers}"/>
                </form:select>
                <form:errors path="assignee"/>
            </p>

            <input value="Save Changes" type="submit">
        </form:form>
    </c:if>

        <a href="${pageContext.request.servletPath}/"
    <%--<span><c:out value="${globalMessage}" escapeXml="true"/></span>--%>
</div>
</body>
</html>