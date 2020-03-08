<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色新增</title>
<style type="text/css">
form {
	width: 300px;
	margin: 0 auto;
}

form input, form select {
	margin: 10px;
}
</style>
</head>
<body>
	<h1 align="center">角色新增</h1>
	<form action="${pageContext.request.contextPath}/RoleServlet"
		method="post">
		<input type="hidden" name="roleAction" value="add">
		请添加角色:&emsp;<input type="text" name="roleName"> <br>
		<p style="margin-bottom: 1px;">请为该角色添加菜单:</p>
		<br>
		<table>
		<c:forEach items="${requestScope.menuList }" var="menu">
		
			<c:if test="${menu.menuPId==0 }">
				${menu.menuName }<br>
					
				<c:forEach items="${requestScope.menuList }" var="childMenu">
				
					<c:if test="${childMenu.menuPId==menu.menuId }">
						&emsp;&emsp;
						<input type="checkbox" name="childMenu"
							value="${childMenu.menuId }">${childMenu.menuId },${childMenu.menuName}<br>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
		</table>
		<br> <input type="submit" value="提交">&emsp; <a
			href="${pageContext.request.contextPath}/RoleServlet?roleAction=list">返回角色列表</a>
	</form>
</body>
</html>