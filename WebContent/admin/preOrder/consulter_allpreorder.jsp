<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>咨询师的客户预约列表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
<div class="wrapper">
		<div class="contentArea">
		<form class="topForm" name="consulterownerform"
				action="${pageContext.request.contextPath}/PreOrderServlet"	method="post">
		<input type="hidden" name="preOrderAction" value="allPreOrderListByuserId">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">			
</form>
		<div class="list">
		<table border="1" cellpadding="8" style="border-collapse: collapse;">
		            <tr>
						<th colspan="6">预约列表</th>
					</tr>
					 <tr>
						<td>查询条件:</td>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td colspan="4">预约日期: <input type="date">&nbsp; 至&nbsp; <input type="date"> </td>
						<td colspan="2">完成状态:
						<select name="orderStatus" id="orderStatus">
						<option value="0">请选择</option>
						<c:forEach items="${requestScope.orderStatusList }" var="orderStatus">
						 
						<option value="${orderStatus.value}">${orderStatus.name }</option>
						</c:forEach>		
						</select> 
						</td>
					</tr>
					<tr>
						<td colspan="4"></td>
						<td colspan="2"><a href="#">查询</a></td>
					</tr>
					<tr>
						<th>预约时间</th>
						<th>用户</th>
						<th>领域</th>
						<th>咨询问题</th>
						<th>完成状态</th>
						<th>操作</th>
					</tr>
					<c:choose>
					<c:when test="${not empty requestScope.page.records}">
					<c:forEach items="${requestScope.page.records }" var="allPreOrder">
					<tr>
					<td>${allPreOrder.PREORDER_TIME}</td>
					<td>${allPreOrder.CUSTOMER_NAME}</td>
					<td>${allPreOrder.AREA_NAME}</td>
					<td>${allPreOrder.PROBLEM_DESC }</td>
					<td>${allPreOrder.NAME }</td>
					<td>
					<c:if test="${allPreOrder.ORDER_STATUS!=6}">
					<c:if test="${allPreOrder.ORDER_STATUS==2}">
					  <a href="javascript:void(0)" onclick="surePreOrder(${allPreOrder.PREORDER_ID})">确认预约</a>
				     </c:if>
				     <c:if test="${allPreOrder.ORDER_STATUS==3}">
					  <a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=assessReplyBefore&preorderId=${allPreOrder.PREORDER_ID}">诊断答复</a>
				     </c:if>
				     &emsp;
					<a href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=consultercheckDetail&preorderId=${allPreOrder.PREORDER_ID}">查看详情</a>
					</c:if>
					</td>
					
					<tr>
					</c:forEach>
					<tr>
								<td colspan="6" align="center">
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
								<td colspan="6">用户预约列表查无数据</td>
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
    document.consulterownerform.submit();
}

function surePreOrder(obj){
	alert(obj);
	if(confirm("确定要预约吗!")){
		$.ajax({
			url:"${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=surePreOrder",
			type:"post",
			data:{"preorderId":obj},
			async:true,
			dataType:"JSON",
			success:function (data){
				console.log(data);
				if(data.id==1){
					window.alert(data.msg);
					window.location.href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=allPreOrderListByuserId";
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