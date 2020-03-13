<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>预约时间设置</title>
</head>
<body>
  请选择日期：<input type="date" name="orderDate" id="orderDate">&emsp;<input type="button" onclick="querydate()"  value="查询"><br>
  <input type="checkbox" name="time" value="1">1
  <input type="checkbox" name="time" value="2">2
  <input type="checkbox" name="time" value="3">3
  <input type="checkbox" name="time" value="4">4
  <input type="checkbox" name="time" value="3">5
  <input type="checkbox" name="time" value="4">6<br>
   <input type="checkbox" name="time" value="7">7
  <input type="checkbox" name="time" value="8">8
  <input type="checkbox" name="time" value="9">9
  <input type="checkbox" name="time" value="10">10
  <input type="checkbox" name="time" value="11">11
  <input type="checkbox" name="time" value="12">12<br>
  <input type="checkbox" name="time" value="13">13
  <input type="checkbox" name="time" value="14">14
  <input type="checkbox" name="time" value="15">15
  <input type="checkbox" name="time" value="16">16
  <input type="checkbox" name="time" value="17">17
  <input type="checkbox" name="time" value="18">18<br>
  <input type="checkbox" name="time" value="19">19
   <input type="checkbox" name="time" value="20">20
    <input type="checkbox" name="time" value="21">21
  <input type="checkbox" name="time" value="22">22
  <input type="checkbox" name="time" value="23">23
  <input type="checkbox" name="time" value="24">24
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

	
function querydate(){
		$.ajax({
			url:"${pageContext.request.contextPath}/OrderTimeServlet",
			type:"post",
			data:{"orderTimeAction":"queryOrderTime","orderDate":$("#orderDate").val()},
			async:true,
			dataType:"JSON",
			success:function(data){
				$("input:checkbox").each(function(){
					var val=this.value;
					for(var i=0;i<data.length;i++){
						if(val==data[i].orderhour){
							$(this).prop("checked","true");
						}
					}

				});
			
			},
			error:function(data){
				alert("请联系管理员！");
			}
		})
	}

</script>
</html>