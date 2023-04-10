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
    <p><fmt:message key="user-edit"/> </p>
    <form method="POST" action="/edit-user-info.do">
    <table>
        <thead>
        <tr>
            <th><fmt:message key="password"/></th>
            <th><fmt:message key="name"/></th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td><input type="text" name="password" value="${user.password}"></td>
                <td><input type="text" name="name" value="${user.name}"></td>
            </tr>
        </tbody>
    </table>
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="profile" value="${user.profileFileName}">
        <input type="hidden" name="auth" value="${user.auth}">

    <div>
        <input type="submit" value="<fmt:message key="edit"/>">
    </div>
    </form>
</div>
</fmt:bundle>
</body>
</html>
