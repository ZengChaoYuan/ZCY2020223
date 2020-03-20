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
<form class="topForm" name="myPreOrderform"
				action="${pageContext.request.contextPath}/PreOrderServlet"	method="post">
		<input type="hidden" name="preOrderAction" value="myPreOrderList">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">			
</form>
		<div class="list">
		<table border="1" cellpadding="8" style="border-collapse: collapse;">
                    <tr>
						<th colspan="5">预约列表</th>
					</tr>
					<tr>
						<th colspan="4"></th>
						<th><a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=queryAreaBeforePre">我要预约</a></th>
					</tr>
					<tr>
						<th>预约时间</th>
						<th>心理咨询师</th>
						<th>领域</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				<c:choose>
					<c:when test="${not empty requestScope.page.records}">
					<c:forEach items="${requestScope.page.records}" var="myPreOrderList">
					<tr>
					<td>${myPreOrderList.PREORDER_TIME}</td>
					<td>${myPreOrderList.USER_NAME}</td>
					<td>${myPreOrderList.AREA_NAME}</td>
					<td>${myPreOrderList.NAME }</td>
					<td>
					<c:if test="${myPreOrderList.ORDER_STATUS!=6}">
					<c:if test="${myPreOrderList.ORDER_STATUS==4}">
					  <a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=assessConsulterBefore&preOrderId=${myPreOrderList.PREORDER_ID}">评价</a>
				     </c:if>
				     &emsp;
					<a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=preOrderDetail&preOrderId=${myPreOrderList.PREORDER_ID}">查看详情</a>&emsp;
					<a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=queryUserInfo&preOrderId=${myPreOrderList.PREORDER_ID}">查看咨询师</a>
					</c:if>
					</td>
					
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
							<tr>
								<td colspan="5" align="center">
									<a href="${pageContext.request.contextPath}/front/customer/index.jsp">返回</a>
								</td>
							</tr>
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
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function pageMethod(pageNo) {
    document.getElementById("currentPageNum").value = pageNo;
    document.myPreOrderform.submit();
}
</script>
</html>