<%@ page import="com.nhnacademy.nhnmart.CookieUtils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false"%>
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
    <title>로그인</title>
</head>
<body>
<form method="post" action="/login.do">
    <div>
        <input type="text" name="id" placeholder=<fmt:message key="id"/> required/></br>
        <input type="password" name="pwd" placeholder=<fmt:message key="password"/>  required/></br>
        <button type="submit"><fmt:message key="login"/></button>
    </div>
</form>
</fmt:bundle>
</body>
</html>