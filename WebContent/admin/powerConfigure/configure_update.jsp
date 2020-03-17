<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色修改</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/zTreeStyle.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
<style type="text/css">
.centerArea{
 width: 300px;
 height: auto;
}

.flex{
 display: flex;
 flex-direction:column;
 justify-content: center;
 flex-wrap: wrap;
 align-content: center;
}

.btn{
  width:80px;
  text-align:center;
  height:30px;
  line-height:30px;
  border-radius: 8px;
  border: 1px solid #666;
  outline: none;
  margin: 10px auto;
}
.btn:hover {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="wrapper">
	<div class="contentArea flex">
	    <div>
	    <input type="hidden" name="roleId" value="${requestScope.role.roleId}">
	      <span>角色名称 : ${requestScope.role.roleName}</span>
		<ul class="ztree" id="treeDemo"></ul>
		<input type="button" id="btn" class="btn" value="提交">
		</div>
	</div>
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
	
	tree=$.fn.zTree.init($("#treeDemo"),setting,data);
	
	$("#btn").click(function(){
		var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
		var nodes=treeObj.getCheckedNodes(true);//获得选中的菜单，是一个数据[{},{},{}]
		if(nodes.length==0){
			alert("请选择菜单");
			return;
		}
		var dataNodes="";
		for(var i=0;i<nodes.length;i++){
			dataNodes+=nodes[i].menuId+","; //"1,3,4,5"
		}
		
		//alert(dataNodes);//打印选择的菜单
		//alert(${requestScope.role.roleId});//打印当前角色所拥有的ID
	    $.ajax({
	    	url:"${pageContext.request.contextPath}/RoleServlet",
			type:"post",
			data:{"roleAction":"update","roleId":${requestScope.role.roleId},"menuList":dataNodes},
			async:true,
			dataType:"JSON",
			success:function(data){
				if(data.id==1){
					window.alert(data.msg);
					window.location.href="${pageContext.request.contextPath}/RoleServlet?roleAction=list";
				}else if(data.id==2){
					window.alert(data.msg);
					return;
				}
			},
	        error:function(data){
	        	alert("修改权限出错啦!!!");
			}
	    	
	    	
	    });
		
		
		
	});
	
	
});


</script>
</html>