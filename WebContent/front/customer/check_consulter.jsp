<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看咨询师</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
<style type="text/css">
.geju{
 width: 60vw;
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
  <th colspan="4">咨询师档案</th>
  </tr>
  <tr>
  <tr>
  <td class="left" colspan="4">咨询师姓名:&nbsp;${requestScope.userInfo.USER_NAME }</td>
  </tr>
   <tr>
  <td class="left" colspan="4">${requestScope.userInfo.SCHOOL }   ${requestScope.userInfo.PROFESSOR }</td>
  </tr>
   <tr>
  <td class="left" colspan="4">擅长领域:
   <c:forEach items="${requestScope.areaList }" var="area">
   <span>${area.AREA_NAME }</span>
   </c:forEach>
  </td>
  </tr>
   <tr>
  <td class="left" colspan="4">
       专业背景: ${requestScope.userInfo.PROFESS_BACK } 
  </td>
  </tr>
  <tr>
  <td class="left" colspan="4">简介:</td>
  </tr>
  <tr>
  <td class="left" colspan="4">
   <textarea style="width:60%;height: 40px;margin-left: 60px;">
   ${requestScope.userInfo.INTRO}
   </textarea>
  </td>
  </tr>
  <tr>
  <td class="left" colspan="4">用户评价:</td>
  </tr>
   <c:forEach items="${requestScope.assessInfoList }" var="assessInfo">
    <c:if test="${assessInfo.ORDER_STATUS!=6}">
  <tr>
  <td class="left" colspan="1">${assessInfo.CUSTOMER_NAME }</td>
  <td colspan="3">
  <c:if test="${assessInfo.ORDER_STATUS==5}">
  ${assessInfo.ASSESS_TIME }
  </c:if>
  <c:if test="${assessInfo.ORDER_STATUS!=5}">
       此用户未评价，暂无评价时间
  </c:if>
  </td>
  </tr>
   <tr>
  <td class="left" colspan="4">
  <c:if test="${assessInfo.ORDER_STATUS==5}">
  ${assessInfo.EVALUATE_CONTENT }
  </c:if>
  <c:if test="${assessInfo.ORDER_STATUS!=5}">
       此用户未评价
  </c:if>
  </td>
  </tr>
 </c:if>
  
  </c:forEach>
  <tr>
  <td colspan="4">
        <a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=myPreOrderList">返回</a>
  </td>
  </tr>
</table>
</div>
</div>
</div>

</body>
</html>