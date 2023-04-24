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
  <p><fmt:message key="user-info"/></p>
</div>
<div><fmt:message key="name"/> : ${user.name}</div>
<div><fmt:message key="id"/> : ${user.id}</div>
<div><fmt:message key="password"/> : ${user.password}</div>
<div><fmt:message key="profile"/></div>
<div><img src="/profile?id=${user.id}"/></div>
<div><a href='/home.do'><fmt:message key="home"/></a></div>
</fmt:bundle>
</body>
</html>
