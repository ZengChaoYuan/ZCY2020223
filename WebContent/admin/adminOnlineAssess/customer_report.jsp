<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户评测报告</title>
</head>
<body>
<h1>客户评测报告</h1>
评测结果:${requestScope.singAss.ASS_RESULT }<br>
报告内容:${requestScope.singAss.REPORT_CONTENT }<br>
<a href="${pageContext.request.contextPath}/AssReportServlet?assAction=userQueryReport">返回</a>
</body>
</html>