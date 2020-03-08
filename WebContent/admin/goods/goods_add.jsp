<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增商品</title>
<style type="text/css">
form{
 width:300px;
 margin: 0 auto;
}
form input,form select{
 margin: 10px;
}

</style>
</head>
<body>
<h1 align="center">新增商品</h1>
<form action="${pageContext.request.contextPath}/GoodsServlet?goodsAction=add" method="post">

商品名称: <input type="text" name="goodsName" id="goodsName"> 
<br>
商品大类别: <input type="text" name="goodsLClass" id="goodsLClass">
<br> 
商品小类别: <input type="text" name="goodsSClass" id="goodsSClass"> 
<br>
状态: <input type="radio" name="status" value="0">未发布
<input type="radio" name="status" value="1">已发布
<br>
<input type="submit" value="提交">&emsp;
<br>
<a href="${pageContext.request.contextPath}/GoodsServlet?goodsAction=list">返回用户列表</a>
</form>

</body>
</html>