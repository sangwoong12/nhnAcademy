<%@ page import="com.nhnacademy.nhnmart.CookieUtils" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
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
    <title><fmt:message key="index"/></title>
</head>
<body>
<div>
<br>
    <a href ="/change-lang.do?locale=message_ko">ko</a> : <a href ="/change-lang.do?locale=message_en">en</a></br>
    <a href="/foods.do"><fmt:message key="foodStand"/></a></br>
    <a href="/cart.do"><fmt:message key="basket"/></a></br>
    <p>${money}</p>
</div>
</body>
</fmt:bundle>
</html>