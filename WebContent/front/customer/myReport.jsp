<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的报告列表</title>
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
<form class="topForm" name="myReportform"
				action="${pageContext.request.contextPath}/AssReportServlet"	method="post">
		<input type="hidden" name="assAction" value="myReportList">		
		<input type="hidden" name="currentPageNum" id="currentPageNum" value="1">			
</form>
 <div class="list">
  <table border="1" cellpadding="8" style="border-collapse: collapse;">
  <tr>
 <th colspan="4">我的报告列表</th>
</tr>
<!-- assReportList -->
<c:choose>
<c:when test="${not empty requestScope.page.records}">
<c:forEach items="${requestScope.page.records}" var="myReport">
<tr>
 <td colspan="4">
  <a href="${pageContext.request.contextPath}/AssReportServlet?assAction=reportResult&standId=${myReport.STAND_ID}">
  ${myReport.ASS_TIME }<br>
          评测报告</a>
 </td>
</tr>
</c:forEach>
<tr>
								<td colspan="4" align="center">
									共&nbsp;${page.totalRecordsNum}&nbsp;条记录,共&nbsp;${page.totalPageNum}&nbsp;页,
									当前第&nbsp;${page.currentPageNum}&nbsp;页
									<a href="javascript:void(0)" onclick="pageMethod(1)">首页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.prevPageNum})">上一页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.nextPageNum})">下一页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.totalPageNum})">尾页</a>
								</td>
							</tr>
					<tr>
					<td colspan="4" align="center">
					 <a href="${pageContext.request.contextPath}/front/customer/index.jsp">返回</a>
					</td>
					 </tr>		
</c:when>
</c:choose>
 
  </table>
 </div>
 </div>
 </div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function pageMethod(pageNo) {
    document.getElementById("currentPageNum").value = pageNo;
    document.myReportform.submit();
}
</script>

</html>