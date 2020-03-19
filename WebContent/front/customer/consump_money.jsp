<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户充值</title>
</head>
<body>
<h1 align="center">客户充值</h1>
<p style="text-align: center;"> 
请选择充值金额:&emsp;
<select name="consumpMoney" id="consumpMoney">
<option value="0">请选择</option>
<option value="500">500</option>
<option value="1000">1000</option>
<option value="1500">1500</option>
</select>  </p>
<p style="text-align: center;"><input type="button" onclick="consumpMoney(${sessionScope.loginCustomer.customerId})" value="充值">&emsp;
<a href="${pageContext.request.contextPath}/MyAccountServlet?accountAction=list">返回</a> </p>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function consumpMoney(obj){
	//alert(obj);
	if($("#consumpMoney").val()==0){
		alert("请选择金额");
		return;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/MyAccountServlet?accountAction=consumpMoney",
		type:"post",
		data:{"customerId":obj,"consumpMoney":$("#consumpMoney").val()},
		async:true,
		dataType:"JSON",
		success:function(data){
			if(data.id==1){
				window.alert(data.msg);
				window.location.href="${pageContext.request.contextPath}/MyAccountServlet?accountAction=list";
			}else if(data.id==2){
				window.alert(data.msg);
			}
		},
		error:function(data){
			alert("出错啦!");
		}
	})
	
	
}

</script>
</html>