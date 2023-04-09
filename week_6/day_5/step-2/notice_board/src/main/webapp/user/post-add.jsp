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
<head>
  <link rel="stylesheet" href="/style.css">
</head>
<body>
<fmt:bundle basename="<%=locale%>">
<%@ include file="/header/header.jsp"%>
<br>
<div>
  <form method="post" action="/post-add.do">
    <p><fmt:message key="title"/> </p>
    <input type="text" style="width: 422px" name="title"/>
    <p><fmt:message key="content"/> </p>
    <textarea rows="4" cols="50" name="content">

    </textarea>
    </br>
    <input type="submit" name="<fmt:message key="submit"/>">
  </form>
</div>
</body>
</fmt:bundle>
</html>
