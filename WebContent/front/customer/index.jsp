<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户首页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer_index.css">
</head>
<body>


<div class="wrapper">
        <div class="head">
            <div class="head-left">
                                    用户端
            </div>
            <div class="head-right">
                <span>欢迎你，${sessionScope.loginCustomer.customerName}用户</span>
               <!--  <a href="${pageContext.request.contextPath}/LoginServlet?loginAction=loginOut">退出</a> -->
                <a>退出</a>
            </div>
        </div>
        <ul class="content">
             <li><a style="margin-top:300px;" onclick="yuyue()">我要预约</a></li>
             <li><a style="margin-top:450px;">我的报告</a></li>
             <li><a style="margin-top:320px;">在线评估</a></li>
             <li><a style="margin-top:180px;">咨询师库</a></li>
             <li><a style="margin-top:400px;">我的账户</a></li>
        </ul>
    </div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
 function yuyue(){
	 alert("我要预约");
 }

</script>
</html>