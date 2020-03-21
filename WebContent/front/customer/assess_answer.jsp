<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评估答题</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
<div class="wrapper">
<div class="contentArea">

<!-- <form class="list" action="${pageContext.request.contextPath}/TitleServlet?titleAction=countScope" method="post"> -->
<div class="list">
<table border="1" cellpadding="8" style="border-collapse: collapse;">
<tr>
<th colspan="2">评估答题</th>
</tr>
                     <tr>
						<th>题目</th>
						<th>选项</th>
					</tr>
<c:choose>
<c:when test="${not empty requestScope.titleList }">
<c:forEach items="${requestScope.titleList }" var="title">
 <tr>
  <td>${title.titleName}</td>
  <td>
  <c:forEach items="${title.items }" var="item">
      <input type="radio" name="${title.titleName}" value="${item.scope}">${item.itemName }
  </c:forEach>
  </td>
 </tr>
</c:forEach>
<tr>
<td colspan="2"> <a href="javascript:void(0)" onclick="submit()">提交</a></td>
</tr>
 
</c:when>
			            <c:otherwise>
							<tr>
								<td colspan="2">该领域查无数据，<a href="${pageContext.request.contextPath}/AreaServlet?areaAction=list">返回</a></td>
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
<script type="text/javascript">
function submit(){
	alert("提交");
	var score=0;
	$("input:radio:checked").each(function(){
		score+=parseInt(this.value);
	});
	
	$.ajax({
		url:"${pageContext.request.contextPath}/AssReportServlet?assAction=addAssReport",
		type:"post",
		data:{"assScore":score},
		async:true,
		dataType:"JSON",
		success:function (data){
			console.log(data);
			if(data.id==1||data.id==2||data.id==3||data.id==4){
               window.alert(data.msg);
				window.location.href="${pageContext.request.contextPath}/front/customer/index.jsp";
		     }
		},	
		error:function(data){
			alert("出错了!!!");
		}
	})
}


</script>
</html>