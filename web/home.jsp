<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-07-17
  Time: 오후 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home.jsp</title>
</head>
<body>
<ul>
    <li><a href="${pageContext.request.contextPath}/board/insert">글등록</a></li>
    <li><a href="${pageContext.request.contextPath}/board/list">글목록</a></li>
</ul>
</body>
</html>
