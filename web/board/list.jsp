<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-07-18
  Time: 오전 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" width="600">
    <tr>
        <th>글번호</th>
        <th>작성자</th>
        <th>제목</th>
        <th>내용</th>
        <th>등록일</th>
    </tr>
    <c:forEach var="vo" items="${list}">
        <tr>
            <td>${vo.num}</td>
            <td>${vo.writer}</td>
            <td>${vo.title}</td>
            <td>${vo.content}</td>
            <td>${vo.regdate}</td>
        </tr>
    </c:forEach>
</table>
<div>
    <!-- 이전 페이지 링크 -->
    <c:if test="${startPage > 10}">
        <a href="${pageContext.request.contextPath}/board/list?pageNum=${startPage-1}&field=${field}&keyword=${keyword}">이전</a>
    </c:if>

    <c:forEach var="num" begin="${startPage}" end="${endPage}">
        <c:choose>
            <c:when test="${pageNum == num}">
                <span style="color:red">${num}</span>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/board/list?pageNum=${num}&field=${field}&keyword=${keyword}">
                    <span style="color:gray">${num}</span>
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <!-- 다음페이지 링크 -->
    <c:if test="${endPage < pageCount}">
        <a href="${pageContext.request.contextPath}/board/list?pageNum=${endPage+1}&field=${field}&keyword=${keyword}">다음</a>
    </c:if>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/board/list">
        <select name="field">
            <option value="writer" <c:if test="${field == 'writer'}">selected</c:if>>작성자</option>
            <option value="title" <c:if test="${field == 'title'}">selected</c:if>>제목</option>
            <option value="content" <c:if test="${field == 'content'}">selected</c:if>>내용</option>
        </select>
        <input type="text" name="keyword" value="${keyword}">
        <input type="submit" value="검색">
    </form>
</div>
<a href="${pageContext.request.contextPath}/board/list">전체글보기</a> | <a href="${pageContext.request.contextPath}/home.jsp">홈가기</a>
</div>
</body>
</html>
