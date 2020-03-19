<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在线评估</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/TitleServlet?titleAction=answer" method="post">
 
   <h1 align="center">在线评估</h1>
      <p style="text-align: center;">领域:
      <select name="areaId" id="areaId">
<c:forEach items="${requestScope.areaList }" var="area">
<option value="${area.areaId }">${area.areaName}</option>
</c:forEach>
</select>
</p>  
<p style="text-align: center;">
 <input  type="submit" value="开始评估">
</p>
 </form>
</body>
</html>