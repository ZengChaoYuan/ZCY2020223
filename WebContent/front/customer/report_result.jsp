<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评测报告</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
<style type="text/css">
.buju{
 display: flex;
 flex-direction: column;
}

</style>
</head>
<body>
<div class="wrapper">
<div class="contentArea">
<div class="list buju">
<h1>评测报告</h1>
评测结果:&nbsp; ${requestScope.stand.assResult}<br>
<br>
报告内容:<br>
<br>
<textarea rows="10" cols="10">
${requestScope.stand.reportContent}
</textarea>
<br>
<a href="${pageContext.request.contextPath}/AssReportServlet?assAction=myReportList">返回</a>
</div>
</div>
</div>
</body>
</html>