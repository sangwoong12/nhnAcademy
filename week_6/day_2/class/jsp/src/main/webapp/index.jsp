<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title><%= "title" %></title>
  </head>
  <body>
    <%= "Hello, World!" %>
    <br/>
    <% out.write("Hello, World!"); %>
    <p>
      <%= 17 * 5 %>
    </p>
    <p>
      <%=request.getParameter("name")%>
    </p>

    <p>
      <% String name="marco"; %>
    </p>
    <p>
      <%=name%>
    </p>

    <ol>
      <li>
        <a href="counter/sessionCounter.jsp">sessionCounter.jsp</a>
      </li>
      <li>
        <a href="counter/globalCounter.jsp">globalCounter.jsp</a>
      </li>
      <li>
        <a href="gugudan.jsp">구구단</a>
      </li>
      <li>
        <a href="implicit.jsp">내장객체</a>
      </li>
      <li>
        <a href="/scope/pageContext.jsp?type=include">include</a>
      </li>
      <li>
        <a href="/scope/pageContext.jsp?type=forward">forward</a>
      </li>
      <li>
        <a href="/beans/bean.jsp">bean - user</a>
      </li>
      <li>
        <a href="/beans/beautify.html">beautify html</a>
      </li>
      <li>
        <a href="/el/el1.jsp">el - array, list, map test</a>
      </li>
      <li>
        <a href="/el/el2.jsp">el - 기본문법 - 연산자</a>
      </li>
      <li>
        <a href="/jstl/jstl1.jsp">jstl - 일반</a>
      </li>
      <li>
        <a href="/jstl/jstl2.jsp?email=marco@nhnacademy.com">jstl - 조건문</a>
      </li>

      <li>
        <a href="/jstl/notice">jstl - 반복 - 공지사항</a>
      </li>

      <li>
        <a href="/jstl/jstl3.jsp">jstl - redirect </a>
      </li>

      <li>
        <a href="/fn/fn1.jsp">function tag library</a>
      </li>

      <li>
        <a href="/fmt/fmt1.jsp">fmt-다국어 메세지 처리</a>
      </li>

      <li>
        <a href="/fmt/fmt2.jsp">fmt - 숫자 포맷팅</a>
      </li>

      <li>
        <a href="/fmt/fmt3.jsp">fmt - 날짜 포맷팅</a>
      </li>

      <li>
        <a href="/error/content.jsp">예외처리</a>
      </li>

      <li>
        <a href="/jstl/notice">notice list</a>
      </li>

    </ol>
  </body>
</html>