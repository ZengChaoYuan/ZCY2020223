<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增题目</title>
<style type="text/css">
input{
  margin: 10px 5px;
}
</style>
</head>
<body>
<form action="${pageContext.request.contextPath}/TitleServlet?titleAction=add" method="post">
<h1>新增题目</h1>
题目名称：<input type="text" name="titleName" id="titleName"><br>
选项：<input type="text" name="itemName" id="itemName"> &emsp;得分：<input type="text" name="scope" id="scope"><br>
选项：<input type="text" name="itemName" id="itemName"> &emsp;得分：<input type="text" name="scope" id="scope"><br>
选项：<input type="text" name="itemName" id="itemName"> &emsp;得分：<input type="text" name="scope" id="scope"><br>
选项：<input type="text" name="itemName" id="itemName"> &emsp;得分：<input type="text" name="scope" id="scope"><br>
<input type="submit"  value="提交">&emsp;<a href="${pageContext.request.contextPath}/TitleServlet?titleAction=list">返回题目列表</a>
</form>
</body>
</html>