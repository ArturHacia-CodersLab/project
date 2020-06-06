<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1><spring:message code="app.title"/></h1>
Hello :)<br>
${user}<br>
${user.firstname} ${user.lastname}
</body>
</html>