<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>服务器错误页面</title>
</head>
<body>
<h1 align="center">服务器异常，正在竭力维修</h1>
<h1 align="center"><%=exception %></h1>
<input type="button" value="返回" onclick="window.history.back()">
<input>
</body>
</html>