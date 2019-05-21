<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />   

        <title>主页</title>
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
           #img{
           		width:100%;
           		height:100%           	
           }             
       </style>
    </head>
 <body>
    <div>
        <ul id="menu">
          <li id="index"><a href="/login" >主 页</a></li>
          <li id="getData"><a href="/data/index" title="数据采集">统 计 图</a></li>
          <li id="map"><a href="/map/index" title="地图">分 布 地 图</a></li>
          <li id="statisticalData"><a href="/statisticalData/index" title="数据统计">热 力 图</a></li>
          <li id="analyseData"><a href="/analyseData/index" title="数据分析">数 据 分 析</a></li>
      </ul>
  </div>
        <img id='img' src="https://tse1-mm.cn.bing.net/th?id=OET.f6811500d3f241d8bfe6c0a6876cdea6&w=272&h=272&c=7&rs=1&o=5&pid=1.9d">
    </body>
</html>