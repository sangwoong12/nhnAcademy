<%@ page import="com.nhnacademy.notice_board.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:bundle basename="<%=locale%>">
    <%
        Integer count = (Integer) request.getServletContext().getAttribute("count");
        long visitorCount = (long) request.getServletContext().getAttribute("visitorsCount");
    %>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
    <header>
    <div>
        | <a href="/logout.do"><fmt:message key="logout"/></a> | <a><fmt:message key="user-count"/> : <%=count%></a> | <a><fmt:message key="visitors-count"/> : <%=visitorCount%></a> | <a href ="/change-lang.do?locale=message_ko">ko</a> : <a href ="/change-lang.do?locale=message_en">en</a> |
    </div>
    </header>
</body>
</fmt:bundle>
</html>
