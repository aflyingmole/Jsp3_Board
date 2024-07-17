<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-07-17
  Time: 오후 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>board/insertForm</title>
</head>
<body>
<h1>글등록하기</h1>
<form method="post" action="${pageContext.request.contextPath}/board/insert">
    작성자<br>
    <input type="text" name="writer"><br>
    제목<br>
    <input type="text" name="title"><br>
    내용<br>
    <textarea rows="5" cols="50" name="content"></textarea><br>
    <input type="submit" value="등록">
</form>
</body>
</html>
