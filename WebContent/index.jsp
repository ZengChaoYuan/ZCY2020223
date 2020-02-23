<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>校务管理系统</title>
<style type="text/css">
a{
margin-left: 420px;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
	 <div class="wrapper">
        <div class="content">
            <h1>校务管理系统</h1>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <input type="hidden" name="loginAction" value="login" >
                <ul>
                    <li>用户名:</li>
                    <li><input type="text" placeholder="请输入用户名" name="userName" id="userName"></li>
                    <li>密码:</li>
                    <li><input type="password" placeholder="请输入密码" name="password" id="password"></li>
                    <li><input type="submit" value="登陆"><input type="reset" value="重置"></li>
                </ul>
                <a href="${pageContext.request.contextPath}/register.jsp">前往注册页面</a>
            </form>
        </div>
    </div>
</body>
</html>