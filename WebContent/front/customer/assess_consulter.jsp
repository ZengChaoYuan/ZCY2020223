<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评价咨询师</title>
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
#assess{
width:60%;
height: 40px;
margin-left: 60px;
}

</style>
</head>
<body>
<div class="wrapper">
<div class="contentArea">
<div class="list geju">
<table border="1" cellpadding="2" style="border-collapse: collapse;">
  <tr>
  <th colspan="4">咨询师评价</th>
  </tr>
  <tr>
  <tr>
  <td class="left" colspan="4">咨询师姓名:&nbsp;${requestScope.userInfo.USER_NAME}</td>
  </tr>
   <tr>
  <td class="left" colspan="4">${requestScope.userInfo.SCHOOL}   ${requestScope.userInfo.PROFESSOR}</td>
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
      专业背景:&nbsp;
  ${requestScope.userInfo.PROFESS_BACK}
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
  <td class="left" colspan="4">评价:</td>
  </tr>
   <tr>
  <td class="left" colspan="4">
    <textarea name="assess" id="assess"></textarea>
  </td>
  </tr>
  <tr>
  <td colspan="4">
  <a href="javascript:void(0)" onclick="submit(${requestScope.preOrderDetail.PREORDER_ID })">提交</a>
  </td>
  </tr>
</table>
</div>
</div>
</div>

</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function submit(obj){
	if($("#assess").val()==""){
		alert("请输入对该咨询师的评价");
		return;
	}
	
	$.ajax({
		url:"${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=assessConsulter",
		type:"post",
		data:{"preOrderId":obj,"assess":$("#assess").val()},
		async:true,
		dataType:"JSON",
		success:function (data){
			console.log(data);
			if(data.id==1){
               window.alert(data.msg);
               window.location.href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=myPreOrderList";
		     }else if(data.id==2){
		    	 window.alert(data.msg);
		     }
		},	
		error:function(data){
			alert("出错了!!!");
		}
	})
}

</script>
</html>