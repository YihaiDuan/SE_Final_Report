<%@ page language="java" import="java.util.*,hfhdao.*,hfhServlet.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'datastatistics.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/colorpicker.css" />
<link rel="stylesheet" href="css/datepicker.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/matrix-style2.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link rel="stylesheet" href="css/bootstrap-wysihtml5.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link href="layui/css/layui.css" />
<style>
	.control-group .controls label{
		display:inline-block;
	}
	.timetext{
		width:154px;
		line-height:58px;
		height:58px;
		
	}
	.range{
		display:inline-block;
		background:#EEEEEE;
		color:#999999;
		border:1px solid #ccc;
		font-size:14px;
		line-height:27px;
		padding:0 14px;
		margin-right:20px;
		cursor:pointer;
	}
	.range:active{
	    background:#2E363F;
	    color:#FFF;
	}
	.tishi{
	display:none;
	 position:absolute;
	 right:30px;
	 top:420px;
	}
	.tishi li{
		list-style:none;
		padding:3px 12px;
		color:#fff;
		font-weight:bold;
		margin-bottom:6px;
	}

   
	
</style>
</head>
  
  <body>
   <div id="content">
   <div  class="quick-actions_homepage offset1  span10">
    <ul class="quick-actions ">
    <%
         BookSonDao booksondao=new BookSonDao();
         BorrowTableDao borrowtabledao=new BorrowTableDao();
     %>
      <li class="bg_lb"> <a href="#">总书量</a><p style="color:#fff;"><%=booksondao.getBookSonDetailNumber() %>册</p></li>
      <li class="bg_lg"> <a href="#">出库量</a><p style="color:#fff;"><%=borrowtabledao.borrowTableDeatilNumber() %>册</p></li>
      <li class="bg_ly"> <a href="#">未出库量</a><p style="color:#fff;"><%=booksondao.getBookSonDetailNumber()-borrowtabledao.borrowTableDeatilNumber()%>册</p></li>
      <li class="bg_lo"> <a href="#">逾期未还</a><p style="color:#fff;"><%=borrowtabledao.borrowTableoverdueNumber()%>册</p></li>
    </ul>
  </div>
  <div class="span8 offset1"  style="margin-top:20px;padding-left:20px;">
  	   <span class="range">范围选择</span><input placeholder="开始时间" id="LAY_start_time" name="startTime"type="text" class="timetext" style="height:30px;line-height:30px;margin-bottom:6px;"/>
       <input placeholder="截止时间" id="LAY_end_time" name="endTime" type="text" class="timetext" style="height:30px;line-height:30px;margin-bottom:6px;"/> <span class="range" id="sure">确定</span>
　　</div>
  <div class="offset1" >
    <canvas id="myChart" width="920" height="350"></canvas>
    <ul class="tishi">
      <li style="background:rgba(255,184,72,1)">借阅量</li>
      <li style="background:rgba(218,84,46,1)">逾期未还</li>
    </ul>
  </div>
</div>

<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/bootstrap-colorpicker.js"></script> 
<script src="js/bootstrap-datepicker.js"></script> 
<script src="js/jquery.toggle.buttons.html"></script> 
<script src="js/masked.js"></script> 
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 
<script src="js/matrix.js"></script> 
<script src="js/matrix.form_common.js"></script> 
<script src="js/wysihtml5-0.3.0.js"></script> 
<script src="js/jquery.peity.min.js"></script> 
<script src="js/bootstrap-wysihtml5.js"></script> 
<script src="layui/layui.js"></script>
<script src="js/Chart.js"></script>
<script>
   var news;
   
   layui.use('laydate', function () {
var laydate = layui.laydate;
var start =
{
/* elem: '#id', //需显示日期的元素选择器*/
event: 'click', //触发事件
format: 'YYYY-MM-DD', //日期格式
isclear: true, //是否显示清空
istoday: true, //是否显示今天
issure: true,// 是否显示确认
start: laydate.now(), //开始日期
fixed: false, //是否固定在可视区域
zIndex: 99999999, //css z-index
choose: function (dates) { //选择好日期的回调
end.min = dates; //开始日选好后，重置结束日的最小日期
end.start = dates //将结束日的初始值设定为开始日
}
}
var end = {
event: 'click', //触发事件
format: 'YYYY-MM-DD', //日期格式
isclear: true, //是否显示清空
issure: true,// 是否显示确认
fixed: false, //是否固定在可视区域
zIndex: 99999999, //css z-index
choose: function (dates) { //选择好日期的回调 结束日选好后，重置开始日的最大日期
start.max = dates;
}
}

$("#LAY_start_time").click(function () {
start.elem = this;
laydate(start);
});

$("#LAY_end_time").click(function () {
end.elem = this;
laydate(end)
});
})
  $("#sure").click(function(){
    var start=$("#LAY_start_time").val();
    var end =$("#LAY_end_time").val();
    if(start==''|| end==''){
     return false;
    }
   var a=start.split("-");
   var b=end.split("-");
   var fyear=a[0];
   var fmotch=a[1];
   var fday=a[2];
   var tyear=b[0];
   var tmotch=b[1];
   var tday=b[2];
   var date={"fyear":fyear,"fmotch":fmotch,"fday":fday,"tyear":tyear,"tmotch":tmotch,"tday":tday,"time": new Date()};
  $.post("DataStatisticsServlet",date,function(data) {
	
    	   news=eval("("+data+")");
    	   //用于存放图表上的数据
    	   var lineChartData = {  
    	   //表的X轴参数
    	   labels :news.overdue,
    	   datasets : [
    	       {
    	           fillColor : "transparent",     //背景色，常用transparent透明
    	           strokeColor : "rgba(255,184,72,1)",  //线条颜色，也可用"#ffffff"
    	           pointColor : "rgba(255,184,72,1)",   //点的填充颜色
    	           pointStrokeColor : "#fff",            //点的外边框颜色
    	           data : news.borrow     //点的Y轴值
    	       },
    	          {
    	           fillColor : "transparent",     //背景色，常用transparent透明
    	           strokeColor : "rgba(218,84,46,1)",  //线条颜色，也可用"#ffffff"
    	           pointColor : "rgba(218,84,46,1)",   //点的填充颜色
    	           pointStrokeColor : "#fff",            //点的外边框颜色
    	           data :news.time      //点的Y轴值
    	       }
    	     ]
    	   }

    	   //定义图表的参数   
    	   var defaults = {    
    	       scaleStartValue :null,     // Y 轴的起始值
    	       scaleLineColor : "rgba(0,0,0,.5)",    // Y/X轴的颜色
    	       scaleLineWidth : 1,        // X,Y轴的宽度
    	       scaleShowLabels : true,    // 刻度是否显示标签, 即Y轴上是否显示文字   
    	      <%--  scaleLabel : "<%=value%>",  // Y轴上的刻度,即文字   --%>
    	       scaleFontFamily : "'Arial'",  // 字体  
    	       scaleFontSize : 14,        // 文字大小 
    	       scaleFontStyle : "normal",  // 文字样式  
    	       scaleFontColor : "#333",    // 文字颜色  
    	       scaleShowGridLines : true,   // 是否显示网格  
    	       scaleGridLineColor : "rgba(0,0,0,.1)",   // 网格颜色
    	       scaleGridLineWidth : 2,      // 网格宽度  
    	       bezierCurve : false,         // 是否使用贝塞尔曲线? 即:线条是否弯曲     
    	       pointDotStrokeWidth : 1,     // 圆点的笔触宽度, 即:圆点外层边框大小 
    	       datasetStroke : true,        // 数据集行程
    	       datasetStrokeWidth : 2,      // 线条的宽度, 即:数据集
    	       datasetFill : false,         // 是否填充数据集 
    	       animation : true,            // 是否执行动画  
    	       animationSteps : 60,          // 动画的时间   
    	       animationEasing : "easeOutQuart",    // 动画的特效   
    	       onAnimationComplete : null    // 动画完成时的执行函数   
    	      }
    	   var ctx = document.getElementById("myChart").getContext("2d");
           window.myLine = new Chart(ctx).Line(lineChartData,defaults); 
   	$('.textarea_editor').wysihtml5();
    $('.tishi').show();
    });
   
  });
 
</script>
  </body>
</html>
