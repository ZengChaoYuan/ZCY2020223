<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示所有客户</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
</head>
<body>
<div class="wrapper">
<div class="contentArea">
<!-- 查询区域 -->
<form class="topForm" name="customerform"
				action="${pageContext.request.contextPath}/CustomerServlet"	method="post">
		<input type="hidden" name="customerAction" value="list">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">	
		<table  cellpadding="10" align="center" style="margin-top: 10px;">
		  <tr>
		  <th colspan="5">查询区域</th>
		  </tr>
		  <tr>
		  <td>请输入用户名:</td>
		  <td><input name="customerName" value="${param.customerName }"></td>
		  <td>请选择状态:</td>
		  <td>
		   <!-- <select name="useStatus">
          <option value="0">禁用</option>
          <option value="1">启用</option>				
          </select> -->
          </td>
          <td><a href="javascript:void(0);" target="adminMainContent">新增 </a></td>
		  </tr>
		  <tr><td colspan="5"><input type="submit" class="submit" value="提交"></td></tr>
		</table>		
	
</form>
<!-- 列表 -->
<div class="list">

<table border="1" cellpadding="8" style="border-collapse: collapse;">
<tr>
<th colspan="6">列表区域</th>
</tr>
<tr>
						<th>用户名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>手机号</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				<c:choose>
				<c:when test="${not empty requestScope.page.records }">
				<c:forEach items="${requestScope.page.records }" var="customer">
				   <tr>
				     <td>${customer.customerName }</td>
				     <td>${customer.sex==1?"男":"女" }</td>
				     <td>${customer.age }</td>
				     <td>${customer.tel }</td>
				     <td>${customer.useStatus==1?"启用":"禁用" }</td>
				     <td>
				      <a	href="${pageContext.request.contextPath}/CustomerServlet?customerAction=updateUseStatus&customerId=${customer.customerId}">${customer.useStatus==1?"禁用":"启用" }</a>
				      
				    <!--  <a	href="${pageContext.request.contextPath}/CustomerServlet?customerAction=updateDeleteStatus&customerId=${customer.customerId}">${customer.deleteStatus==1?"删除":"已删除" }</a>
				     -->
				    <!--  <a   customerId='${ customer.customerId}' onclick="deleteStatus(${customer.customerId})">${customer.deleteStatus==1?"删除":"已删除" }</a> -->
				     <a  onclick="deleteStatus(${customer.customerId})">${customer.deleteStatus==1?"删除":"已删除" }</a>
				     <a	href="${pageContext.request.contextPath}/CustomerServlet?customerAction=resetPassword&customerId=${customer.customerId}">重置密码</a>		
				     						
					</td>
				   </tr>
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
								<td colspan="6">用户列表查无数据</td>
							</tr>
						</c:otherwise>
				</c:choose>	
	

</table>


</div>
</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script>
	function deleteStatus(obj){
		if(confirm("确定要删除吗!")){
			$.ajax({
				url:"${pageContext.request.contextPath}/CustomerServlet?customerAction=updateDeleteStatus",
				type:"post",
				data:{"customerId":obj},
				async:true,
				dataType:"JSON",
				success:function (data){
					console.log(data);
					if(data.id==1){
						window.alert(data.msg);
						window.location.href="${pageContext.request.contextPath}/CustomerServlet?customerAction=list";
					}else if(data.id==2||data.id==3){
						window.alert(data.msg);
					}
				},
				error:function(data){
					alert("出错了!!!");
				}
			})
		}
	}
    function pageMethod(pageNo) {
        document.getElementById("currentPageNum").value = pageNo;
        document.customerform.submit();
    }
</script>
</html>