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

<!--  <form class="list" action="${pageContext.request.contextPath}/TitleServlet?titleAction=countScope" method="post">
 -->
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
<c:when test="${not empty requestScope.myAccountList }">
<c:forEach items="${requestScope.myAccountList }" var="myAccount">
  <tr>
  <td>${myAccount.happenTime}</td>
  <td>${myAccount.happenThing}</td>
  <td>
   <c:if test="${myAccount.userId==0}">
				    ${sessionScope.loginCustomer.customerName}
				     </c:if>
   <c:if test="${myAccount.userId!=0}">
				     ${myAccount.userId }(咨询师)
				     </c:if>
  </td>
  <td>${myAccount.consumpType}</td>
  <td>${myAccount.consumpMoney}</td>
  <tr>
  
</c:forEach>
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
<!-- </form> -->


</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<!-- <script type="text/javascript">
function consump(obj){
	$.ajax({
		url:"${pageContext.request.contextPath}/MyAccountServlet?accountAction=consumpMoney",
		type:"post",
		data:{"customerId":obj},
		async:true,
		dataType:"JSON",
		success:function(data){
			if(data.id==1){
				window.alert(data.msg);
				window.location.href="${pageContext.request.contextPath}/MyAccountServlet?accountAction=list";
			}else if(data.id==2){
				window.alert(data.msg);
			}
		},
		error:function(data){
			alert("出错啦!");
		}
	})
}
</script> -->
</html>