<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色修改</title>
<style type="text/css">
form {
	width: 300px;
	margin: 0 auto;
}

form input {
	margin: 10px;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/zTreeStyle.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js"></script>
</head>
<body>
	<div>
		<ul class="ztree" id="treeDemo"></ul>
		<input type="button" id="btn" value="提交">
		
	</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	var setting={
			check:{//是否支持复选
				enable:true
			},
			view:{
				showLine:true,
			},
			data:{//数据的设置
				simpleData:{//json格式的数据
					enable:true,
					idKey:"menuId",
					pIdKey:"menuPId",
					rootPid:0 //一级菜单
				},
				key:{
					name:"menuName",
				}
			
			}
					
		};
	
	var data=[
		
		<c:forEach items="${requestScope.menuList }" var="menu">
		 {menuId:${menu.menuId},menuPId:${menu.menuPId},menuName:"${menu.menuName}",checked:${menu.checked}},
		</c:forEach>
		
	];
	
	$.fn.zTree.init($("#treeDemo"),setting,data);
	
	$("#btn").click(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/RoleServlet",
			type:"post",
			data:{"roleAction":"updateBefore","roleId":${param.roleId}},
			async:true,
			dataType:"JSON",
			success:function(data){
				
					$("#roleName").val(data.roleName);
					$.fn.zTree.init($("#treeDemo"),setting,data.menuList);
			},
	        error:function(data){
	        	alert("创建失败!!!");
			}
		})
	});
	
	
});


</script>
</html>