<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>globalCounter</title>
</head>
<body>
    <%!
        private long counter=0;
        private long increaseCounter(){
            return ++counter;
        }
        public void jspInit(){
            counter=100;
        }
    %>

    <h1>counter:<%=increaseCounter()%></h1>
</body>
</html>
