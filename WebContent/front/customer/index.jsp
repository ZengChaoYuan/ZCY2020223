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
                <a href="${pageContext.request.contextPath}/LoginServlet?loginAction=frontLoginOut">退出</a>
            </div>
        </div>
        <ul class="content">
             <li><a style="margin-top:250px;" href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=myPreOrderList">我要预约</a></li>
             <li><a style="margin-top:400px;" href="${pageContext.request.contextPath}/AssReportServlet?assAction=myReportList">我的报告</a></li>
             <li><a style="margin-top:270px;" href="${pageContext.request.contextPath}/AreaServlet?areaAction=list">在线评估</a></li>
             <li><a style="margin-top:350px;" href="${pageContext.request.contextPath}/MyAccountServlet?accountAction=list">我的账户</a></li>
        </ul>
    </div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
</html>