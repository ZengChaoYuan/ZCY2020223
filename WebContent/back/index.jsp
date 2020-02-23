<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台页面</title>
</head>
<body>
<h1>欢迎你!,${sessionScope.loginUser.userName },我要<a href="http://localhost:8080/JF190902/LoginServlet?loginAction=loginOut">退出</a></h1>

</body>
</html>