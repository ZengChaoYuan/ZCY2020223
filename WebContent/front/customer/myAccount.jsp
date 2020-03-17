<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的账户</title>
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
<form class="topForm" name="myAccountform"
				action="${pageContext.request.contextPath}/MyAccountServlet"	method="post">
		<input type="hidden" name="accountAction" value="list">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">			
</form>
 <div class="list">
<table border="1" cellpadding="8" style="border-collapse: collapse;">
<tr>
<th colspan="5">我的账户</th>
</tr>
<tr>
<th  class="left" colspan="4">余额: ${sessionScope.loginCustomer.balance}</th>
<th><a href="${pageContext.request.contextPath}/front/customer/consump_money.jsp">充值</a> </th>
</tr>
<tr>
<th class="left" colspan="5">收支明细:</th>
</tr>
                     <tr>
						<th>发生时间</th>
						<th>发生事项</th>
						<th>关系人</th>
						<th>收入/支出</th>
						<th>金额(元)</th>
					</tr>
<c:choose>
<c:when test="${not empty requestScope.page.records }">
<c:forEach items="${requestScope.page.records }" var="myAccount">
  <tr>
  <td>${myAccount.HAPPEN_TIME}</td>
  <td>${myAccount.HAPPEN_THING}</td>
  <td>
   <c:if test="${myAccount.USER_ID==0}">
				    ${sessionScope.loginCustomer.customerName}
				     </c:if>
   <c:if test="${myAccount.USER_ID!=0}">
				     ${myAccount.USER_NAME }(咨询师)
				     </c:if>
  </td>
  <td>${myAccount.CONSUMP_TYPE}</td>
  <td>${myAccount.CONSUMP_MONEY}</td>
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
<td colspan="5"><a href="${pageContext.request.contextPath}/front/customer/index.jsp">返回</a> </td>
</tr>
 
</c:when>
			            <c:otherwise>
							<tr>
								<td colspan="5">我的账户查无数据，<a href="${pageContext.request.contextPath}/front/customer/index.jsp">返回</a></td>
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
    document.myAccountform.submit();
}
</script>
</html>