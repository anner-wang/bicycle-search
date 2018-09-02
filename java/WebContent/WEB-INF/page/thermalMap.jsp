<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />   
        <title>热力图</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script> 
        <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=EivS2K3LgKdDjdaty4Z666QEuw2kMz41"></script>
         <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
        <style type="text/css">
           #menu{
               position: absolute;
               background-color: #363636;
	           width: 100%;
	           height: 100px;
               margin: 0px;
               left: 0px;
           }
           /*设置导航条文字属性*/
           ul#menu li {
	          /*设置文字的格式*/
	           display: inline-block;
	           float: left; 
               margin-left:175px;
               margin-top: 40px;
           }
           /*设置链接的字体和颜色*/
           a{
               font-family:'仿宋';
	           font-size:18px;
               color: white;
               background: transparent;
               text-decoration : none;
           }
            input[type=range]{
                -webkit-appearance: none;
                width: 300px;
                border-radius: 10px; /*这个属性设置使填充进度条时的图形为圆角*/
            }
            input[type=range]::-webkit-slider-runnable-track {
                height: 15px;
                border-radius: 10px; /*将轨道设为圆角的*/
                box-shadow: 0 1px 1px #def3f8, inset 0 .125em .125em #0d1112; /*轨道内置阴影效果*/
            }
            input[type=range]:focus {
                outline: none;
            }
            #move{
                position: absolute;
                top: 845px;
                left: 550px;
                width: 50%;
                height:10px;
            }
            #auto_play{
                position: absolute;
                left: 500px;
                top: 0px;
            }
            #stop{
                 position: absolute;
                left: 600px;
                top: 0px;
            }
            #container{
                position: absolute;
                left: 0px;
                top:100px;
                width: 100%;
                height: 75%;
            }
        </style>
    </head>
    <body>
        <div>
            <ul id="menu">
                <li id="index"><a href="/Test/login.action" >主 页</a></li>
                <li id="getData"><a href="/Test/data/index.action" title="数据采集" >统 计 图</a></li>
                <li id="map"><a href="/Test/map/index.action" title="地图">分 布 地 图</a></li>
                <li id="statisticalData"><a href="/Test/statisticalData/index.action" title="数据统计" style="color:yellow">热 力 图</a></li>
                <li id="analyseData"><a href="/Test/analyseData/index.action" title="数据分析">数 据 分 析</a></li>
            </ul>
        </div>
        <div id='container'></div>
        <div id="move">
            <input id="range" type="range" value="0" min="0" max="24" step="1" oninput="change()">
            <button id="auto_play" onclick="play()">自动播放</button>
            <button id="stop" onclick="stop()">暂停播放</button>
        </div>
        
    </body>
</html>
<script type="text/javascript">
var map = new BMap.Map("container");          // 创建地图实例
var point = new BMap.Point(114.36304,30.61227);
map.centerAndZoom(point, 18);             // 初始化地图，设置中心点坐标和地图级别
map.enableScrollWheelZoom(); // 允许滚轮缩放
heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":30});
map.addOverlay(heatmapOverlay);
var currentValue=0
var mark;
function change(){
    window.clearInterval(mark);
    var value=document.getElementById('range').value;
    $.ajax({
		url:"/Test/statisticalData/getData.action",
    type:"POST",
    contentType:'application/json;charset=utf-8',
    data:value+"",
    dataType:'json',
    success:function(res){
           drawHeatMap(res);
        },
        error:function(e){
        	alert("服务器无法接收数据");
        } 
    });
}
function play(){
    mark=window.setInterval(function auto_play(){
    
        document.getElementById('range').value=currentValue;
     
        $.ajax({
    		url:"/Test/statisticalData/getData.action",
        type:"POST",
        contentType:'application/json;charset=utf-8',
        data:currentValue+"",
        dataType:'json',
        success:function(res){
           drawHeatMap(res);
        },
        error:function(e){
        	alert("服务器无法接收数据");
        } 
	});
        currentValue=currentValue+1;
        if(currentValue==24){
            currentValue=0;
        }
    },200);
}
function stop(){
    window.clearInterval(mark);
}
//形成百度地图需要的数据格式
function drawHeatMap(res){
    var datas=[];
    for(var i=0;i<res.length;i++){
        var image=res[i];
        var lng=image.longitude;
        var lat=image.latitude;
        var count=image.number;
        var data={"lng":lng,"lat":lat,"count":count};
        datas.push(data);
    }
    heatmapOverlay.setDataSet({data:datas,max:100});
    heatmapOverlay.show();
}
</script>
