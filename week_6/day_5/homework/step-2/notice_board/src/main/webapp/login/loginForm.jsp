<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.nhnacademy.notice_board.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title><fmt:message key="login-form"/> </title>
  <link rel="stylesheet" href="/style.css">
</head>
<body>
<div>
<form method="post" action="/login.do">
  <table>
    <tr>
      <th><fmt:message key="id"/></th>
      <td><input type="text" name="id" required></td>
    </tr>
    <tr>
      <th><fmt:message key="password"/></th>
      <td><input type="text" name="password" required></td>
    </tr>
  </table>
  <input type="submit" name="<fmt:message key="submit"/>">
</form>
</div>
</body>
</fmt:bundle>
</html>
