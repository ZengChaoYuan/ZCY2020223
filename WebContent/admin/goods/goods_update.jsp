<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品修改</title>
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
<div class="wrapper">
<h1 align="center">商品修改</h1>
<form action="${pageContext.request.contextPath}/GoodsServlet?goodsAction=update" method="post">
<input type="hidden" name="goodsId" id="goodsId" value="${requestScope.goods.goodsId }">

商品名称: <input type="text" name="goodsName" id="goodsName" value="${requestScope.goods.goodsName}"> 
<br>
商品大类别: <input type="text" name="goodsLClass" id="goodsLClass" value="${requestScope.goods.goodsLClass}">
<br> 
商品小类别: <input type="text" name="goodsSClass" id="goodsSClass" value="${requestScope.goods.goodsSClass}"> 
<br>
状态: <input type="radio" name="status" value="0" ${requestScope.goods.status==0?"checked":""}>未发布
<input type="radio" name="status" value="1" ${requestScope.goods.status==1?"checked":""}>已发布
<br>
<input type="submit" value="提交">&emsp;
<a href="${pageContext.request.contextPath}/GoodsServlet?goodsAction=list">返回商品列表</a>
</form>

</div>
</body>
</html>