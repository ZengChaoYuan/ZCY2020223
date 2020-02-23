<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<style type="text/css">
form{
 width: 300px;
 margin: 0 auto;
}
form input{
 margin: 8px;
}
form a{
margin-left: 200px;
}

</style>
</head>
<body>
 <h1 align="center">后台用户注册</h1>
 <form>
  用户名:<input type="text" name="userName" placeholder="请输入用户名"><br>
 密码:<input type="password" name="password" placeholder="请输入密码"><br>
确认密码:<input type="password" name="rePassword" placeholder="请确认密码"><br>
真实姓名:<input type="text" name="realName" placeholder="请输入真实姓名"><br>
性别:<input type="radio" name="sex">男
<input type="radio" name="sex">女
<br>
出生日期:<input type="date"><br>
<input type="submit" value="提交">&emsp;<input type="reset" value="重置"><br>
<a href="http://localhost:8080/JF190902/index.jsp">返回登录页面</a>
 </form>
</body>
</html>