<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎登录心理咨询与诊断平台</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
	<div class="wrapper">
		<div class="content-side">
			<h1>欢迎登录心理咨询与诊断平台</h1>
				  <table cellpadding="10">
   <tr>
    <td>用户名:</td>
    <td><input type="text" class="input" placeholder="请输入用户名" name="customerName"
						id="customerName"></td>
  </tr>
  <tr>
    <td>密码:</td>
    <td><input type="password" class="input" placeholder="请输入密码" name="password"
						id="password"></td>
  </tr>
   <tr>
    <td>验证码:</td>
    <td class="zhixu"><input type="text" class="input" style="width: 100px;" name="code" id="code"
						placeholder="请输入验证码" />
						<img src="${pageContext.request.contextPath}/ImageServlet"
						id="imageCode"  onclick="getCode()" />
					<input type="button" id="imageCode" class="imageCode" onclick="getCode()" value="看不清，换一张">	</td>
  </tr>
  <tr>
  <td colspan="2" style="text-align: center;"><input type="button" id="loginBtn" value="登录"><a href="${pageContext.request.contextPath}/front/register.jsp">快速注册</a></td>
  </tr>
</table>
		
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">  
	function getCode() {
	    var image = document.getElementById("imageCode");
		image.src = "${pageContext.request.contextPath}/ImageServlet?"
				+ Math.random();
	}
	
	
	 $(document).ready(function() {
	        var $loginBtn = $("#loginBtn");
	        
	        $loginBtn.click(function() {
	        	 if($("#customerName").val()==""){ 
	        		 window.alert("用户名不得为空");	 
	        		 return;
	        	 }else if($("#password").val()==""){
	        		 window.alert("密码不得为空");	        		 
	        		 return;
	        	 }else if($("#code").val()==""){
	        		 window.alert("验证码不得为空");
	        		 return;
	        	 }else{
	        		 $.ajax({
		 	                url: "${pageContext.request.contextPath}/CustomerLoginServlet",
		 	                type: "post",
		 	                data: {
		 	                    "customerName": $("#customerName").val(),
		 	                    "password": $("#password").val(),
		 	                    "code": $("#code").val()
		 	                },
		 	                async: true, //是否异步提交,默认为true
		 	                dataType: "JSON", //提交的数据格式为JSON,返回的数据格式也是json
		 	                success: function(data) {
		 	                	console.log(data);
		 	                	if(data.id==1 || data.id==2||data.id==3||data.id==4){
		 	                		 window.alert(data.msg);
		 	                		 getCode();
		 	                	}else if(data.id==5){
		 	                		 window.alert(data.msg);
		 	                		window.location.href=data.location;
		 	                	}
		 	                },
		 	                error: function(data) {
		 	                    alert("请联系管理员");
		 	                }

		 	            })   		 
	        	 }
	        	 
	        	 
	        });

	    });
	
	
	
</script>
</html>