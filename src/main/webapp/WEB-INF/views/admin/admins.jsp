<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="../../includes/head.jsp"/>
<body>
<div class="container pt-2">
    <jsp:include page="../../includes/admin/menu.jsp"/>
    <jsp:include page="../../includes/messages.jsp"/>
    <div class="row mb-3">
        <div class="col">
            <a href="<c:url value="/admin/admin"/>" class="btn btn-outline-success">Dodaj administratora</a>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <table class="table">
                <thead class="thead-dark">
                    <th scope="col"><spring:message code="admin.email"/></th>
                    <th scope="col"><spring:message code="admin.firstname"/></th>
                    <th scope="col"><spring:message code="admin.lastname"/></th>
                    <th scope="col" class="w-15"></th>
                </thead>
                <c:forEach var="admin" items="${admins}">
                    <tr>
                        <td>${admin.email}</td>
                        <td>${admin.firstname}</td>
                        <td>${admin.lastname}</td>
                        <td class="text-right">
                            <a href="<c:url value="/admin/admin/${admin.id}"/>" class="badge badge-pill badge-primary"><spring:message code="app.edit"/></a>
                            <a href="<c:url value="/admin/delete/${admin.id}"/>" class="badge badge-pill badge-danger"><spring:message code="app.delete"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
