<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
        <title>数据分析</title>
       <style type="text/css">
           #menu{
               background-color: #363636;
	           width: 100%;
	           height: 100px;
               margin: 0px;
           }
           /*设置导航条文字属性*/
           ul#menu li {
	          /*设置文字的格式*/
	           display: inline-block;
	           float: left; 
               margin-left:175px;
               margin-top: 40px;
           }
           a{
               font-family:'仿宋';
	           font-size:18px;
               color: white;
               background: transparent;
               text-decoration : none;
           }
          
           #userUploadNumber{
               margin-top:0px;
               margin-left: 0px;
           }
           #dayUploadNumber{
               margin-top: 0px;
               margin-right: 0px;
           }
           #monthUploadNumber{
               margin-top: 0px;
           }
           
       </style>
    </head>
 <body>
        <div>
              <ul id="menu">
                <li id="index"><a href="/Test/login.action" >主 页</a></li>
                <li id="getData"><a href="/Test/data/index.action" title="数据采集" style="color:yellow">统 计 图</a></li>
                <li id="map"><a href="/Test/map/index.action" title="地图">分 布 地 图</a></li>
                <li id="statisticalData"><a href="/Test/statisticalData/index.action" title="数据统计">热 力 图</a></li>
                <li id="analyseData"><a href="/Test/analyseData/index.action" title="数据分析">数 据 分 析</a></li>
            </ul>
        </div>
        <div id="user" style="height:400px;width:100%"></div>
        <div id="day" style="height:400px;width:100%"></div>
        <div id="month" style="height:400px;width:100%"></div>
      <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
      <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script> 
    <script type="text/javascript">
    drawuseruploadnumber();
    drawdayuploadnumber();
    drawmonthuploadnumber();
    function drawuseruploadnumber(){
    	$.ajax({
    		url:"/Test/data/userUploadNumber.action",
        type:"POST",
        contentType:'application/json;charset=utf-8',
        data:"123",
        dataType:'json',
        success:function(res){
        	var username=new Array();
        	var uploadnumber=new Array();
          for(var i=0;i<res.length;i++){
        	  username.push(res[i].name);
        	  uploadnumber.push(res[i].uploadNumber);
          }
          showUserUploadNumber(username,uploadnumber)
        },
        error:function(e){
        	alert("服务器无法接收数据");
        } 
	});}
    function drawdayuploadnumber(){
    	$.ajax({
    		url:"/Test/data/dayUploadNumber.action",
        type:"POST",
        contentType:'application/json;charset=utf-8',
        data:"123",
        dataType:'json',
        success:function(res){
        	var uploadnumber=new Array();
        	var time=new Array();
        	for (var i=0;i<res.length;i++){
        		uploadnumber.push(res[i]);
        		time.push(i);
        	}
        	showDayUploadNumber(time,uploadnumber);
        },
        error:function(e){
        	alert("服务器无法接收数据");
        } 
	});
    }
    function drawmonthuploadnumber(){
    	var myDate = new Date(); //获取今天日期
    	var dateArray =new Array(); 
    	 myDate.setDate(myDate.getDate() - 29);
    	 var dateTemp; 
    	 var flag = 1; 
    	 for (var i = 0; i < 30; i++) {
    	     dateTemp = (myDate.getMonth()+1)+"-"+myDate.getDate();
    	     dateArray.push(dateTemp);
    	     myDate.setDate(myDate.getDate() + flag);
    	 }
    	$.ajax({
    		url:"/Test/data/monthUploadNumber.action",
        type:"POST",
        contentType:'application/json;charset=utf-8',
        data:"123",
        dataType:'json',
        success:function(res){
        	var uploadnumber=new Array();
        	for (var i=0;i<res.length;i++){
        		uploadnumber.push(res[i]);
        	}
        	showMonthUploadNumber(dateArray,uploadnumber);
        },
        error:function(e){
        	alert("服务器无法接收数据");
        } 
	});}
    window.setInterval(function auto_play(){         
    	drawuseruploadnumber();
    	drawdayuploadnumber();
    },20000);
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        function showUserUploadNumber(username,uploadnumber){
        	require(
                    [
                        'echarts',
                        'echarts/chart/bar',
                        'echarts/chart/line' 
                    ],
                    function (ec) {
                        // 基于准备好的dom，初始化echarts图表
                        var myChart = ec.init(document.getElementById('user'));
                        var option = {
            				title : {
               			    text: '用户上传数量统计图'
            				},
            				tooltip : {
               					 trigger: 'axis'
            				},
            				legend: {
                				data:['上传数量']
           					},
            				toolbox: {
                				show : true,
                				feature : {
                    				mark : {show: true},
                   				    dataView : {show: true, readOnly: false},
                   	                magicType : {show: true, type: ['line', 'bar']},
                    				restore : {show: true},
                    				saveAsImage : {show: true}
                				}
            				},
            				calculable : true,
           		 			xAxis : [
                				{
                    				type : 'category',
                    				data :username
                				}
            				],
            				yAxis : [
                				{
                    				type : 'value'
                				}
            				],
            				series : [
                				{
                    				name:'上传照片数量',
                    				type:'bar',
                    				data:uploadnumber,
                    				markPoint : {
                        				data : [
                            				{type : 'max', name: '最大值'},
                            				{type : 'min', name: '最小值'}
                        				]
                    				},
                    			markLine : {
                        			data : [
                            			{type : 'average', name: '平均值'}
                        			]
                    			}
                			}
            			]
        			};
                    myChart.setOption(option);
                });
        }
        function showDayUploadNumber(time,uploadnumber){
        	require(
                    [
                        'echarts',
                        'echarts/chart/line' ,
                        'echarts/chart/bar' 
                    ],
                    function (ec) {
                        // 基于准备好的dom，初始化echarts图表
                        var myChart = ec.init(document.getElementById('day'));
                        var option = {
            				title : {
               			    text: '今日不同时刻上传数量统计图'
            				},
            				tooltip : {
               					 trigger: 'axis'
            				},
            				legend: {
                				data:['上传数量']
           					},
            				toolbox: {
                				show : true,
                				feature : {
                    				mark : {show: true},
                   				    dataView : {show: true, readOnly: false},
                   	                magicType : {show: true, type: ['line', 'bar']},
                    				restore : {show: true},
                    				saveAsImage : {show: true}
                				}
            				},
            				calculable : true,
           		 			xAxis : [
                				{
                    				type : 'category',
                    				data :time
                				}
            				],
            				yAxis : [
                				{
                    				type : 'value'
                				}
            				],
            				series : [
                				{
                    				name:'上传照片数量',
                    				type:'line',
                    				data:uploadnumber,
                    				smooth:true,
                    				markPoint : {
                        				data : [
                            				{type : 'max', name: '最大值'},
                            				{type : 'min', name: '最小值'}
                        				]
                    				},
                    			markLine : {
                        			data : [
                            			{type : 'average', name: '平均值'}
                        			]
                    			}
                			}
            			]
        			};
                    myChart.setOption(option);
                });
        }
        function showMonthUploadNumber(time,uploadnumber){
        	require(
                    [
                        'echarts',
                        'echarts/chart/line' ,
                        'echarts/chart/bar' 
                    ],
                    function (ec) {
                        // 基于准备好的dom，初始化echarts图表
                        var myChart = ec.init(document.getElementById('month'));
                        var option = {
            				title : {
               			    text: '月上传数量统计图'
            				},
            				tooltip : {
               					 trigger: 'axis'
            				},
            				legend: {
                				data:['上传数量']
           					},
            				toolbox: {
                				show : true,
                				feature : {
                    				mark : {show: true},
                   				    dataView : {show: true, readOnly: false},
                   	                magicType : {show: true, type: ['line', 'bar']},
                    				restore : {show: true},
                    				saveAsImage : {show: true}
                				}
            				},
            				calculable : true,
           		 			xAxis : [
                				{
                    				type : 'category',
                    				data :time
                				}
            				],
            				yAxis : [
                				{
                    				type : 'value'
                				}
            				],
            				series : [
                				{
                    				name:'上传数量',
                    				type:'line',
                    				data:uploadnumber,
                    				smooth:true,
                    				markPoint : {
                        				data : [
                            				{type : 'max', name: '最大值'},
                            				{type : 'min', name: '最小值'}
                        				]
                    				},
                    			markLine : {
                        			data : [
                            			{type : 'average', name: '平均值'}
                        			]
                    			}
                			}
            			]
        			};
                    myChart.setOption(option);
                });
        }
    </script>
        </div>
    </body>
</html>
