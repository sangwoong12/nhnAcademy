<%@ page import="com.nhnacademy.nhnmart.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page isErrorPage="true" %>
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
    <title><fmt:message key="errorPage"/></title>
    <link rel="stylesheet" href="/nhnmart.css.css" />
</head>
<body>
<div>
<table>
    <tbody>
    <tr>
        <th><fmt:message key="status_code"/></th>
        <td>${status_code}</td>
    </tr>
    <tr>
        <th><fmt:message key="exception_type"/></th>
        <td>${exception_type}</td>
    </tr>
    <tr>
        <th><fmt:message key="message"/></th>
        <td>${message}</td>
    </tr>
    <tr>
        <th><fmt:message key="exception"/></th>
        <td>${exception}</td>
    </tr>
    <tr>
        <th><fmt:message key="request_uri"/></th>
        <td>${request_uri}</td>
    </tr>
    </tbody>
</table>
</div>
</body>
</fmt:bundle>
</html>