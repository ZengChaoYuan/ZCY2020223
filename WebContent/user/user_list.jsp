<%@page import="java.util.List"%>
<%@page import="com.cyzy.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示所有用户</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
</head>
<body>
	<div class="wrapper">
		<div class="contentArea">
			<form name="userform"
				action="${pageContext.request.contextPath}/UserServlet"
				method="post">
				<input type="hidden" name="userAction" value="listLike"> 
				<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">


				<p>
					<span>请输入用户名:&emsp;<input name="userName"
						value="${param.userName }"></span> <span>请选择角色:&emsp; <select
						name="roleId">
							<option value="0">请选择</option>
							<c:forEach items="${requestScope.roleList }" var="role">
								<option ${role.roleId == param.roleId ? " selected ":""}
									value="${role.roleId }">${role.roleName }</option>
							</c:forEach>
					</select></span> <span> <a
						href="${pageContext.request.contextPath}/UserServlet?userAction=addBefore"
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
						<th>用户名</th>
						<th>密码</th>
						<th>真实姓名</th>
						<th>性别</th>
						<th>出生日期</th>
						<th>所属角色</th>
						<th>操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty requestScope.page.records }">
							<c:forEach items="${requestScope.page.records }" var="user">

								<tr>
									<td>${user.userName }</td>
									<td>${user.password }</td>
									<td>${user.realName }</td>
									<td>${user.sex==1?"男":"女" }</td>

									<td>${user.birthday }</td>
									<td>
									<select name="roleId" disabled="false">
											<c:forEach items="${requestScope.roleList }" var="role">
												<option ${role.roleId==user.roleId?"selected":""}
													value="${role.roleId}">${role.roleName }</option>
											</c:forEach>
									</select>
									</td>
									<td><a
										href="${pageContext.request.contextPath}/UserServlet?userAction=updateBefore&userId=${user.userId}">
											修改 </a> <a
										href="${pageContext.request.contextPath}/UserServlet?userAction=delete&userId=${user.userId}">删除</a>
										<a
										href="${pageContext.request.contextPath}/UserServlet?userAction=detail&userId=${user.userId}">查看详情</a>

									</td>
								</tr>

							</c:forEach>
							<tr>
								<td colspan="7" align="center">
									共${page.totalRecordsNum}条记录,共${page.totalPageNum}页,当前${page.currentPageNum}页
									<a href="javascript:void(0)" onclick="pageMethod(1)">首页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.prevPageNum})">上一页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.nextPageNum})">下一页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.totalPageNum})">尾页</a>
								</td>
							</tr>


						</c:when>

						<c:otherwise>
							<tr>
								<td colspan="7">用户列表查无数据</td>
							</tr>
						</c:otherwise>
					</c:choose>


				</table>

			</div>
		</div>

	</div>


</body>
<script>
    function pageMethod(pageNo) {
        document.getElementById("currentPageNum").value = pageNo;
        document.userform.submit();
    }
</script>
</html>