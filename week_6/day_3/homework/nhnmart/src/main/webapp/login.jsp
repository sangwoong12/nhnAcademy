<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false"%>
<html>
<head>
    <link rel="stylesheet" href="nhnmart.css">
    <title>로그인</title>
</head>
<body>
<form method="post" action="/login">
    <div>
        <input type="text" name="id" placeholder="아이디" required/></br>
        <input type="password" name="pwd" placeholder="비밀번호" required/></br>
        <button type="submit">로그인</button>
    </div>
</form>
</body>
</html>