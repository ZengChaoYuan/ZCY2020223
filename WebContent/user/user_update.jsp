
<%@page import="com.cyzy.bean.Role"%>
<%@page import="java.util.List"%>
<%@page import="com.cyzy.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户修改</title>
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
<% String userId=request.getParameter("userId"); %>
<h1 align="center">用户修改</h1>
<form action="http://localhost:8080/JF190902/UserServlet?userAction=update" method="post" onsubmit="return checkForm()">
<input type="hidden" name="userId" id="userId" value="${requestScope.user.userId }">
用户名: <input type="text" name="userName" id="userName" value="${requestScope.user.userName}"><br>
密码: <input type="password" name="password" id="password" value="${requestScope.user.password}"><br>
真实姓名: <input type="text" name="realName" id="realName" value="${requestScope.user.realName}"><br>
性别: <input type="radio" name="sex" value="1" ${requestScope.user.sex==1?"checked":""}>男
<input type="radio" name="sex" value="0" ${requestScope.user.sex==0?"checked":""}>女
<br>
出生日期:<input type="date" name="birthday" id="birthday" value="${requestScope.user.birthday}"><br>
请选择角色:<select name="roleId">
<c:forEach items="${requestScope.roleList }" var="role">
<option ${role.roleId==user.roleId?"selected":""} value="${role.roleId }">${role.roleName }</option>
</c:forEach>
</select>
<br>
<input type="submit" value="提交">&emsp;
<a href="http://localhost:8080/JF190902/UserServlet?userAction=list">返回用户列表</a>
</form>

<script type="text/javascript">
function checkForm() {
	var userName=document.getElementById("userName");
	var password=document.getElementById("password");
	var realName=document.getElementById("realName");
	var birthday=document.getElementById("birthday");
	if(userName.value==""){
		alert("用户名不得为空!");
		userName.focus();
		return false;
	}else if(password.value==""){
		alert("密码不得为空!");
		password.focus();
		return false;
	}else if(realName.value==""){
		alert("真实姓名不得为空!");
		realName.focus();
		return false;
	}else if(birthday.value==""){
		alert("出生日期不得为空!");
		birthday.focus();
		return false;
	}
	return true;
}

</script>
</body>
</html>