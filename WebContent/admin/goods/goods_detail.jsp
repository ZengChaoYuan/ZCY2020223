<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品详情</title>
<style type="text/css">
.wrapper{
  width:250px;
  height: auto;
  margin: 0 auto;
}
.wrapper>p{
  font-size:18px;
}

</style>
</head>
<body>
<div class="wrapper">
<h1>商品详情</h1>
<input type="hidden" name="goodsId" id="goodsId" >
<p>商品名称:&nbsp;&nbsp;${requestScope.goods.goodsName}</p>
<p>商品大类别:&nbsp;&nbsp;${requestScope.goods.goodsLClass}</p>
<p>商品大类别:&nbsp;&nbsp;${requestScope.goods.goodsSClass}</p>
<p>状态:&nbsp;&nbsp;${requestScope.goods.status==0?"未发布":"已发布"}</p>
<a href="${pageContext.request.contextPath}/GoodsServlet?goodsAction=list">返回商品列表</a>
</div>
</body>
</html>