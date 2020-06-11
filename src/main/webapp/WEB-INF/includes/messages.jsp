<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="messages" scope="request" type="java.util.Set"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${messages != null && messages.size() != 0}">
    <div class="row mb-3">
        <div class="col">
            <div class=" alert alert-success m-0">
                <c:forEach var="message" items="${messages}">
                    <spring:message code="${message}"/><br>
                </c:forEach>
            </div>
        </div>
    </div>
</c:if>
