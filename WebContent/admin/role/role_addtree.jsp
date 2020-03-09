<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色新增</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zTreeStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js"></script>
</head>
<body>
 <div>
    请输入角色名字:&emsp;<input name="roleName" id="roleName" placeholder="请输入角色名称">
    
    <ul class="ztree" id="treeDemo"></ul>
    <br>
   <input type="button" id="submit" value="提交">
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
	//获取树形菜单
	$.ajax({
		url:"${pageContext.request.contextPath}/RoleServlet",
		type:"post",
		data:{"roleAction":"allMenu"},
		async:true,
		dataType:"JSON",
		success:function(data){
			console.log(data);
			$.fn.zTree.init($("#treeDemo"),setting,data);
		},

	    error:function(data){
	    	alert("创建失败!");
		}
	});
	//提交表单判断是否选中
	$("#submit").click(function(){
		var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
		var nodes=treeObj.getCheckedNodes(true);
		if(0 == nodes.length){
			alert("请选择菜单");
			return;
		}
		var dataNodes="";
		for(var i=0;i<nodes.length;i++){
			dataNodes+=nodes[i].menuId+",";
		}
		alert(dataNodes);//判断是否选中
		
		$.ajax({
			url:"${pageContext.request.contextPath}/RoleServlet",
			type:"post",
			data:{"roleAction":"add","roleName":$("#roleName").val(),"menus":dataNodes},
			async:true,
			dataType:"JSON",
			success:function(data){	
					alert(data.msg);
					window.location.href="${pageContext.request.contextPath}/RoleServlet?roleAction=list";	
			},
	        error:function(data){
	        	alert(data.msg);
			}
			
		});
		
	})
	
	
	
})
	






</script>
</html>