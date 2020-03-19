<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>诊断答复</title>
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
  <th colspan="3">诊断答复</th>
  </tr>
  <tr>
  <tr>
  <td class="left">咨询人:&nbsp;${requestScope.preOrderDetail.CUSTOMER_NAME }</td>
  <td class="left">领域:&nbsp;${requestScope.preOrderDetail.AREA_NAME }</td>
  <td class="left">预约时间:&nbsp;${requestScope.preOrderDetail.PREORDER_TIME }</td>
  </tr>
   <tr>
  <td class="left">问题描述:</td>
  <td colspan="2"></td>
  </tr>
  <tr>
  <td class="left" colspan="3">
  <textarea rows="5" cols="80" style="margin-left: 450px;">
  ${requestScope.preOrderDetail.PROBLEM_DESC}
  </textarea>
  </td>
  </tr>
  <tr>
  <td class="left">我的答复:</td>
   <td colspan="2"></td>
  </tr>
  <tr>
  <td class="left" colspan="3">
  <textarea name="reply" id="reply"  rows="5" cols="80" style="margin-left: 450px;">
  
  </textarea>
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
	alert(obj);
	$.ajax({
		url:"${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=assessReply",
		type:"post",
		data:{"preOrderId":obj,"reply":$("#reply").val()},
		async:true,
		dataType:"JSON",
		success:function (data){
			console.log(data);
			if(data.id==1){
               window.alert(data.msg);
               window.location.href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=allPreOrderListByuserId";
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