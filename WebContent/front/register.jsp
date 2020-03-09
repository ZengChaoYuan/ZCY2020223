<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户注册页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
</head>
<body>
 <div class="wrapper">
        <div class="content">
           <h1 align="center" >用户注册</h1>
          <form action="${pageContext.request.contextPath}/CustomerServlet?customerAction=register" method="post">
           <table  cellpadding="10">
            <tr>
             <td>用户名:</td>
             <td><input type="text" name="customerName" id="customerName"> </td>
             <td>性别:</td>
             <td> <input type="radio" name="sex" value="1">男&nbsp;
             <input type="radio" name="sex" value="0">女 </td>
            </tr>
             <tr>
             <td>密码:</td>
             <td><input type="password" name="password" id="password"> </td>
             <td>年龄:</td>
             <td><input type="text" name="age" id="age"></td>
            </tr>
             <tr>
             <td>确认密码:</td>
             <td><input type="password" name="rePassword" id="rePassword"> </td>
             <td>手机号:</td>
             <td><input type="text" name="tel" id="tel"></td>
            </tr>
             <tr>
             <td colspan="4">通讯地址&emsp;<input type="text" name="address" id="address" style="width: 80%;"></td>
            
            </tr>
             <tr>
             <td colspan="4" style="text-align: center;"><input type="submit" class="registerBtn"  value="注册"><input type="button" class="backLogin" onclick="back()" value="返回"></td>
            
            </tr>
           </table>
          </form>
        </div>
    </div>
</body>
<script type="text/javascript">
 function back(){
	 window.location.href="${pageContext.request.contextPath}/front/login.jsp";
 }
</script>
</html>