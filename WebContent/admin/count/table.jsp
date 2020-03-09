<%@page import="com.cyzy.bean.CountData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学习图表</title>
<!-- 引入echarts.js -->
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
<body>
 
  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
 // 步骤:1.引入图形JS库(基于JQuery),2.创建一个div,用于放置图形,3.写JS脚本进行初始化
        var titles=[];
        var datas=[];
        <%
        List<CountData> datas=( List<CountData>)request.getAttribute("datas");
        
        for(CountData data:datas){
        	
       
        %>
          titles.push('<%=data.getTitle()%>');
          datas.push(<%=data.getCount()%>);
        <%
         }
        %>
    
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
 
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '第一个 ECharts 实例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: titles
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: datas,
                
                itemStyle:{
                	normal:{
                		label:{
                			show:true,//开启显示
                			position:'top',//上方显示
                			textStyle:{//样式
                				color:'red',
                				fontSize:16
                			}
                		}
                	}
                }
            
            }]
        };
 
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
</html>