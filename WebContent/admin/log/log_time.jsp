<%@page import="com.cyzy.bean.Log"%>
<%@page import="com.cyzy.bean.CountData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>根据时间来查询日志</title>
<style>
        body {
            margin: 0;
            padding: 0;
        }
        
        .wrapper {
            width: 100vw;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        
        .content {
            width: 1000px;
            height: 600px;
            display:flex;
            flex-direction:column;
        }
        .content-top{
            width: 100%;
            height: 100px;     
            display:flex;
            justify-content:center; 
            align-items:center;
        }
        .week,.year{
          font-size: 18px;
          padding: 8px 16px;
          background-color: transparent;
          border: 1px solid #ccc;
          border-radius: 12px;
          outline: none;
          
        }
        .week:hover,.year:hover{
          cursor: pointer;
          color: white;
          background-color: red;
          border: 1px solid white;
        }
        .year{
        margin-left: 16px;
        }
        .content-bottom{
            width: 100%;
            height: 500px; 
            flex-grow:1;
            display:flex;   
            justify-content:center;         
        }
       
    </style>
<!-- 引入echarts.js -->
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
<body>
	 <div class="wrapper">
	 <div class="content">
	    <div class="content-top">
	    
		<form action="${pageContext.request.contextPath}/LogServlet"
			method="post">
			<input type="hidden" name="logAction" value="weekList"> 
			<input type="submit" class="week" value="本周">
		</form>
		<form action="${pageContext.request.contextPath}/LogServlet"
			method="post">
			<input type="hidden" name="logAction"  value="yearList"> 
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
        <%List<Log> logList = (List<Log>) request.getAttribute("logList");
			for (Log log : logList) {%>
          titles.push('<%=log.getTitle()%>');
		datas.push(<%=log.getCount()%>);
	<%}%>
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '第一个 ECharts 实例'
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
				name : '销量',
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