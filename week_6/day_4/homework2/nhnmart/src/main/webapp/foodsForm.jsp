<%@ page import="com.nhnacademy.nhnmart.item.FoodStand" %>
<%@ page import="com.nhnacademy.nhnmart.CookieUtils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
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
    <link rel="stylesheet" href="nhnmart.css">
    <title><fmt:message key="foodList"/></title>
</head>
<body>
<div>
    <table>
        <thead>
        <tr>
            <th><fmt:message key="foodName"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="Remaining"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="food" items="${foodStand}">
            <tr>
                <td><fmt:message key="${food.key.name}"/></td>
                <td>${food.key.price}</td>
                <td>${food.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method='post' action='/cart.do'>
        <c:forEach var="food" items="${foodStand}">
            <fmt:message key="${food.key.name}"/>
                <input type="number" name="${food.key.name}" required/></br>
        </c:forEach>
        <button type='submit'><fmt:message key="inBasket"/></button>
    </form>
</div>
</body>
</fmt:bundle>
</html>
