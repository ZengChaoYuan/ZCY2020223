<%@page import="com.cyzy.bean.CustomerStatis"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>按周查询注册用户</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer_statis.css">
<!-- 引入echarts.js -->
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
<body>
 <div class="wrapper">
	 <div class="content">
	    <div class="content-top">
	    
		<form action="${pageContext.request.contextPath}/CustomerServlet"
			method="post">
			<input type="hidden" name="customerAction" value="queryByWeek"> 
			<input type="submit" class="week" value="本周">
		</form>
		<form action="${pageContext.request.contextPath}/CustomerServlet"
			method="post">
			<input type="hidden" name="customerAction"  value="queryByMonths"> 
			<input	type="submit" class="months" value="本月">
		</form>
		<form action="${pageContext.request.contextPath}/CustomerServlet"
			method="post">
			<input type="hidden" name="customerAction"  value="queryByYear"> 
			<input	type="submit" class="year" value="近半年">
		</form>
		</div>
		<div class="content-bottom">
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		<div id="main" style="width: 800px; height: 400px; margin-top: 40px;"></div>
		</div>
		</div>
     </div>
     <script type="text/javascript">
 // 步骤:1.引入图形JS库(基于JQuery),2.创建一个div,用于放置图形,3.写JS脚本进行初始化
        var titles=[];
        var datas=[];
        <%List<CustomerStatis> customerList = (List<CustomerStatis>) request.getAttribute("customerList");
			for (CustomerStatis customerStatis : customerList) {%>
          titles.push('<%=customerStatis.getTitle()%>');
		datas.push(<%=customerStatis.getCount()%>);
	<%}%>
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '用户统计'
			},
			tooltip : {},
			legend : {
				data : [ '销量' ]
			},
			xAxis : {
				data : titles
			},
			yAxis : {},
			series : [ {
				type : 'bar',
				data : datas,

				itemStyle : {
					normal : {
						label : {
							show : true,//开启显示
							position : 'top',//上方显示
							textStyle : {//样式
								color : 'red',
								fontSize : 16
							}
						}
					}
				}

			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>
</body>
</html>