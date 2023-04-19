<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@ page import="com.nhnacademy.springmvcstudent.item.Gender" %>
<%@page session="false"%>
<html>
<head>
    <title>학생-등록</title>
    <link rel="stylesheet" href="/resources/style.css"/>
    <meta charset="UTF-8"/>
</head>
<jsp:include page="/WEB-INF/view/login/loginInfo.jsp"/>
<body>
<c:choose>
    <c:when test="${empty student}">
        <c:set var="action" value="/student/register.do"/>
    </c:when>
    <c:otherwise>
        <c:set var="action" value="/student/update.do"/>
    </c:otherwise>
</c:choose>
<form:form method="post" action="${action}" modelAttribute="studentRequest">
    <table>
        <tbody>
        <tr>
            <th>ID</th>
            <td><form:input type="text" path="id" value="${student.id}"/></td>
        </tr>
        <tr>
            <th>이름</th>
            <td><form:input type="text" path="name" value="${student.name}"/></td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <form:radiobuttons path="gender" items="${Gender.values()}" itemLabel="name" itemValue="name"
                                  checked="${student.gender.name()}"/>
            </td>
        </tr>
        <tr>
            <th>나이</th>
            <td><form:input type="number" path="age" value="${student.age}"/></td>
        </tr>
        </tbody>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form:form>
</form>
</body>
</html>
