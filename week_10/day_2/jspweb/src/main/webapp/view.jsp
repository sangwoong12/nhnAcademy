<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %><%--
  Created by IntelliJ IDEA.
  User: sangwoong
  Date: 2023/05/02
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    String id = request.getParameter("id");
    String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/module_05";
    String USER = "root";
    String PWD = "";


    Class.forName(DRIVER_NAME);
    Connection connection = DriverManager.getConnection(URL, USER, PWD);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from Passenger WHERE Passenger.PassengerNo = " + id);
    resultSet.next();
%>
<body>
<table border="1">
    <thead>
    <tr>
        <td>Name</td>
        <td>age</td>
        <td>grade</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%=resultSet.getString("PassengerName")%>
        </td>
        <td><%=resultSet.getString("Age")%>
        </td>
        <td><%=resultSet.getString("Grade")%>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
