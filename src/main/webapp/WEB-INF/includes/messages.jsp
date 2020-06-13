<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${messages != null && messages.size() != 0}">
    <div class="row mb-3">
        <div class="col">
            <c:forEach var="message" items="${messages}">
                <div class=" alert alert-${message.type} m-0">
                    <spring:message code="${message.text}"/><br>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>
