<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

.wrapper{
  width: 200px;
  height: auto;
  margin: 0 auto;
}

</style>
</head>
<body>
<h1 align="center">角色详情</h1>
<div class="wrapper">
<input type="hidden" name="roleId" id="roleId" >
<p>用户名:&nbsp;&nbsp;${requestScope.role.roleName}</p>

<a href="${pageContext.request.contextPath}/RoleServlet?roleAction=list">返回角色列表</a>
</div>
</body>
</html>