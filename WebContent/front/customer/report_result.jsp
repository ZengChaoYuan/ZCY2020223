<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评测报告</title>
</head>
<body>
<h1>评测报告</h1>
评测结果:${requestScope.stand.assResult }<br>
报告内容:${requestScope.stand.reportContent }
<a href="${pageContext.request.contextPath}/AssReportServlet?assAction=myReportList">返回</a>
</body>
</html>