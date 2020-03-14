<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>预约列表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
<div class="wrapper">
		<div class="contentArea">
		<div class="list">
		<table border="1" cellpadding="8" style="border-collapse: collapse;">
                    <tr>
						<th colspan="5">预约列表</th>
					</tr>
					<tr>
						<th colspan="4"></th>
						<th><a href="#">我的预约</a></th>
					</tr>
					<tr>
						<th>预约时间</th>
						<th>心理咨询师</th>
						<th>领域</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				<c:choose>
					<c:when test="${not empty requestScope.customerReportList}">
					<c:forEach items="${requestScope.customerReportList }" var="reportList">
					<tr>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<tr>
					</c:forEach>
					</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">预约列表查无数据</td>
							</tr>
						</c:otherwise>
					</c:choose>	
		</table>	
		</div>
		</div>
		</div>
</body>
</html>