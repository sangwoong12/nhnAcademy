<%@ page import="com.nhnacademy.nhnmart.CookieUtils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<head>
    <link rel="stylesheet" href="/nhnmart.css">
    <title><fmt:message key="cart"/></title>
</head>
<body>
<div>
    <a href="/cart.do"><fmt:message key="View_order_information"/></a>
</div>
</body>
</fmt:bundle>
</html>
