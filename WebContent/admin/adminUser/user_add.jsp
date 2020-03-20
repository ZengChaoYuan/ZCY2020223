<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员分配用户</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
<style type="text/css">
.left{
text-align: left;
text-indent: 30px;
}
</style>
</head>
<body>
<div class="wrapper">
<div class="contentArea">
<div class="list">
<table border="1" cellpadding="8" style="border-collapse: collapse;">
<tr>
 <th colspan="4">新增人员</th>
</tr>
 <tr>
 <td>用户名:&nbsp;<input type="text" name="userName" id="userName" placeholder="请输入用户名"></td>
 <td>密码:&nbsp;<input type="password" name="password" id="password" placeholder="请输入密码"></td>
 <td>毕业院校:&nbsp;<input type="text" name="school" id="school" placeholder="请输入毕业院校"></td>
 <td>职称:&nbsp;<input type="text" name="professor" id="professor" placeholder="请输入职称"></td>
 </tr>
 <tr>
 <td>真实姓名:&nbsp;<input type="text" name="realName" id="realName" placeholder="请输入姓名"></td>
 <td>专业背景:&nbsp;<input type="text" name="professBack" id="professBack" placeholder="请输入专业背景"></td>
 <td colspan="2">简介:&nbsp;<textarea name="intro" id="intro" rows="" cols="40"></textarea></td>
 </tr>
 <tr>
 <td colspan="4">请选择角色:&nbsp;
 <select name="roleId" id="roleId">
 <option value="0">请选择</option>
<c:forEach items="${requestScope.roleList }" var="role">
<option value="${role.roleId }">${role.roleName}</option>
</c:forEach>
 </select>
</td>
</tr>
<tr>
<td colspan="4" id="special">
请选择领域:&nbsp;
<c:forEach items="${requestScope.areaList }" var="area">
<input type="checkbox" name="areas" id="areas" value="${area.areaId }">${area.areaName}
</c:forEach>
 &emsp;请选择咨询费用:&nbsp;
 <select name="expense" id="expense">
 <option value="0">请选择</option>
 <option value="200">200</option>
 <option value="500">500</option>
 <option value="800">800</option>
 <option value="1000">1000</option>
 </select>
</td>
</tr>
<tr>
<td colspan="4">
<a href="javascript:void(0)" onclick="add()">新增</a>&emsp;<a href="${pageContext.request.contextPath}/UserServlet?userAction=list">返回</a>
</td>
</tr>

</table>
</div>
</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function add(){
	if($("#userName").val()==""){
		alert("用户名不得为空！");
	}else if($("#password").val()==""){
		alert("密码不得为空！");
	}else if($("#school").val()==""){
		alert("毕业院校不得为空！");
	}else if($("#professor").val()==""){
		alert("职称不得为空！");
	}else if($("#realName").val()==""){
		alert("真实姓名不得为空！");
	}else if($("#professBack").val()==""){
		alert("专业背景不得为空！");
	}else if($("#intro").val()==""){
		alert("简介不得为空！");
	}else if($("#roleId").val()==0){
		alert("请选择角色！");
	}else{
		var areas="";
		$("input:checkbox:checked").each(function(){
			areas+=this.value+",";
		})
		
		$.ajax({
			url:"${pageContext.request.contextPath}/UserServlet?userAction=addAdminUser",
			type:"post",
			data:{"userName":$("#userName").val(),"password":$("#password").val(),
				"school":$("#school").val(),"professor":$("#professor").val(),
				"realName":$("#realName").val(),"professBack":$("#professBack").val(),
				"intro":$("#intro").val(),"roleId":$("#roleId").val(),
				"expense":$("#expense").val(),"areas":areas},
			async:true,
			dataType:"JSON",
			success:function (data){
				console.log(data);
				if(data.id==1){
	               window.alert(data.msg);     
			     }else if(data.id==2||data.id==3){
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
$(document).ready(function(){
	$("#special").hide();
	//显示和隐藏
	$("#roleId").change(function(){
		if($("#roleId").val()==2){
			$("#special").show();
		}else{
			$("#special").hide();
		}
		
	});
});
</script>
</html>