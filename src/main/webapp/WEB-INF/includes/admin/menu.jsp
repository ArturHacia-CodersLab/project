<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="menuC" class="row justify-content-between">
    <div class="col text-left pl-0">
        <a href="<c:url value="/admin/admins"/>" class="btn btn-outline-secondary">Administratorzy</a>
        <a href="" class="btn btn-outline-secondary">Konfiguracja</a>
    </div>
    <div class="col text-right pr-0">
        <spring:message code="currentAdmin.welcome"/> ${currentAdmin.firstname} ${currentAdmin.lastname}
        <form action="<c:url value="/logout"/>" method="post" class="d-inline">
            <input class="btn btn-outline-danger" type="submit" value="<spring:message code="button.logout"/>">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</div>
