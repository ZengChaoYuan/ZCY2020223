<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员查看用户评估列表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
	<div class="wrapper">
		<div class="contentArea">
		 <form class="topForm" name="allform"
				action="${pageContext.request.contextPath}/AssReportServlet"	method="post">
		<input type="hidden" name="assAction" value="userQueryReport">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">			
        </form>
			<div class="list">
				<table border="1" cellpadding="8" style="border-collapse: collapse;">
					<tr>
						<th colspan="5">用户评估列表</th>
					</tr>
					<tr>
						<td colspan="5">评估日期：<input type="date">&nbsp;至&nbsp;<input type="date">&nbsp;<input type="button" value="查询"> </td>
						
					</tr>
					<tr>
						<td colspan="5">得分：<input type="text">&nbsp;至&nbsp;<input type="text"></td>
					</tr>
					<tr>
						<th>评估日期</th>
						<th>客户</th>
						<th>领域</th>
						<th>得分</th>
						<th>操作</th>
					</tr>
					<c:choose>
					<c:when test="${not empty requestScope.page.records}">
					<c:forEach items="${requestScope.page.records}" var="reportList">
					<tr>
					<td>${reportList.ASS_TIME}</td>
					<td>${reportList.CUSTOMER_NAME}</td>
					<td>${reportList.AREA_NAME}</td>
					<td>${reportList.ASS_SCORE}</td>
					<td><a href="${pageContext.request.contextPath}/AssReportServlet?assAction=userQueryAss&AssReportId=${reportList.ASSREPORT_ID}">查看详情</a> </td>
					<tr>
					</c:forEach>
					<tr>
								<td colspan="5" align="center">
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
								<td colspan="5">用户评估列表查无数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function pageMethod(pageNo) {
    document.getElementById("currentPageNum").value = pageNo;
    document.allform.submit();
}
</script>
</html>