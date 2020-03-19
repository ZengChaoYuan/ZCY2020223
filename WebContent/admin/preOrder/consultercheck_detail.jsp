<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>咨询师查看详情</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
<style type="text/css">
.geju{
 width: 80vw;
 height:80vh;
 margin: 0 auto;
}
.left{
  text-align: left;
  text-indent: 110px;
}

</style>
</head>
<body>
<div class="wrapper">
<div class="contentArea">
<div class="list geju">
<table border="1" cellpadding="2" style="border-collapse: collapse;">
  <tr>
  <th colspan="6">预约详情</th>
  </tr>
  <tr>
   <td>咨询人:</td>
   <td>${requestScope.preOrderDetail.CUSTOMER_NAME }</td>
  <td colspan="2">领域:${requestScope.preOrderDetail.AREA_NAME }</td>
  <td colspan="2">预约时间:${requestScope.preOrderDetail.PREORDER_TIME }</td>
  </tr>
  <tr>
  <td colspan="2">预约状态:${requestScope.preOrderDetail.NAME}</td>
  <td colspan="2">费用:${requestScope.preOrderDetail.PREORDER_PRICE}元</td>
  <td colspan="2">咨询师:${requestScope.preOrderDetail.USER_NAME}</td>
  </tr>
  <tr>
  <td colspan="2">问题描述:</td><td colspan="4">${requestScope.preOrderDetail.PROBLEM_DESC}</td>
  </tr> 
  <tr>
  <td colspan="2">诊断答复:</td><td colspan="4">
   <c:if test="${requestScope.preOrderDetail.ORDER_STATUS>=4}">
     ${requestScope.preOrderDetail.DIAGNOSE_REPLY}
   </c:if>
   <c:if test="${requestScope.preOrderDetail.ORDER_STATUS<4}">
              暂未诊断，无答复
   </c:if>
  </td>
  </tr>
  <tr>
  <td colspan="2">答复时间:</td><td colspan="4">
   <c:if test="${requestScope.preOrderDetail.ORDER_STATUS>=4}">
     ${requestScope.preOrderDetail.REPLY_TIME}
   </c:if>
   <c:if test="${requestScope.preOrderDetail.ORDER_STATUS<4}">
              暂未答复时间
   </c:if>
  </td>
  </tr> 
  <tr>
  <td colspan="2">评价内容:</td><td colspan="4">${requestScope.preOrderDetail.EVALUATE_CONTENT}
 </td>
  </tr>
   <tr>
  <td colspan="6"><a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=allPreOrderListByuserId">返回</a> </td>
  </tr>
</table>
</div>
</div>
</div>

</body>
</html>