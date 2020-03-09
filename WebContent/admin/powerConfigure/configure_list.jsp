<%@page import="com.cyzy.bean.Role"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限配置(角色列表)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
</head>
<body>

	<div class="wrapper">
		<div class="contentArea">
			<div class="list">
				<table border="1" cellpadding="15" style="border-collapse: collapse; margin-top: 50px;">
					<tr>
                    <th colspan="3">权限配置</th>
                    </tr>
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
									</td>
								</tr>
							</c:forEach>
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