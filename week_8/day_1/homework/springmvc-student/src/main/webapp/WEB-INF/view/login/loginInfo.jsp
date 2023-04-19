<%@ page import="com.nhnacademy.springmvcstudent.item.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@page session="false"%>
<html>
<head>
    <title>Title</title>
</head>
<script>
    function logout() {
        document.getElementById("logoutForm").submit();
    }
</script>
<link rel="stylesheet" href="/resources/style.css">

<body>
<div>
    <%
        User user = (User)request.getSession().getAttribute("user");
    %>
    <p>아이디 :<%=user.getUserId()%> 이름 :<%=user.getUserName()%></p>
    <button type="button" onclick="logout()">로그아웃</button>
    <form id="logoutForm" method="post" action="/logout"></form>
</div>
</body>
</html>
