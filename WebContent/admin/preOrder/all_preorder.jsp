<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员查看所有的预约列表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
<div class="wrapper">
		<div class="contentArea">
		<form class="topForm" name="allform"
				action="${pageContext.request.contextPath}/PreOrderServlet"	method="post">
		<input type="hidden" name="preOrderAction" value="allPreOrderList">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">			
        </form>
		<div class="list">
		<table border="1" cellpadding="8" style="border-collapse: collapse;">
		            <tr>
						<th colspan="7">预约列表</th>
					</tr>
					<tr>
						<td>查询条件:</td>
						<td colspan="6"></td>
					</tr>
					<tr>
						<td colspan="3">咨询师: <input type="text"></td>
						<td colspan="3">用户: <input type="text"></td>
						<td><input type="button" value="查询"></td>
					</tr>
					<tr>
						<td colspan="6">预约日期: <input type="date">&emsp;至&emsp; <input type="date"></td>
						<td></td>
					</tr>
					<tr>
						<th>预约时间</th>
						<th>心理咨询师</th>
						<th>领域</th>
						<th>用户</th>
						<th>完成时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>	
					<c:choose>
					<c:when test="${not empty requestScope.page.records}">
					<c:forEach items="${requestScope.page.records }" var="allPreOrder">
					<tr>
					<td>${allPreOrder.PREORDER_TIME}</td>
					<td>${allPreOrder.USER_NAME}</td>
					<td>${allPreOrder.AREA_NAME}</td>
					<td>${allPreOrder.CUSTOMER_NAME}</td>
					<td>
					<c:if test="${allPreOrder.ORDER_STATUS==5}">
					${allPreOrder.ASSESS_TIME}
					</c:if>
					<c:if test="${allPreOrder.ORDER_STATUS!=5}">
					用户未评价，暂无完成时间
					</c:if>
                     </td>
					<td>${allPreOrder.NAME }</td>
					<td>
					<c:if test="${allPreOrder.ORDER_STATUS==2}">
					  <a href="javascript:void(0)" onclick="stopPreOrder(${allPreOrder.PREORDER_ID})">终止预约</a>
				     </c:if>
				     <c:if test="${allPreOrder.ORDER_STATUS!=6}">
					 <a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=usercheckDetail&preorderId=${allPreOrder.PREORDER_ID}">查看详情</a>
					 </c:if>
					</td>
					</tr>
					</c:forEach>
					<tr>
								<td colspan="7" align="center">
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
function stopPreOrder(obj){
	alert(obj);
	if(confirm("确定要终止预约吗!")){
		$.ajax({
			url:"${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=stopPreOrder",
			type:"post",
			data:{"preorderId":obj},
			async:true,
			dataType:"JSON",
			success:function (data){
				console.log(data);
				if(data.id==1){
					window.alert(data.msg);
					window.location.href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=allPreOrderList";
				}else if(data.id==2){
					window.alert(data.msg);
					return;
				}
			},
			error:function(data){
				alert("出错了!!!");
			}
		})
	}
	
	
}
</script>
</html>