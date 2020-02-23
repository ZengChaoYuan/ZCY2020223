<%@page import="com.cyzy.bean.Role"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增用户</title>
<style type="text/css">
form{
 width:300px;
 margin: 0 auto;
}
form input,form select{
 margin: 10px;
}

</style>
</head>
<body>
<h1 align="center">新增用户</h1>
<form action="http://localhost:8080/JF190902/UserServlet?userAction=add" method="post">
<input type="hidden" name="userId" id="userId" >
用户名: <input type="text" name="userName" id="userName" placeholder="请输入用户名" value=""><br>
密码: <input type="password" name="password" id="password" placeholder="请输入密码" value=""><br>
真实姓名: <input type="text" name="realName" id="realName" placeholder="请输入真实姓名" value=""><br>
性别: <input type="radio" name="sex" value="1">男
<input type="radio" name="sex" value="0">女
<br>
出生日期:<input type="date" name="birthday" id="birthday" value=""><br>
用户角色:

<select name="roleId">
<c:forEach items="${requestScope.roleList }" var="role">
<option value="${role.roleId }">${role.roleName}</option>

</c:forEach>
</select>

<br>
<input type="submit" value="提交">&emsp;<input type="reset" value="重置"><a href="http://localhost:8080/JF190902/UserServlet?userAction=list">返回用户列表</a>
</form>
</body>

</html>