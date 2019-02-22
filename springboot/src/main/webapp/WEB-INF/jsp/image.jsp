<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
        <title>数据分析</title>
        <!--调用jquery框架-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script> 
       <style type="text/css">
            body{
                width: 100%;
                padding: 0px;
                margin: 0px;
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
           #change{
               width:100%;
               height: 500px;
           }
           #main{
                position: relative;
                margin-left: 80px;
                width:100%;
            }
            .box{
                padding: 15px 0 0 15px;
                float: left;
            }
            .pic{
                padding: 10px;
                border: 1px solid #ccc;
                /*圆角*/
                border-radius: 5px;
                /*阴影*/
                box-shadow: 0 0 5px #ccc;
            }
            .pic img{
                width:300px;
                height: auto;
            }
            #image1{
            	width:23%;
            	height:450px;	
                margin-left: 100px;
            }
             #image2{
            	width:23%;
            	height:450px;
            }
            #changeTable{
            	width:41%;
                position: absolute;
                top: 120px;
                left: 1100px;
               
                
            }
           #changeButton{
               position: absolute;
               top:540px;
               left:990px;
               width: 70px;
              height: auto;
           }
           #resetButton{
               position: absolute;
               top:530px;
               left:1190px;
               width: 70px;
               height: auto;
           }
           .changedValue{
               height:40px;
               width: 200px;
               font-size: 18
           }
       </style>
     <script type="text/javascript">
            window.onload=function(){
                waterfall('main','box');
            }
            function waterfall(parent,box){
                //将main下的所有class为box的元素取出来
                var oParent=document.getElementById(parent);
                var oBoxes=getByClass(oParent,box);
                //计算整个页面显示的列数（页面的宽度/box的宽度）
                var oBoxWidth=oBoxes[0].offsetWidth;
                var cols=Math.floor(document.documentElement.clientWidth/oBoxWidth);
                var hArr=[];
                for(var i=0;i<oBoxes.length;i++){
                    if(i<cols){
                        hArr.push(oBoxes[i].offsetHeight)
                    }else{
                        //获取数组中最小的值,确定高度
                        var minH=Math.min.apply(null,hArr);
                        var index=getMinIndex(hArr,minH);
                        oBoxes[i].style.position='absolute';
                        oBoxes[i].style.top=minH+'px';
                        oBoxes[i].style.left=oBoxWidth*index+'px';
                        //更新数组
                        hArr[index]=hArr[index]+oBoxes[i].offsetHeight;
                    }
                }
            }
            //根据class获取元素
            function getByClass(oParent,className){
                var boxArray=new Array();
                oElements=oParent.getElementsByTagName('*');
                for(var i=0;i<oElements.length;i++){
                    if(oElements[i].className==className){
                        boxArray.push(oElements[i]);
                    }
                }
                return boxArray;
            } 
            //寻找数组中最小值的下标
            function getMinIndex(array,num){
                for(var i=0;i<array.length;i++){
                    if(array[i]==num){
                        return i;
                    }
                }
                return -1;
            }
            //判断是否具备加载数据的条件
            function checkScrollSlide(){
                var oParent=document.getElementById('main');
                var oBoxes=getByClass(oParent,'box');
                var lastBoxLength=oBoxes[oBoxes.length-1].offsetTop+Math.floor(oBoxes[oBoxes.length-1].offsetHeight/2);
                //标准模式和混杂模式
                var scrollTop=document.body.scrollTop||document.documentElement.scrollTop;
                //窗口的高度
                var height=document.body.clientHeight||document.documentElement.clientHeight;
                return (lastBoxLength<scrollTop+height)?true:false;
            }
          //判断是否具备加载数据的条件
            function checkScrollSlide(){
                var oParent=document.getElementById('main');
                var oBoxes=getByClass(oParent,'box');
                var lastBoxLength=oBoxes[oBoxes.length-1].offsetTop+Math.floor(oBoxes[oBoxes.length-1].offsetHeight/2);
                //标准模式和混杂模式
                var scrollTop=document.body.scrollTop||document.documentElement.scrollTop;
                //窗口的高度
                var height=document.body.clientHeight||document.documentElement.clientHeight;
                return (lastBoxLength<scrollTop+height)?true:false;
            }
        </script>
    </head>
    <body>
        <div>
            <ul id="menu">
              <li id="index"><a href="/login" >主 页</a></li>
              <li id="getData"><a href="/data/index" title="数据采集">统 计 图</a></li>
              <li id="map"><a href="/map/index" title="地图">分 布 地 图</a></li>
              <li id="statisticalData"><a href="/statisticalData/index" title="数据统计">热 力 图</a></li>
              <li id="analyseData"><a href="/analyseData/index" title="数据分析" style="color:yellow">数 据 分 析</a></li>
          </ul>
      </div>
        <div id="change">
        	<img id="image1" alt="待修改图片" src="https://tse2-mm.cn.bing.net/th?id=OIP.e7DW_jvHP67Eh7Ya5n1ZUwHaFS&w=266&h=186&c=7&o=5&pid=1.7" >
        	<img id="image2" alt="待修改图片" src="https://tse2-mm.cn.bing.net/th?id=OIP.e7DW_jvHP67Eh7Ya5n1ZUwHaFS&w=266&h=186&c=7&o=5&pid=1.7">
        	<table  id="changeTable" border="1">
        		<tr>
        			<td></td>
        			<td>上传数据</td>
        			<td>修改数据</td>
        		</tr>
                <tr>
        			<td>上传者</td>
        			<td id="name">test</td>
        			<td ><input class="changedValue"type="text" readonly="true"></td>
        		</tr>
                <tr>
        			<td>文件名</td>
        			<td id="fileName" >test</td>
        			<td><input class="changedValue"type="text" readonly="true"></td>
        		</tr>
        		<tr>
        			<td>上传时间</td>
        			<td id="time">test</td>
        			<td ><input class="changedValue"type="text" readonly="true"></td>
        		</tr>
                <tr>
        			<td>上传经度</td>
        			<td id="longitude">test</td>
        			<td ><input class="changedValue"type="text" readonly="true"></td>
        		</tr>
                <tr>
        			<td>上传纬度</td>
        			<td id="latitude">test</td>
        			<td ><input class="changedValue"type="text" readonly="true"></td>
        		</tr>
                <tr>
        			<td>识别结果</td>
        			<td id="number">test</td>
        			<td ><input class="changedValue"type="text"></td>
        		</tr>
                <tr>
        			<td>上传备注</td>
        			<td id="ps">test</td>
        			<td ><input class="changedValue"type="text" readonly="true"></td>
        		</tr>
        	</table>
        </div>
        <div id="main">
           <c:forEach items="${images }" var="image">
           		<div class='box'>
                    <div class='pic'>
                        <img alt="图片丢失" src="${image }" onclick='fun("${image}")'>
                    </div>
               </div>
           </c:forEach>
        </div>
    </body>
</html>
<script type="text/javascript">
    function fun(test){
        image1=document.getElementById('image1');
        image1.src=test;
        image2=document.getElementById('image2');
        image2.src=test.substring(0,13)+'p.png';
        //请求对应图片的信息
        $.ajax({
           		url:"/analyseData/getImageData",
                type:"POST",
                contentType:'application/json;charset=utf-8',
                data:test,
                dataType:'json',
                success:function(res){
                    var name=document.getElementById('name');
                    name.innerHTML=res.name;
                    var fileName=document.getElementById('fileName');
                    fileName.innerHTML=res.fileName;
                	var time=document.getElementById('time');
                    time.innerHTML=res.time;
                    var longitude=document.getElementById('longitude');
                    longitude.innerHTML=res.longitude;
                    var latitude=document.getElementById('latitude');
                    latitude.innerHTML=res.latitude;
                    var number=document.getElementById('number');
                    number.innerHTML=res.number;
                    var ps=document.getElementById('ps');
                    ps.innerHTML=res.ps;
                    inputs=document.getElementsByTagName('input');
                    inputs[0].value=res.name;
                    inputs[1].value=res.fileName;
                    inputs[2].value=res.time;
                    inputs[3].value=res.longitude;
                    inputs[4].value=res.latitude;
                    inputs[5].value=res.number;
                    inputs[6].value=res.ps;
                },
                error:function(e){
                	alert("错误:服务器关闭，无法获取数据");
                }
                
           	});
    }
    //回车事件
    $(function(){
		document.onkeydown = function(e){ 
    		var ev = document.all ? window.event : e;
    		if(ev.keyCode==13) {
    			change();
    		 }
		}
	}); 
    function change(){
        inputs=document.getElementsByTagName('input');
        var name=inputs[0].value;
        var fileName=inputs[1].value;
        var time=inputs[2].value;
        var longitude=inputs[3].value;
        var latitude=inputs[4].value;
        var number=inputs[5].value;
        var ps=inputs[6].value;
        document.getElementById('name').innerHTML=name;
        document.getElementById('fileName').innerHTML=fileName;
        document.getElementById('time').innerHTML=time;
        document.getElementById('longitude').innerHTML=longitude;
        document.getElementById('latitude').innerHTML=latitude;
        document.getElementById('number').innerHTML=number;
        document.getElementById('ps').innerHTML=ps;
        var data={name:name,time:time,longitude:longitude,latitude:latitude,fileName:fileName,number:number,ps:ps};
        $.ajax({
            url:"/analyseData/setImageData",
            type:"POST",
            contentType:'application/json;charset=utf-8',
            data:JSON.stringify(data),
            dataType:'text',
            success:function(res){
                if(res=="ok"){
                    alert("修改成功");
                }else{
                	alert("修改失败");
                }
              },
            error:function(e){
                alert("错误:服务器关闭，无法发送修改数据");
             }
        });
    }
    function reset(){
        alert("重置成功");
    }
</script>
