<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>预约时间设置</title>
</head>
<body>
  <input type="hidden" name="userId" id="userId" value="${sessionScope.loginUser.userId}">
  请选择日期：<input type="date" name="orderDate" id="orderDate"><br>
  <input type="checkbox" name="hours" id="hours" value="1">1
  <input type="checkbox" name="hours" id="hours" value="2">2
  <input type="checkbox" name="hours" id="hours" value="3">3
  <input type="checkbox" name="hours" id="hours" value="4">4
  <input type="checkbox" name="hours" id="hours" value="5">5
  <input type="checkbox" name="hours" id="hours" value="6">6<br>
   <input type="checkbox" name="hours" id="hours" value="7">7
  <input type="checkbox" name="hours" id="hours" value="8">8
  <input type="checkbox" name="hours" id="hours" value="9">9
  <input type="checkbox" name="hours" id="hours" value="10">10
  <input type="checkbox" name="hours" id="hours" value="11">11
  <input type="checkbox" name="hours" id="hours" value="12">12<br>
  <input type="checkbox" name="hours" id="hours" value="13">13
  <input type="checkbox" name="hours" id="hours" value="14">14
  <input type="checkbox" name="hours" id="hours" value="15">15
  <input type="checkbox" name="hours" id="hours" value="16">16
  <input type="checkbox" name="hours" id="hours" value="17">17
  <input type="checkbox" name="hours" id="hours" value="18">18<br>
  <input type="checkbox" name="hours" id="hours" value="19">19
   <input type="checkbox" name="hours" id="hours" value="20">20
    <input type="checkbox" name="hours" id="hours" value="21">21
  <input type="checkbox" name="hours" id="hours" value="22">22
  <input type="checkbox" name="hours" id="hours" value="23">23
  <input type="checkbox" name="hours" id="hours" value="24">24<br>
  <input type="button" onclick="save()" value="保存">
  
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
//查询
$("#orderDate").change(function(){
	//把所有的复选框的选中状态清空
	$("input[name='hours']").each(function(){
		$(this).prop("checked",false);
	});
	//查询出已经保存在数据库中的可预约时间
	$.ajax({
		url:"${pageContext.request.contextPath}/OrderTimeServlet",
		type:"post",
		data:{"orderTimeAction":"queryOrderTime","orderDate":$("#orderDate").val()},
		async:true,
		dataType:"JSON",
		success:function(data){
			$("input[name='hours']").each(function(){
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
	
});


function save(){
	if(confirm("确认保存吗?")){
		var hours="";
		$("input:checkbox:checked").each(function(){
			hours+=this.value+",";
		})
		
		$.ajax({
			url:"${pageContext.request.contextPath}/OrderTimeServlet?orderTimeAction=saveOrderTime",
			type:"post",
			data:{"userId":$("#userId").val(),"orderDate":$("#orderDate").val(),"hours":hours},
			async:true,
			dataType:"JSON",
			success:function(data){
               if(data.id==1){
            	   window.alert(data.msg);
               }else if(data.id==2){
            	   window.alert(data.msg);
            	   return;
               }
			},
			error:function(data){
				window.alert("出错啦");
			}
		});
	}
}



</script>
</html>