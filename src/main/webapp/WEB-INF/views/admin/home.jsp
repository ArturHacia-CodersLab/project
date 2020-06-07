<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="../../includes/head.jsp"/>
<body class="container">
A to jest strona admina<br>
<spring:message code="currentAdmin.welcome"/> ${currentAdmin.firstname} ${currentAdmin.lastname}<br>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="<spring:message code="button.logout"/>">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
