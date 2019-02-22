<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!DOCTYPE html>  
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />   
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>分布地图</title>  
<style type="text/css">  
html{height:100%}  
body{height:100%;margin:0px;padding:0px}  
#container{height:90%}  
</style> 
<style type="text/css">
            body{
                height: 100%;
                width: 100%;
           }
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
           /*设置链接的字体和颜色*/
           a{
               font-family:'仿宋';
	           font-size:18px;
               color: white;
               background: transparent;
               text-decoration : none;
           }
          
       </style>
<!--调用地图组件API-->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=EivS2K3LgKdDjdaty4Z666QEuw2kMz41"></script>
<!--调用jquery框架-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script> 
<!-- 脚本的函数 -->
<script type="text/javascript">
    window.setInterval(function getPoint(){
            var data={name:'test',uploadNumber:123,status:'test'};
        	$.ajax({
           		url:"/map/getPoint",
                type:"POST",
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify(data),
                dataType:'json',
                success:function(res){
                    if(res.time!="12345"){
                        var marker=addMarker(res.longitude,res.latitude);
                        var infoWindow=getInfoWindow(res.fileName,res.name,res.number,res.time,res.ps);
                        addMarkerHandler(marker,infoWindow);
                    }
                },
                error:function(e){
                	alert("错误:服务器关闭，无法获取数据");
                }
                
           	})},700);
</script>
</head>  
	<body>
        <div>
            <ul id="menu">
              <li id="index"><a href="/login" >主 页</a></li>
              <li id="getData"><a href="/data/index" title="数据采集">统 计 图</a></li>
              <li id="map"><a href="/map/index" title="地图"  style="color:yellow">分 布 地 图</a></li>
              <li id="statisticalData"><a href="/statisticalData/index" title="数据统计">热 力 图</a></li>
              <li id="analyseData"><a href="/analyseData/index" title="数据分析">数 据 分 析</a></li>
          </ul>
      </div>
		<div id="container"></div> 
	</body>  
</html>
<script type="text/javascript"> 
    // 创建地图实例
    var map = new BMap.Map("container"); 
    // 创建中心点坐标
    var centerPoint = new BMap.Point(114.36304,30.61227);  
    // 初始化地图，设置中心点坐标和地图级别 
    map.centerAndZoom(centerPoint, 18);
    //支持鼠标滚轮缩放地图
    map.enableScrollWheelZoom(true);
    //添加标记点
    function addMarker(longitude,latitude){
        var point=new BMap.Point(longitude,latitude);
        var marker=new BMap.Marker(point);
        map.addOverlay(marker);
        return marker;
    }
    //添加图文组合窗口
    function getInfoWindow(fileName,name,number,time,ps){
        var content="<img height='250' width='230' style='float:left' src='"+fileName+"'/><p style='width:500px;padding:5px'>上传者: "+name+"<br/><br/>上传时间:"+time+
            "<br/><br/>识别结果:"+number+"<br/><br/>上传备注:"+ps+"</p>";
        //创建信息窗口对象
        var infoWindow=new BMap.InfoWindow(content);
        return infoWindow;          
    }
    //添加标记点处理器
    function addMarkerHandler(marker,infoWindow){
        marker.addEventListener("click",function(){
            this.openInfoWindow(infoWindow);
        })
    }
</script>  