<%@ page import="com.nhnacademy.nhnmart.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
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
<head>
    <link rel="stylesheet" href="/nhnmart.css">
    <title>카트</title>
    <script>
        function updateTotalPrice() {
            var totalPrice = 0;
            var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
            for (var i = 0; i < checkboxes.length; i++) {
                var price = checkboxes[i].value;
                var quantity = checkboxes[i].parentNode.nextElementSibling.textContent;
                totalPrice += parseInt(price) * parseInt(quantity);
            }
            document.querySelector('#totalPrice').textContent = totalPrice;
        }
    </script>
</head>
<body>
<p>
<div>
    <form action="/pay.do" method="post">
    <table>
        <thead>
        <tr>
            <th><fmt:message key="foodName"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="amount"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="food" items="${buyList}">
            <tr>
                <td><input type="checkbox" name="${food.key.name}" value="${food.value}" onclick="updateTotalPrice()"><fmt:message key="${food.key.name}"/></td>
                <td>${food.key.price}</td>
                <td>${food.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <p><fmt:message key="totalPrice"/>: <span id="totalPrice">0</span></p>
        <input type="submit" value="<fmt:message key="payment"/>">
    </form>
</div>
</fmt:bundle>
</body>
</html>