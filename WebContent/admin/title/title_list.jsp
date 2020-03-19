<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>题目列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
<div class="wrapper">
<div class="contentArea">
<div class="list">
<table border="1" cellpadding="8" style="border-collapse: collapse;">
<tr>
<th colspan="3">题库管理</th>
<tr>
<tr>
<th colspan="3">
领域: 
<select name="areaId">
<c:forEach items="${requestScope.areaList }" var="area">
<option value="${area.areaId }">${area.areaName}</option>
</c:forEach>
</select>
</th>
<tr>
<th colspan="2">题目预览</th>
<th><a href="${pageContext.request.contextPath}/admin/title/title_add.jsp">新增</a></th>
</tr>
                     <tr>
						<th>题目</th>
						<th>选项</th>
						<th>操作</th>
					</tr>
<c:choose>
<c:when test="${not empty requestScope.titleList }">
<c:forEach items="${requestScope.titleList }" var="title">
 <tr>
  <td>${title.titleId}.${title.titleName}</td>
  <td>
  <c:forEach items="${title.items }" var="item">
      <input type="radio" disabled="disabled">${item.itemName }
  </c:forEach>
  </td>
   <td>
    <a href="${pageContext.request.contextPath}/TitleServlet?titleAction=updateBefore&titleId=${title.titleId}">修改</a>
    <a href="javascript:void(0)" onclick="deleteItem(${title.titleId})">删除</a>
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
								<td colspan="3">题目列表查无数据</td>
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

function deleteItem(obj){
	if(confirm("确定要删除吗?")){
		$.ajax({
			url:"${pageContext.request.contextPath}/TitleServlet?titleAction=delete",
			type:"post",
			data:{"titleId":obj},
			async:true,
			dataType:"JSON",
			success:function (data){
				console.log(data);
				if(data.id==1){
                   window.alert(data.msg);
					window.location.href="${pageContext.request.contextPath}/TitleServlet?titleAction=list";
			     }else if(data.id==2){
			    	 window.alert(data.msg);
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