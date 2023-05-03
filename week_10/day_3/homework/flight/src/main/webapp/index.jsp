<%@ page import="com.nhnacademy.flight.item.Passenger" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Passenger</title>
</head>
<body>
<table border="1" style="margin: auto">
    <thead>
    <tr>
        <td>Name</td>
        <td>Age</td>
        <td>Grade</td>
    </tr>
    </thead>
    <tbody>
    <%
        List<Passenger> list = (List<Passenger>) request.getAttribute("list");

        for (Passenger passenger : list) {
    %>
    <tr>
        <td><a href="/boarding?id=<%=passenger.getId()%>"/><%=passenger.getName()%>
        </td>
        <td><%=passenger.getAge()%>
        </td>
        <td><%=passenger.getGrade()%>
        </td>
    </tr>
    </tbody>
    <%
        }
    %>
</table>
<p align="center"><a href="/member"/>additional homework [Members]</p>
</body>
</html>