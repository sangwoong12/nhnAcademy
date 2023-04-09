<%@ page import="com.nhnacademy.notice_board.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<%@ include file="/header/header.jsp"%>

<div>
    <h1><fmt:message key="post"/> </h1>
</div>
<div>
    <fmt:message key="title"/> : ${post.title}</br>
</div>
<div>
    <fmt:message key="writerUserId"/> : <a href="/user-info.do?id=${post.writerUserId}">${post.writerUserId}</a>
</div>
<div>
${post.content}
</div>
<c:set var="id" value="${id}"/>
<c:set var="writerUserId" value="${post.writerUserId}"/>
<c:if test="${id eq writerUserId}">
    <div><a href="/edit-post.do?id=${post.id}"><fmt:message key="edit"/></a>  :  <a href="/delete-post.do?id=${post.id}"><fmt:message key="delete"/></a></div>
</c:if>
<div>
<a href="/post-list.do"><fmt:message key="post-list"/></a></br>
</div>
</fmt:bundle>
</body>
</html>
