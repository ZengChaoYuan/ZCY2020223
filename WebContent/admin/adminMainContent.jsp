<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内容</title>
<link rel="stylesheet" href="../css/adminMainContent.css">
</head>
<body>
  <div class="content">
                 欢迎你，${sessionScope.loginUser.userName}&nbsp;${sessionScope.loginUser.roleId==1?"系统管理员":"咨询师"}
    </div>
</body>
</html>