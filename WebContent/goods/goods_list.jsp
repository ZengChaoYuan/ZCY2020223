<%@page import="com.cyzy.bean.Goods"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示所有商品</title>
<style type="text/css">
body,
ul,
ol,
li,
p {
    margin: 0;
    padding: 0;
    list-style: none;
}

.wrapper {
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
}

.contentArea {
    width: 90%;
    height: 700px;
    display: flex;
    flex-direction: column;
}

.contentArea>form {
    width: 100%;
    height: 25%;
    border: 1px solid #666;
    border-radius: 12px;
}

.contentArea>form>p {
    text-indent: 20px;
    padding-top: 10px;
    font-size: 18px;
    font-weight: bold;
}

.contentArea>form>p:nth-child(2) {
    display: flex;
}

.contentArea>form>p:nth-child(2)>span:nth-child(2) {
    margin-left: 10px;
}

.contentArea>form>p:last-child {
    padding-top: 20px;
    text-indent: 20px;
}

.contentArea>form>p:last-child>input {
    margin-left: 20px;
    padding: 6px 18px;
    border: 1px solid #ccc;
    background-color: transparent;
    border-radius: 10px;
    outline: none;
    font-weight: bold;
}

.contentArea>form>p:last-child>a {
    margin-left: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    text-decoration: none;
    padding: 7px 18px;
    font-weight: bold;
    color: black;
    font-size: 14px;
}

.contentArea>form>p:last-child>input:nth-child(1) {
    margin-left: 0px;
}

.contentArea>form>p:last-child>input:hover,
.contentArea>form>p:last-child>a:hover {
    cursor: pointer;
    background-color: red;
    border: 1px solid transparent;
    color: white;
}

.contentArea>.list {
    width: 100%;
    margin-top: 2%;
    height: 73%;
    border: 1px solid #666;
    border-radius: 12px;
}

.list>p:nth-child(1) {
    text-indent: 20px;
    margin-top: 10px;
    font-size: 18px;
    font-weight: bold;
}

.list>table {
    width: 96%;
    height: 100px;
    margin: 10px auto 0;
}

table tr th {
    text-align: center;
    height: 40px;
    line-height: 40px;
    font-size: 24px;
}

table tr td {
    text-align: center;
    height: 32px;
    line-height: 32px;
}

table tr td a {
    text-decoration: none;
}

.page {
    width: 96%;
    margin: 10px auto 0;
    height: 32px;
    font-size: 18px;
    font-weight: bold;
    text-align: right;
}

.page a {
    padding: 0 8px;
}

</style>
</head>
<body>
<div class="wrapper">
		<div class="contentArea">
			<form action="">
				<p>查询区域</p>
				<p>
					<span>商品分类名称: <input type="text" width="200px"></span> <span>上级商品分类:
						<select style="width: 100px;">
							<option>请选择</option>
					</select>
					</span>
				</p>
				<p>
					<input type="button" value="查询"><input type="reset"
						value="重置"> <a href="./add.html" target="adminMainContent">新增</a>
				</p>
			</form>
			<div class="list">
				<p>列表区域</p>
				<table border="1" style="border-collapse: collapse;">
					<tr>
						<th>商品ID</th>
						<th>商品名称</th>
						<th>操作</th>
					</tr>
					<%
						//先把数据取出来，通过request对象（内置对象，不需要我们去创建，其实是httpservletRequest对象的实例）
						List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
							for(Goods goods:goodsList){
					%>
					<tr>
						<td><%=goods.getGoodsId()%></td>
						<td><%=goods.getGoodsName()%></td>
						<td>
						<a href="http://localhost:8080/JF190902/GoodsServlet?goodsAction=updateBefore&goodsId=<%=goods.getGoodsId()%>">修改</a>
						<a href="http://localhost:8080/JF190902/GoodsServlet?goodsAction=delete&goodsId=<%=goods.getGoodsId()%>">删除</a> 
						<a href="http://localhost:8080/JF190902/GoodsServlet?goodsAction=detail&goodsId=<%=goods.getGoodsId()%>">查看详情</a>
						</td>
					</tr>

					<%
						}
					%>
				</table>
				<div class="page">
					<p>
						<a href="#">首页</a>|<a href="#">上一页</a>|<a href="#">下一页</a>|<a
							href="#">尾页</a> 每页显示<select style="width: 40px; margin: 0 8px;">
							<option>10</option>
						</select>条 当前第<select style="width: 40px; margin: 0 8px;">
							<option>1</option>
						</select>页 总共<span style="padding: 0 5px;">4</span>页 总共<span
							style="padding: 0 5px;">40</span>条记录
					</p>
				</div>

			</div>
		</div>

	</div>
</body>
</html>