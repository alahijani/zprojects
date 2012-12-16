<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--@elvariable id="tasks" type="java.util.List<org.github.alahijani.zprojects.model.Task>"--%>
<%--@elvariable id="project" type="org.github.alahijani.zprojects.model.Project"--%>
<div class="entities">

    <c:if test="${tasks != null}">
        <table class="grid">
            <thead>
            <tr>
                <td><spring:message code="task.title"/></td>
                <td><spring:message code="task.description"/></td>
                <td><spring:message code="task.assignee"/></td>
                <td>Actions</td>
            </tr>
            </thead>

            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td><c:out value="${task.title}"/></td>
                    <td><c:out value="${task.description}"/></td>
                        <%--todo can be too long!--%>
                    <td><c:out value="${task.assignee.fullName}"/></td>
                    <td><a href="<c:url value="/project/${project.id}/task/${task.id}"/>" class="button">Details</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty tasks}">
                <tr>
                    <td colspan="5" class="centered">No data.</td>
                </tr>
            </c:if>
        </table>
    </c:if>

    <sec:authorize access="hasRole('admin')">
        <a href="<c:url value="/project/${project.id}/task/new"/>" class="button">Create Task...</a>
    </sec:authorize>
</div>
