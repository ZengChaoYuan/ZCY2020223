<%@page import="com.cyzy.bean.Role"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示所有角色</title>
<style type="text/css">
body, ul, ol, li, p {
	margin: 0;
	padding: 0;
	list-style: none;
}

.wrapper {
	width: 100vw;
	height: 100vh;
	display: flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
}

.contentArea {
	width: 90%;
	height: 700px;
	display: flex;
	flex-direction: column;
}

.contentArea>form {
	width: 100%;
	height: 25%;
	border: 1px solid #666;
	border-radius: 12px;
}

.contentArea>form>p {
	text-indent: 20px;
	padding-top: 10px;
	font-size: 18px;
	font-weight: bold;
}

.contentArea>form>p:nth-child(2) {
	display: flex;
}

.contentArea>form>p:nth-child(2)>span:nth-child(2) {
	margin-left: 10px;
}

.contentArea>form>p:last-child {
	padding-top: 20px;
	text-indent: 20px;
}

.contentArea>form>p:last-child>input {
	margin-left: 20px;
	padding: 6px 18px;
	border: 1px solid #ccc;
	background-color: transparent;
	border-radius: 10px;
	outline: none;
	font-weight: bold;
}

.contentArea>form>p:last-child>a {
	margin-left: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	text-decoration: none;
	padding: 7px 18px;
	font-weight: bold;
	color: black;
	font-size: 14px;
}

.contentArea>form>p:last-child>input:nth-child(1) {
	margin-left: 0px;
}

.contentArea>form>p:last-child>input:hover, .contentArea>form>p:last-child>a:hover
	{
	cursor: pointer;
	background-color: red;
	border: 1px solid transparent;
	color: white;
}

.contentArea>.list {
	width: 100%;
	margin-top: 2%;
	height: 73%;
	border: 1px solid #666;
	border-radius: 12px;
}

.list>p:nth-child(1) {
	text-indent: 20px;
	margin-top: 10px;
	font-size: 18px;
	font-weight: bold;
}

.list>table {
	width: 96%;
	height: 100px;
	margin: 10px auto 0;
}

table tr th {
	text-align: center;
	height: 40px;
	line-height: 40px;
	font-size: 24px;
}

table tr td {
	text-align: center;
	height: 32px;
	line-height: 32px;
}

table tr td a {
	text-decoration: none;
}

.page {
	width: 96%;
	margin: 10px auto 0;
	height: 32px;
	font-size: 18px;
	font-weight: bold;
	text-align: right;
}

.page a {
	padding: 0 8px;
}
</style>
</head>
<body>

	<div class="wrapper">
		<div class="contentArea">
			<form name="roleform"
				action="${pageContext.request.contextPath}/RoleServlet"
				method="post">
				<input type="hidden" name="roleAction" value="list"> 
				<p>
					<span>请输入角色名:&emsp;
					<input name="roleName">
					</span>  		
					<span> 
					<a
						href="${pageContext.request.contextPath}/admin/role/role_addtree.jsp"
						target="adminMainContent">新增 </a>
					</span>
				</p>

				<p>
					<input type="submit" value="提交">
				</p>
			</form>
			<div class="list">
				<p>列表区域</p>
				<table border="1" style="border-collapse: collapse;">
					<tr>
						<th>角色ID</th>
						<th>角色名称</th>
						<th>操作</th>
					</tr>
				<c:choose>
						<c:when test="${not empty requestScope.roleList }">
							<c:forEach items="${requestScope.roleList }" var="role">

								<tr>
									<td>${role.roleId }</td>
			                        <td>${role.roleName }</td>
								
									<td>
									<a href="${pageContext.request.contextPath}/RoleServlet?roleAction=updateBefore&roleId=${role.roleId}">修改</a>
									<!-- <a
										href="${pageContext.request.contextPath}/admin/role/role_update.jsp?roleId=${role.roleId}">
											修改 </a>  -->
											<a
										href="${pageContext.request.contextPath}/RoleServlet?roleAction=delete&roleId=${role.roleId}">删除</a>
										<a
										href="${pageContext.request.contextPath}/RoleServlet?roleAction=detail&roleId=${role.roleId}">查看详情</a>

									</td>
								</tr>

							</c:forEach>
							<tr>
								<td colspan="3" align="center">
									<a href="javascript:void(0)">首页</a> 
									<a href="javascript:void(0)">上一页</a> 
									<a href="javascript:void(0)">下一页</a> 
									<a href="javascript:void(0)">尾页</a>
								</td>
							</tr>


						</c:when>

						<c:otherwise>
							<tr>
								<td colspan="3">角色列表查无数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>


			</div>
		</div>

	</div>

</body>
</html>