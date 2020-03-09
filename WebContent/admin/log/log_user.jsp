<%@page import="com.cyzy.bean.LogInf"%>
<%@page import="com.cyzy.bean.Log"%>
<%@page import="com.cyzy.bean.CountData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>根据用户来查询日志</title>
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
        .query{
          font-size: 18px;
          padding: 8px 16px;
          background-color: transparent;
          border: 1px solid #ccc;
          border-radius: 12px;
          outline: none;
        }
        .query:hover{
          cursor: pointer;
          color: white;
          background-color: red;
          border: 1px solid white;       
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
   <input type="hidden" name="logAction" value="userList">
     统计时间:&emsp;<input type="date" name="startTime" value="${param.startTime }"> - <input type="date" name="endTime" value="${param.endTime }">
  &emsp;<input type="submit" class="query" value="查询">
  </form>
</div>

  <div class="content-bottom">
  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    </div>
    </div>
    </div>
    <script type="text/javascript">
 // 步骤:1.引入图形JS库(基于JQuery),2.创建一个div,用于放置图形,3.写JS脚本进行初始化
        var titles=[];
        var datas=[];
        <%
        List<LogInf> logInfList=(List<LogInf>)request.getAttribute("logInfList");       
        for(LogInf logInf:logInfList){        	       
        %>
          titles.push('<%=logInf.getAdminName()%>');
          datas.push(<%=logInf.getCount()%>);
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