<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评估答题</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
</head>
<body>
<div class="wrapper">
<div class="contentArea">

<form class="list" action="${pageContext.request.contextPath}/TitleServlet?titleAction=countScope" method="post">

<table border="1" cellpadding="8" style="border-collapse: collapse;">
<tr>
<th colspan="2">评估答题</th>
</tr>
                     <tr>
						<th>题目</th>
						<th>选项</th>
					</tr>
<c:choose>
<c:when test="${not empty requestScope.titleList }">
<c:forEach items="${requestScope.titleList }" var="title">
 <tr>
  <td>${title.titleId}.${title.titleName}</td>
  <td>
  <c:forEach items="${title.items }" var="item">
      <input type="radio">${item.itemName }
  </c:forEach>
  </td>
 </tr>
</c:forEach>
<tr>
<td colspan="2"><input type="submit" value="提交"> </td>
</tr>
 
</c:when>
			            <c:otherwise>
							<tr>
								<td colspan="2">该领域查无数据，<a href="${pageContext.request.contextPath}/AreaServlet?areaAction=list">返回</a></td>
							</tr>
						</c:otherwise>

</c:choose>
</table> 

</form>


</div>
</div>
</body>
</html>