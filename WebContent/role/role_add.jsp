<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">角色新增</h1>
<form action="${pageContext.request.contextPath}/RoleServlet" method="post">
<input type="hidden" name="roleAction" value="add">
<input type="text" name="roleName" id="roleName" value="">
<br>
<input type="checkbox" name="menu" value="1">前台管理<br>
&emsp;<input type="checkbox" name="menu" value="3">订单管理<br>
&emsp;<input type="checkbox" name="menu" value="4">商品管理<br>
<input type="checkbox" name="menu" value="2">后台管理<br>
&emsp;<input type="checkbox" name="menu" value="5">用户管理<br>
&emsp;<input type="checkbox" name="menu" value="6">角色管理<br>
<input type="submit" value="提交">
</form>

</body>
</html>