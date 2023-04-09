<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="nhnmart.css">
    <title>카트</title>
</head>
<body>
<p>
<div>
    <table>
        <thead>
        <tr>
            <th>음식이름</th>
            <th>가격</th>
            <th>구매량</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="food" items="${buyList}">
            <tr>
                <td>${food.key.name}</td>
                <td>${food.key.price}</td>
                <td>${food.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>총가격: <c:out value="${totalPrice}" /></p>
    <a href="/">홈으로 돌아가기</a>
</div>
</body>
</html>