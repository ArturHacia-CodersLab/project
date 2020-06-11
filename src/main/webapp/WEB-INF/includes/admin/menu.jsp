<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="currentAdmin" scope="request" type="pl.madld.cms.admin.security.CurrentAdmin"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="menuC" class="row justify-content-between mb-4">
    <div class="col text-left">
        <a href="<c:url value="/admin/admins"/>" class="btn btn-outline-secondary"><spring:message code="menu.admins"/></a>
        <a href="" class="btn btn-outline-secondary"><spring:message code="menu.configuration"/></a>
    </div>
    <div class="col text-right">
        <spring:message code="current-admin.welcome"/> ${currentAdmin.admin.firstname} ${currentAdmin.admin.lastname}
        <form action="<c:url value="/logout"/>" method="post" class="d-inline">
            <input class="btn btn-outline-warning" type="submit" value="<spring:message code="app.logout"/>">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</div>
