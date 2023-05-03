<%@ page import="com.nhnacademy.flight.item.Boarding" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Boarding</title>
</head>
<body>
<table border="1" style="margin: auto">
    <thead>
    <tr>
        <td>ReservedDate</td>
        <td>Departure</td>
        <td>arrival</td>
        <td>price</td>
        <td>flightDate</td>
        <td>kindOfAirCraft</td>
        <td>airline</td>
    </tr>
    </thead>
    <tbody>
    <%
        List<Boarding> list = (List<Boarding>) request.getAttribute("list");

        for (Boarding boarding : list) {
    %>
    <tr>
        <td><%=boarding.getReservedDate()%>
        </td>
        <td><%=boarding.getDeparture()%>
        </td>
        <td><%=boarding.getArrival()%>
        </td>
        <td><%=boarding.getPrice()%>
        </td>
        <td><%=boarding.getFlightDate()%>
        </td>
        <td><%=boarding.getKindOfAirCraft()%>
        </td>
        <td><%=boarding.getAirline()%>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
