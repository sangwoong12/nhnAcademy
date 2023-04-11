<%@ page import="com.nhnacademy.notice_board.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title><fmt:message key="main"/> </title>
        <link rel="stylesheet" href="/style.css">
    </head>
<body>
<%@ include file="/header/header.jsp"%>
<div>
    <p><fmt:message key="user-list"/> </p>
    <table>
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="edit-user-information"/></th>
            <th><fmt:message key="delete-user"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><a href = "/user-view.do?id=${user.id}">${user.id}</a></td>
                <td>${user.name}</td>
                <td><a href = "/edit-user-info.do?id=${user.id}"><fmt:message key="edit"/></a></td>
                <td><a href = "/delete-user.do?id=${user.id}"><fmt:message key="delete"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href='/home.do'><fmt:message key="home"/></a>
</div>
</body>
</fmt:bundle>
</html>
