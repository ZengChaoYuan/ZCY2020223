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
<form class="topForm" name="userform"
				action="${pageContext.request.contextPath}/UserServlet"	method="post">
		<input type="hidden" name="userAction" value="list">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">	
		<table  cellpadding="10" align="center" style="margin-top: 10px;">
		  <tr>
		  <th colspan="5">查询区域</th>
		  </tr>
		  <tr>
		  <td>请输入用户名:</td>
		  <td><input name="userName" value="${param.userName }"></td>
		  <td>请选择状态:</td>
		  <td>
		  
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
<th colspan="5">列表区域</th>
</tr>
<tr>
						<th>姓名</th>
						<th>职称</th>
						<th>毕业院校</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				<c:choose>
				<c:when test="${not empty requestScope.page.records }">
				<c:forEach items="${requestScope.page.records }" var="user">
				   <tr>
				     <td>${user.realName }</td>
				     <td>${user.professor }</td>
				     <td>${user.school }</td>
				     <td>
				     <c:if test="${user.deleteStatus==1}">
				     ${user.useStatus==1?"启用":"禁用" }
				     </c:if>
				     <c:if test="${user.deleteStatus==0}">
				       已删除
				     </c:if>
				     </td>			     
				     <td>
				     <c:if test="${user.deleteStatus==1}">
				       
				       <c:if test="${sessionScope.loginUser.userId!=user.userId}">
				       <a href="javascript:void(0)"  onclick="useStatus(${user.userId})">${user.useStatus==1?"禁用":"启用"  }</a>
				     <a href="javascript:void(0)"  onclick="deleteStatus(${user.userId})">${user.deleteStatus==1?"删除":"已删除" }</a>
				   	     </c:if>
				     	 <a href="javascript:void(0)" onclick="resetPassword(${user.userId})">重置密码</a>	
				     	 </c:if>			
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
								<td colspan="5">用户列表查无数据</td>
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
    function resetPassword(obj){
    	//重置密码
    	if(confirm("确定要重置密码吗!")){
    		$.ajax({
				url:"${pageContext.request.contextPath}/UserServlet?userAction=resetPassword",
				type:"post",
				data:{"userId":obj},
				async:true,
				dataType:"JSON",
				success:function (data){
					console.log(data);
					if(data.id==1){
                       window.alert(data.msg);
						window.location.href="${pageContext.request.contextPath}/UserServlet?userAction=list";
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



    function useStatus(obj){
    	//启用/禁用、恢复/锁定
    	if(confirm("确定要修改吗!")){
    		$.ajax({
				url:"${pageContext.request.contextPath}/UserServlet?userAction=useStatus",
				type:"post",
				data:{"userId":obj},
				async:true,
				dataType:"JSON",
				success:function (data){
					console.log(data);
					if(data.id==1||data.id==2){
                       window.alert(data.msg);
						window.location.href="${pageContext.request.contextPath}/UserServlet?userAction=list";
				     }
				},	
				error:function(data){
					alert("出错了!!!");
				}
			})
    	}
    }

	function deleteStatus(obj){
		//删除和已删除状态
		if(confirm("确定要删除吗!")){
			$.ajax({
				url:"${pageContext.request.contextPath}/UserServlet?userAction=deleteStatus",
				type:"post",
				data:{"userId":obj},
				async:true,
				dataType:"JSON",
				success:function (data){
					console.log(data);
					if(data.id==1){
						window.alert(data.msg);
						window.location.href="${pageContext.request.contextPath}/UserServlet?userAction=list";
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
        document.userform.submit();
    }
</script>
</html>