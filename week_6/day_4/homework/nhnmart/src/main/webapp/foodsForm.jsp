<%@ page import="com.nhnacademy.nhnmart.item.FoodStand" %><%--
  Created by IntelliJ IDEA.
  User: sangwoong
  Date: 2023/04/04
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="nhnmart.css">
    <title>음식 리스트</title>
</head>
<body>
<div>
    <table>
        <thead>
        <tr>
            <th>음식이름</th>
            <th>가격</th>
            <th>남은 수량</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="food" items="${foodStand}">
            <tr>
                <td>${food.key.name}</td>
                <td>${food.key.price}</td>
                <td>${food.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method='post' action='/cart.do'>
        <c:forEach var="food" items="${foodStand}">
            ${food.key.name}
                <input type="number" name="${food.key.name}" required/></br>
        </c:forEach>
        <button type='submit'>장바구니에 담기</button>
    </form>
</div>
</body>
</html>
