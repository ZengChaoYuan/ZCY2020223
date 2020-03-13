<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户评估列表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
	<div class="wrapper">
		<div class="contentArea">
			<div class="list">
				<table border="1" cellpadding="8" style="border-collapse: collapse;">
					<tr>
						<th colspan="5">用户评估列表</th>
					</tr>
					<tr>
						<td colspan="5">评估日期：<input type="date">至<input type="date"><input type="button" value="查询"> </td>
						
					</tr>
					<tr>
						<td colspan="5">得分：<input type="text">至<input type="text"></td>
					</tr>
					<tr>
						<th>评估日期</th>
						<th>客户</th>
						<th>领域</th>
						<th>得分</th>
						<th>操作</th>
					</tr>
					<c:choose>
					<c:when test="${not empty requestScope.customerReportList}">
					<c:forEach items="${requestScope.customerReportList }" var="reportList">
					<tr>
					<td>${reportList.ASS_TIME}</td>
					<td>${reportList.CUSTOMER_NAME}</td>
					<td>${reportList.AREA_NAME}</td>
					<td>${reportList.ASS_SCORE}</td>
					<td><a href="${pageContext.request.contextPath}/AssReportServlet?assAction=userQueryAss&AssReportId=${reportList.ASSREPORT_ID}">查看详情</a> </td>
					<tr>
					</c:forEach>
					</c:when>
					
						<c:otherwise>
							<tr>
								<td colspan="5">用户评估列表查无数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		</div>
	</div>
</body>
</html>