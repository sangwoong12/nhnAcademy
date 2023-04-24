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
    <title><fmt:message key="edit"/></title>
</head>
<body>
<%@ include file="/header/header.jsp"%>
<div>
    <p><fmt:message key="user-info"/> </p>
    <table>
        <thead>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="profile"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${user.name}</td>
            <td><img src="/profile?id=${user.id}"/></td>
        </tr>
        </tbody>
    </table>
    <a href="/post-list.do"><fmt:message key="post-list"/></a></br>
</div>
</fmt:bundle>
</body>
</html>
