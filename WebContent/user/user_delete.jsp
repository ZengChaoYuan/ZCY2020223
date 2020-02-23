<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>删除当前用户</title>
</head>
<body>
<%int result=(int)request.getAttribute("result");%>

<script type="text/javascript">
if(<%=result>0%>){
	window.alert("删除成功!");
	
}else{
	window.alert("删除失败!");
}

</script>
</body>
</html>