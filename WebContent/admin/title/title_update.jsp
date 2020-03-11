<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>题库修改</title>
<style type="text/css">
input{
  margin: 10px 5px;
}
</style>
</head>
<body>
<form action="${pageContext.request.contextPath}/TitleServlet?titleAction=update" method="post">
<h1>修改当前题目信息</h1>

<input type="hidden" name="titleId" id="titleId" value="${requestScope.title.titleId}" >
题目${requestScope.title.titleId}:&nbsp;<input type="text" name="titleName" value="${requestScope.title.titleName}" style="width: 300px;">

<br>
<c:forEach items="${requestScope.itemList }" var="item">
选项${item.itemId}:&nbsp;<input type="text" name="itemNames" value="${item.itemName}">&emsp;
得分:&nbsp;<input type="text" name="scopes" value="${item.scope}"><br>
</c:forEach>
<input type="submit" value="提交">&emsp;<a href="${pageContext.request.contextPath}/TitleServlet?titleAction=list">返回题目列表</a>
</form>
</body>
</html>