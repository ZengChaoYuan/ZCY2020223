<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的报告列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
<div class="wrapper">
<div class="contentArea">
 <div class="list">
  <table border="1" cellpadding="8" style="border-collapse: collapse;">
  <tr>
 <th colspan="4">我的报告列表</th>
</tr>
<!-- assReportList -->
<c:choose>
<c:when test="${not empty requestScope.assReportList }">
<c:forEach items="${requestScope.assReportList }" var="myReport">
<tr>
 <td colspan="4">
  <a href="${pageContext.request.contextPath}/AssReportServlet?assAction=reportResult&standId=${myReport.standId}">${myReport.assTime }<br>
          评测报告</a>
 </td>
</tr>
</c:forEach>
</c:when>
</c:choose>
 <tr>
 <th colspan="4">分页</th>
</tr>
  </table>
 </div>
 </div>
 </div>
</body>
</html>