<%@ page import="com.nhnacademy.flight.item.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Member</title>
</head>
<body>
<table border="1" style="margin: auto">
    <thead>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>City</td>
    </tr>
    </thead>
    <%
        List<Member> list = (List<Member>) request.getAttribute("list");

        for (Member member : list) {
    %>
    <tbody>
    <tr>
        <td><%=member.getId()%>
        </td>
        <td><%=member.getName()%>
        </td>
        <td><%=member.getCity()%>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<table border="1" style="margin: auto">
    <tr>
        <%
            int startPageNum = (int) request.getAttribute("startPageNum");

            for (int i = startPageNum; i < startPageNum + 10; i++) {
        %>
        <td><a href="/member?pageNum=<%=i%>"/><%=i%>
        </td>
        <%
            }
        %>
    </tr>
</table>
</body>
</html>
