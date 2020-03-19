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
<form class="topForm" name="userFundform"
				action="${pageContext.request.contextPath}/MyAccountServlet"	method="post">
		<input type="hidden" name="accountAction" value="fundList">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">			
</form>
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
	<c:when test="${not empty requestScope.page.records}">
	<c:forEach items="${requestScope.page.records }" var="fund">
	<tr>
	<td>${fund.HAPPEN_TIME }</td>
	<td>${fund.CUSTOMER_NAME }客户</td>
	<td>${fund.HAPPEN_THING }</td>
	<td>${fund.CONSUMP_MONEY }</td>
	</tr>
	</c:forEach>
	<tr>
								<td colspan="4" align="center">
									共&nbsp;${page.totalRecordsNum}&nbsp;条记录,共&nbsp;${page.totalPageNum}&nbsp;页,
									当前第&nbsp;${page.currentPageNum}&nbsp;页
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
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function pageMethod(pageNo) {
    document.getElementById("currentPageNum").value = pageNo;
    document.userFundform.submit();
}
</script>
</html>