<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
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
    <%
        String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/module_05";
        String USER = "root";
        String PWD = "";


        Class.forName(DRIVER_NAME);
        Connection connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Passenger");

        while (resultSet.next()) {
            int id = resultSet.getInt("PassengerNo");
            String name = resultSet.getString("PassengerName");
            int grade = resultSet.getInt("Grade");
            int age = resultSet.getInt("Age");
    %>
    <tr>
        <td><a href="view.jsp?id=<%=id%>"/><%=name%>
        </td>
        <td><%=age%>
        </td>
        <td><%=grade%>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>