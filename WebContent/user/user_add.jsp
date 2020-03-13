<%@page import="com.cyzy.bean.Role"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增用户</title>
<style type="text/css">
form{
 width:500px;
 margin: 0 auto;
}
form input,form select{
 margin: 10px 5px 10px 10px;
}

</style>
</head>
<body>
<h1 align="center">新增用户</h1>
<form action="${pageContext.request.contextPath}/UserServlet?userAction=add" method="post">
<input type="hidden" name="userId" id="userId" value="" >
用户名: <input type="text" name="userName" id="userName" placeholder="请输入用户名" value="">
<input type="button" id="checkRepeat" value="检验是否重复">
<span id="message"></span><br>
密码: <input type="password" name="password" id="password" placeholder="请输入密码" value=""><br>
真实姓名: <input type="text" name="realName" id="realName" placeholder="请输入真实姓名" value=""><br>
性别: <input type="radio" name="sex" value="1">男
<input type="radio" name="sex" value="0">女
<br>
出生日期:<input type="date" name="birthday" id="birthday" value=""><br>
用户角色:

<select name="roleId">
<c:forEach items="${requestScope.roleList }" var="role">
<option value="${role.roleId }">${role.roleName}</option>
</c:forEach>
</select>

<br>
<input type="submit" value="提交">&emsp;<input type="reset" value="重置"><a href="${pageContext.request.contextPath}/UserServlet?userAction=list">返回用户列表</a>
</form>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var $checkRepeat=$("#checkRepeat");
	//检测用户名是否重复
	$checkRepeat.click(function(){
		 if($("#userName").val()==""){ 
			 $("#message").text("用户名不得为空"); 
			 $("#message").css("color","red");
    	 }else{
    		 
    		 $.ajax({
    				url:"${pageContext.request.contextPath}/UserServlet",
    				type:"post",
    				data:{"userName":$("#userName").val(),"userAction":"add"},
    				async:true,
    				dataType:"JSON",
    				success:function(data){
    					$("#message").text(data.msg);
    					$("#message").css("color","red");
    				},
    				error:function(data){
    					alert("请联系管理员!");
    				}
    				
    				
    			});
    		 
    	 }
		
	});	
	
})


</script>
</html>