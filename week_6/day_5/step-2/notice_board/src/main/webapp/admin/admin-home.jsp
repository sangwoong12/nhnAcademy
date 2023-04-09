<%@ page import="com.nhnacademy.notice_board.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <link rel="stylesheet" href="/style.css">
    <title><fmt:message key="home"/></title>
</head>
<body>
<%@ include file="/header/header.jsp"%>
<div>
    <a href="/user-list.do"><fmt:message key="user-list"/></a></br>
    <a href="/user-add.do"><fmt:message key="add-user"/></a>
</div>
</fmt:bundle>
</body>
</html>
