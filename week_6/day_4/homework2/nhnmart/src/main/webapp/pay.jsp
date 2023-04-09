<%@ page import="com.nhnacademy.nhnmart.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%
        String locale;
        Cookie cookie = CookieUtils.getCookie(request, "locale");
        if(cookie == null){
            locale = "message_ko";
        }else{
            locale = cookie.getValue();
        }
    %>
    <fmt:bundle basename="<%=locale%>">
    <link rel="stylesheet" href="/nhnmart.css">
    <title><fmt:message key="basket"/></title>
</head>
<body>
<div>
    <p><fmt:message key="Amount_of_payment"/>: ${totalPrice}</p>
    <p><fmt:message key="balance"/>: ${balance}</p>
    <a href="/home.do"><fmt:message key="home"/></a>
</div>
</body>
</fmt:bundle>
</html>