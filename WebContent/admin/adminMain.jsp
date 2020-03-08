<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminMain.css">
</head>
<body>

 <div class="wrapper">
        <div class="head">
            <div class="head-left">
                                     管理端
            </div>
            <div class="head-right">
                <span>欢迎你，${sessionScope.loginUser.userName}&nbsp;${sessionScope.loginUser.roleId==1?"系统管理员":"咨询师"}</span>
                <a href="${pageContext.request.contextPath}/LoginServlet?loginAction=loginOut">退出</a>
            </div>
        </div>
        <div class="content">
            <iframe src="${pageContext.request.contextPath }/admin/adminMainMenu.jsp" frameborder="0" 
            class="adminMainMenu" name="adminMainMenu"></iframe>
            <iframe src="${pageContext.request.contextPath }/admin/adminMainContent.jsp" frameborder="0" 
            class="adminMainContent" name="adminMainContent"></iframe>
        </div>
    </div>
</body>
</html>