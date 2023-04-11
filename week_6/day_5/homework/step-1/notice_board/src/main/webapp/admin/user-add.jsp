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
  <p><fmt:message key="user-list"/> </p>
  <form method="POST" action="/profile" enctype="multipart/form-data">
    <table>
      <thead>
      <tr>
        <th><fmt:message key="id"/></th>
        <th><fmt:message key="password"/></th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td><input type="text" name="id" required></td>
        <td><input type="text" name="password" required></td>
      </tr>
      </tbody>
    </table>
    <table>
    <thead>
    <tr>
      <th><fmt:message key="name"/></th>
      <th><fmt:message key="profile"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td><input type="text" name="name" required></td>
      <td><input type="file" name="profile" accept="image/jpeg"></td>
    </tr>
    </tbody>
    </table>
      <input type="submit" value="<fmt:message key="add-user"/>">
  </form>
  <a href='/home.do'><fmt:message key="home"/></a>
</div>
</fmt:bundle>
</body>
</html>
