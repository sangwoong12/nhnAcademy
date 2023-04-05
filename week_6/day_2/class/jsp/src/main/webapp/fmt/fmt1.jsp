<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>fmt-다국어 메세지 처리</title>
</head>
<body>

<fmt:setLocale value="ko" />
<fmt:setBundle basename="message" var="message" />
<fmt:message key="hello" bundle="${message}" />
<fmt:bundle basename="message">
    i say hello, you say <fmt:message key="hello" />
</fmt:bundle>

</body>
</html>