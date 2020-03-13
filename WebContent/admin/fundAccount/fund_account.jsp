<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资金账户</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
<style type="text/css">
.left{
text-align: left;
text-indent: 30px;
}
</style>
</head>
<body>
<div class="wrapper">
<div class="contentArea">
 <div class="list">
 <table border="1" cellpadding="8" style="border-collapse: collapse;">
 <tr>
 <th colspan="4">资金账户</th>
</tr>
<tr>
<th  class="left" colspan="4">余额: ${sessionScope.loginUser.balance}</th>
</tr>
<tr>
<th  class="left" colspan="4">业务收入情况：</th>
</tr>
 <tr>
						<th>发生时间</th>
						<th>客户姓名</th>
						<th>咨询/预约</th>
						<th>金额(元)</th>
					</tr>
<c:choose>
	<c:when test="${not empty requestScope.fundList }">
	<c:forEach items="${requestScope.fundList }" var="fund">
	<tr>
	<td>${fund.happenTime }</td>
	<td>${fund.customerId }客户</td>
	<td>${fund.happenThing }</td>
	<td>${fund.consumpMoney }</td>
	</tr>
	</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="4">资金账户查无数据</td>
		</tr>
	</c:otherwise>
	</c:choose>
	<tr>
	<td colspan="4"><a href="${pageContext.request.contextPath}/admin/adminMainContent.jsp">返回</a></td>
	</tr>
 </table>
 </div>
</div>
</div>
</body>
</html>