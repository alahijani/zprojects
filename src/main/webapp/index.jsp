<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h2><spring:message code="welcome"/></h2>

<sec:authorize access="hasRole('admin')">
    <a href="<c:url value="/user/"/>"><spring:message code="user.manage"/></a>
</sec:authorize>

<a href="<c:url value="/logout"/>"><spring:message code="logout"/></a>

</body>
</html>
