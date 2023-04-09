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
</head>
<body>
<%@ include file="/header/header.jsp"%>
<br>
</div>
<p><fmt:message key="edit"/> </p>

<form method="post" action="/edit-post.do">
  <div>
    <input type="hidden" name="id" value="${post.id}">
    <p><fmt:message key="title"/></p></br>
    <input type="text"  style="width: 422px" name="title" value="${post.title}"></br>
    <p><fmt:message key="content"/></p></br>
    <textarea rows="4" cols="50" name="content">
        ${post.content}
    </textarea></br>
    <input type="submit" value="<fmt:message key="edit"/>">
  </div>
</form>
<div>
  <a href='/home.do'><fmt:message key="home"/></a>
</div>
</fmt:bundle>
</body>
</html>
