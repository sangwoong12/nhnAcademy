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
        <title><fmt:message key="post-list"/> </title>
        <link rel="stylesheet" href="/style.css">
    </head>
    <body>
    <%@ include file="/header/header.jsp"%>
    <div>
        <p><fmt:message key="post-list"/> </p>
        <table>
            <thead>
            <tr>
                <th class="title"><fmt:message key="title"/></th>
                <th class="user"><fmt:message key="writerUserId"/></th>
                <th class="view-count"><fmt:message key="viewCount"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="post" items="${postList}">
                <tr>
                    <td><a href = "/post-view.do?id=${post.id}">${post.title}</a></td>
                    <td>${post.writerUserId}</td>
                    <td>${post.viewCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href='/home.do'><fmt:message key="home"/></a>
    </div>
    </body>
</fmt:bundle>
</html>
