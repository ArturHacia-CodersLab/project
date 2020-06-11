<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="../../includes/head.jsp"/>
<body>
<div class="container pt-2">
    <jsp:include page="../../includes/admin/menu.jsp"/>
    <div class="row justify-content-center mt-5">
        <div class="col-6">
            <form:form method="post" modelAttribute="admin">
                <form:hidden path="id"/>
                <c:if test="${mode != 'pass'}">
                    <div class="form-group">
                        <form:label path="email"><spring:message code="admin.email"/>:</form:label>
                        <form:input path="email" cssClass="form-control" />
                        <form:errors path="email" element="div" cssClass="alert alert-danger"/>
                        <c:if test="${mode == 'edit'}">
                            <form:errors element="div" cssClass="alert alert-danger"/>
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${mode != 'edit'}">
                    <div class="form-group">
                        <form:label path="password"><spring:message code="admin.password"/>:</form:label>
                        <form:password path="password" cssClass="form-control"/>
                        <form:errors path="password" element="div" cssClass="alert alert-danger"/>
                    </div>
                    <div class="form-group">
                        <form:label path="confirmPassword"><spring:message code="admin.confirm-password"/>:</form:label>
                        <form:password path="confirmPassword" cssClass="form-control"/>
                        <form:errors element="div" cssClass="alert alert-danger"/>
                    </div>
                </c:if>
                <c:if test="${mode != 'pass'}">
                    <div class="form-group">
                        <form:label path="firstname"><spring:message code="admin.firstname"/>:</form:label>
                        <form:input path="firstname" cssClass="form-control"/>
                        <form:errors path="firstname" element="div" class="alert alert-danger"/>
                    </div>
                    <div class="form-group">
                        <form:label path="lastname"><spring:message code="admin.lastname"/>:</form:label>
                        <form:input path="lastname" cssClass="form-control"/>
                        <form:errors path="lastname" element="div" cssClass="alert alert-danger"/>
                    </div>
                </c:if>
                <a href="<c:url value="/admin/admins"/>"
                   class="btn btn-outline-danger float-left"><spring:message code="app.cancel"/></a>
                <input type="submit" value="<spring:message code="app.save"/>"
                       class="btn btn-outline-success float-right">
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
