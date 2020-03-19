<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我要预约</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/title_list.css">
<style type="text/css">
.geju{
 width: 60vw;
 height:80vh;
 margin: 0 auto;
}
.left{
  text-align: left;
  text-indent: 110px;
}
</style>
</head>
<body>
<div class="wrapper">
<div class="contentArea">
<div class="list geju">
<table border="1" cellpadding="2" style="border-collapse: collapse;">
  <tr>
  <th colspan="4">我要预约</th>
  </tr>
   <tr>
  <th>咨询领域:</th>
  <th>
   <select id="areaId" name="areaId">
        <option value="0">请选择</option>
        <c:forEach items="${requestScope.areaList }" var="area">
        <option value="${area.areaId }">${area.areaName}</option>
        </c:forEach>
   </select>
   </th>
  <th>心理咨询师:</th>
  <th>
   <select id="userId" name="userId">
        <option value="0">请选择</option>
   </select>
  </th>
  </tr>
  <tr>
   <td colspan="4" id="userInfo">  
            咨询师详情
   </td>
   </tr>
  <tr>
  <td class="left" colspan="1">可预约时间:</td>
  <td class="left" colspan="3"><input type="date" name="orderDate" id="orderDate"></td>
  </tr>
  <tr>
  <td colspan="4">
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
  <input type="checkbox" name="hours" id="hours" value="24">24<br>  </td>
  </tr>
  <tr>
  <td class="left" colspan="4">问题描述:</td>
  </tr>
  <tr>
  <td colspan="4">
  <textarea style="width:60%;height: 40px;" name="problemDesc" id="problemDesc"></textarea>
  </td>
  </tr>
  <tr>
  <td colspan="4">
   <input type="button" onclick="submit()" value="提交">
  </td>
  </tr>
</table>
</div>
</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
//提交
function submit(){
	alert("提交");
	var hours="";
	$("input:checkbox:checked").each(function(){
		hours+=this.value;
	})
	$.ajax({
		url:"${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=wantPreOrder",
		type:"post",
		data:{"userId":$("#userId").val(),"problemDesc":$("#problemDesc").val(),"preorderPrice":$("#preorderPrice").val(),
			"areaId":$("#areaId").val(),"orderDate":$("#orderDate").val(),"hours":hours},
		async:true,
		dataType:"JSON",
		success:function(data){
			console.log(data);
			if(data.id==1){
				window.alert(data.msg);
				window.location.href="${pageContext.request.contextPath}/PreOrderServlet?preOrderAction=myPreOrderList";
			}else if(data==2){
				window.alert(data.msg);
			}
		},
		error:function(data){
			alert("出错啦");
		}
	})
	
}


//时间改变查询可预约时间
$("#orderDate").change(function(){
	//把所有的复选框的选中状态清空
	$("input[name='hours']").each(function(){
		$(this).hide();
	});
	//查询出已经保存在数据库中的可预约时间
	$.ajax({
		url:"${pageContext.request.contextPath}/OrderTimeServlet",
		type:"post",
		data:{"orderTimeAction":"customerQueryOrderTime","orderDate":$("#orderDate").val(),
			"consultId":$("#consultId").val()},
		async:true,
		dataType:"JSON",
		success:function(data){
			$("input[name='hours']").each(function(){
				var val=this.value;
				for(var i=0;i<data.length;i++){
					if(val==data[i].orderhour){
						$(this).show();
					}
				}
			});
		
		},
		error:function(data){
			alert("请联系管理员！");
		}
	})
	
});
//通过咨询师查出下面的信息
$("#userId").change(function(){
	$.ajax({
		 url:"${pageContext.request.contextPath}/PreOrderServlet",
		 type:"post",
		 data:{"preOrderAction":"activeQueryUserInfo","userId":this.value},
		 async:true,
		 dataType:"JSON",
		 success:function(data){
			 console.log(data.areaList);
			 var $userInfo=$("#userInfo");
			 $userInfo.empty();
			 $userInfo.append("<input type='hidden' name='consultId' id='consultId' value='"+data.singUser.USER_ID+"'>");
			 $userInfo.append("<span>"+"咨询师姓名: "+data.singUser.USER_NAME+"</span><br>");
			 $userInfo.append("<span>"+data.singUser.SCHOOL+"</span>&emsp;");
			 $userInfo.append("<span>"+data.singUser.PROFESSOR+"</span><br>");
			 $userInfo.append("<span>"+"擅长领域:"+"</span><br>");
			 var list=data.areaList;
			 for(var i=0;i<list.length;i++){
				 $userInfo.append("<span>"+list[i].AREA_NAME+"</span><br>");
			 }
			 $userInfo.append("<span>"+"服务价格: "+data.singUser.PRE_EXPENSE+"/次"+"</span><br>");
			 $userInfo.append("<span>"+"专业背景: "+data.singUser.INTRO+"</span>");
		 },
		 error:function(data){
			 alert("出错啦");
		 }
	 });
	
});
//通过领域查出下面的咨询师
 $("#areaId").change(function(){
	 if(this.value==0){
		 $("#userId").empty();
		 $("#userId").append("<option value='0'>请选择</option>");
	 }
	 $.ajax({
		 url:"${pageContext.request.contextPath}/PreOrderServlet",
		 type:"post",
		 data:{"preOrderAction":"queryAreaDownUser","areaId":this.value},
		 async:true,
		 dataType:"JSON",
		 success:function(data){
			 console.log(data);
			 var $userId=$("#userId");
			 $userId.empty();
			 $userId.append("<option value='0'>请选择</option>");
			 for(var i=0;i<data.length;i++){
				 $userId.append("<option value='"+data[i].USER_ID+"'>"+data[i].USER_NAME+"</option>");
			 }
		 },
		 error:function(data){
			 alert("出错啦");
		 }
	 });
 });

</script>
</html>