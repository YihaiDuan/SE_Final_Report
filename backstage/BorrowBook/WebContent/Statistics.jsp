<%@ page language="java" import="java.util.*,dao.*,entity.*" contentType="text/html; charset=gbk" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>总借出量统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <script src="js/bootstrap.min.js"></script>
      <link href="css/bootstrap.min.css" rel="stylesheet">
	  <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="assets/css/paper-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" />


    <!--  Fonts and icons     -->
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    
    <link href="assets/css/themify-icons.css" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="assets/css/iconfont.css">
    <link rel="stylesheet" href="example/example.css">
  <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>

  <!-- This is what you need -->
  <script src="dist/sweetalert-dev.js"></script>

  <link rel="stylesheet" href="dist/sweetalert.css">
    <style>
    	.prompt{
    		display:none;
    	  position:fixed;
    	  top:180px;
    	  left:540px;	
          width:500px;
          min-height:240px;
          background:#dfecfa;
          z-index:100;
          padding:40px;
          text-indent:2em;
          font-size:16px;
    	}
    	.chahao{
    	 position:absolute;
    	 top:10px;
    	 right:10px;
    	 width:20px;
    	 height:20px;
    	}
    	.look{
    		cursor: pointer;
    		color:#AEC2D4;
    	}
    	.look:hover{
    		
    		color:#68B3C8;
    	
    	}
    	.search{
    		float:right;
    		border:2px solid #f4ea2a;
    		height:28px;
    		
    	}
    	.searchtext{
    		width:170px;
    		
    		border:0;
    		font-size:12px;
    		color:#eee;
    	}
    	.searchsubmit{
    	   border:0;
    	   width:28px;
    	   height:28px;
    	   border-left:1px solid #F4EA2A;
    	   background:url("assets/img/搜索 .png");
    	    background-size: 100%;
    	    
    	}
    	.da
        {
      float:left;
      margin-top:20px;
      padding:4px 7px 0 20%;
      font-size:13px;
      	font-family:微软雅黑
        }
   .deleteimage{
    		width:30px;
    		height:30px;
    		
    		
    	}
    	#addbook{
    	 float:right;
    	 line-height:30px;
    	 margin-left:10px;
    	 color:#6cbbd1;
    	 cursor: pointer;
    	}
    	#addbook:hover{
    	color:#3a96b0;
    	}
    	#addbox{
    	display:none;
    	  position:fixed;
    	  top:180px;
    	  left:540px;	
          width:500px;
          min-height:240px;
          background:#dfecfa;
          z-index:100;
          padding:40px;
          text-indent:2em;
          font-size:16px;
    	}
    
    	.image{
    	  width:18px;
    	  height:18px;
    	}
    	td{
    	text-align:center;
    	}
    	.return,.putin
    	{
    	   display:inline-block;
    	   background:#68b3c8;
    	   color:#fff;
    	   padding:6px 10px;
    	   border-radius:8px;
    	   margin-right:10px;
    	}
    	.putin:hover{
    	  background:#31708f;
    	  color:#fff;
    	   cursor: pointer;
    	}
    	.return:hover{
    	 background:#31708f;
    	  color:#fff;
    	   cursor: pointer;
    	}
    	.select1{
    	  padding:4px;
    	
    	}
    	#add{
    	cursor:pointer;
    	}
.box { 
 float:left;
	border: 1px solid #C0C0C0;
	width: 120px;
	height: 22px;
	clip: rect(0px, 181px, 18px, 0px);
	overflow: hidden;
}

.box2 {
	border: 1px solid #F4F4F4;
	width: 120px;
	height: 22px;
	clip: rect(0px, 179px, 16px, 0px);
	overflow: hidden;
}

select {
	position: relative;
	left: -2px;
	top: -2px;
	font-size: 12px;
	width: 120px;
	line-height: 22px;
	bo rder: 0px;
	color: #909993;
	
}
.check{
    float:left;
    border:0;
    background:#69B3C8;
    color:#fff;
    line-height:22px;
    margin-left:10px;

}
 .timetext{
 width:40px;
 } 
  select{
  width:70px;
  
  }  	
    </style>
 
  </head>
  
  <body>
    <div class="wrapper">
    <div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                         后台管理系统
                </a>
            </div>

          <ul class="nav">
                <li >
                    <a href="BookPageServlet">
                        <i class="ti-panel"></i>
                        <p>图书管理</p>
                    </a>
                </li>

                   <li class="c1" style="display:none" >
                    <a href="reservePageServlet">
                        <p style="margin-left:45px;line-height:10px;">用户未取</p>
                    </a>
                 </li>
                 <li class="c2"  style="display:none">
                    <a href="reserve3PageServlet"  >
                        <p style="margin-left:45px;line-height:10px;">用户已取</p>
                    </a>
                 </li>
                     
                 
                <li>
                    <a href="UserPageServlet">
                        <i class="ti-view-list-alt"></i>
                        <p>用户管理</p>
                    </a>
                </li>
               <li>
                    <a href="AdminPageServlet">
                        <i class="ti-user"></i>
                        <p>管理员</p>
                    </a>
                </li>
             
             
            </ul>
    	</div>
    </div>
<form action="addBookServlet" method="post" enctype="multipart/form-data">
    <div class="main-panel">
       <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li style="background-color:#fff;">
                           
                        </li>
                        <li class="dropdown" style="background-color:#fff;">
                              
                              <ul class="dropdown-menu">
                               
                              </ul>
                        </li>
						<li style="background-color:#fff;">
                            
                        </li>
                    </ul>
                     <a class="navbar-brand" href="exitloginServlet" style="float:right;">注销登录</a>
                   <a class="navbar-brand" href="RetrievalPageServlet" style="float:right">图书检索</a>
                   
                </div>
            </div>
        </nav>

           <div class="content" id="catalogue">
                <div class="content">
            <div class="container-fluid">
            <div class="row">
            <div class="col-lg-7 col-lg-offset-3" style="text-align:center;" id="form1">
            <select id="YYYY" onChange="YYYYDD(this.value)" class="fyear"><option value="">请选择 年</option></select>
            <select id="MM" onChange="MMDD(this.value)" class="fmotch"><option value="">选择 月</option></select>
            <select id="DD" class="fday"><option value="" >选择 日</option></select>--
            <select id="yyyy" onChange="YYYYDD1(this.value)" class="tyear"><option value="">请选择 年</option></select>
            <select id="mm" onChange="MMDD1(this.value)" class="tmotch"><option value="">选择 月</option></select>
            <select id="dd" class="tday"><option value="" >选择 日</option></select>
             <input type="button" value="搜索" id="sear">
            
            </div>
            </div>
          </div>
             </div>         
        </div>


    
   
   
   
   
   
   
   
   
   
        <footer class="footer">
            <div class="container-fluid">
               
                <div class="copyright pull-right">
                    Copyright &copy; 2017.Company name All rights reserved.
                </div>
            </div>
        </footer>

    </div>
     </form>
</div>

  </body>
   <!--   Core JS Files   -->
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="assets/js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="assets/js/bootstrap-notify.js"></script>
    <!--  Google Maps Plugin    -->
    <!---<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>--->

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="assets/js/paper-dashboard.js"></script>
 
	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="assets/js/demo.js"></script>
	  <script src="Chart.js"></script>
     <script type="text/javascript">
     $("body").on("click",".statistics",function(){
		   $(".change1").slideToggle(100);
	       $(".change2").slideToggle(100);
	       $(".change3").slideToggle(100);
	       return false;
		});
	    $("body").on("click",".reverse",function(){
	    	 $(".c1").slideToggle(100);
		       $(".c2").slideToggle(100);
		       return false;
			});
     //日期选择
	   function YYYYMMDDstart(){   
		MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];   
		//先给年下拉框赋内容   
		var y  = new Date().getFullYear();  
		for (var i = (y-20); i < (y+10); i++) //以今年为准，前30年，后30年   
			document.getElementById("YYYY").options.add(new Option(" "+ i +" 年", i));   
		//赋月份的下拉框   
		for (var i = 1; i < 13; i++)   
			document.getElementById("MM").options.add(new Option(" " + i + " 月", i));   
		document.getElementById("YYYY").value = y;   
		document.getElementById("MM").value = new Date().getMonth() + 1;   
		var n = MonHead[new Date().getMonth()];   
		if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;   
			writeDay(n); //赋日期下拉框Author:meizz   
			document.getElementById("DD").value = new Date().getDate();   
	}   
	if(document.attachEvent)   
		window.attachEvent("onload", YYYYMMDDstart);   
	else   
	window.addEventListener('load', YYYYMMDDstart, false);   
	function YYYYDD(str) //年发生变化时日期发生变化(主要是判断闰平年)   
	{   
		var MMvalue = document.getElementById("MM").options[document.getElementById("MM").selectedIndex].value;   
		if (MMvalue == ""){ var e = document.getElementById("DD"); optionsClear(e); return;}   
		var n = MonHead[MMvalue - 1];   
		if (MMvalue ==2 && IsPinYear(str)) n++;   
		writeDay(n)   
	}   
	function MMDD(str)   //月发生变化时日期联动   
	{   
		var YYYYvalue = document.getElementById("YYYY").options[document.getElementById("YYYY").selectedIndex].value;   
		if (YYYYvalue == ""){ var e = document.getElementById("DD"); optionsClear(e); return;}   
		var n = MonHead[str - 1];   
		if (str ==2 && IsPinYear(YYYYvalue)) n++;   
		writeDay(n)   
	}   



	function writeDay(n)   //据条件写日期的下拉框   
	{   
		var e = document.getElementById("DD"); optionsClear(e);   
		for (var i=1; i<(n+1); i++)   
		e.options.add(new Option(" "+ i + " 日", i));   
	}   
	function IsPinYear(year)//判断是否闰平年   
	{
		return(0 == year%4 && (year%100 !=0 || year%400 == 0));
	}   
	function optionsClear(e)   
	{   
		e.options.length = 1;   
	}
	 
	//日期选择
	   function YYYYMMDDstart1(){   
		MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];   
		//先给年下拉框赋内容   
		var y  = new Date().getFullYear();  
		for (var i = (y-20); i < (y+10); i++) //以今年为准，前30年，后30年   
			document.getElementById("yyyy").options.add(new Option(" "+ i +" 年", i));   
		//赋月份的下拉框   
		for (var i = 1; i < 13; i++)   
			document.getElementById("mm").options.add(new Option(" " + i + " 月", i));   
		document.getElementById("yyyy").value = y;   
		document.getElementById("mm").value = new Date().getMonth() + 1;   
		var n = MonHead[new Date().getMonth()];   
		if (new Date().getMonth() ==1 && IsPinYear1(YYYYvalue)) n++;   
			writeDay1(n); //赋日期下拉框Author:meizz   
			document.getElementById("dd").value = new Date().getDate();   
	}   
	if(document.attachEvent)   
		window.attachEvent("onload", YYYYMMDDstart1);   
	else   
	window.addEventListener('load', YYYYMMDDstart1, false);   
	function YYYYDD1(str) //年发生变化时日期发生变化(主要是判断闰平年)   
	{   
		var MMvalue = document.getElementById("mm").options[document.getElementById("mm").selectedIndex].value;   
		if (MMvalue == ""){ var e = document.getElementById("dd"); optionsClear1(e); return;}   
		var n = MonHead[MMvalue - 1];   
		if (MMvalue ==2 && IsPinYear1(str)) n++;   
		writeDay1(n)   
	}   
	function MMDD1(str)   //月发生变化时日期联动   
	{   
		var YYYYvalue = document.getElementById("yyyy").options[document.getElementById("yyyy").selectedIndex].value;   
		if (YYYYvalue == ""){ var e = document.getElementById("dd"); optionsClear1(e); return;}   
		var n = MonHead[str - 1];   
		if (str ==2 && IsPinYear1(YYYYvalue)) n++;   
		writeDay1(n)   
	}   



	function writeDay1(n)   //据条件写日期的下拉框   
	{   
		var e = document.getElementById("dd"); optionsClear1(e);   
		for (var i=1; i<(n+1); i++)   
		e.options.add(new Option(" "+ i + " 日", i));   
	}   
	function IsPinYear1(year)//判断是否闰平年   
	{
		return(0 == year%4 && (year%100 !=0 || year%400 == 0));
	}   
	function optionsClear1(e)   
	{   
		e.options.length = 1;   
	}
     $("#statistics").click(function(){
         $("#change1").slideToggle(100);
         $("#change2").slideToggle(100);
         return false;
      
      }); 
     $("#sear").click(function(){
          var fyear= $(".fyear").val();
          var fmotch= $(".fmotch").val();
          var fday= $(".fday").val();
          var tyear= $(".tyear").val();
          var tmotch= $(".tmotch").val();
          var tday= $(".tday").val();
         
          window.location.href="StatisticsServlet?fyear="+fyear+"&fmotch="+fmotch+"&fday="+fday+"&tyear="+tyear+"&tmotch="+tmotch+"&tday="+tday;
           
         
         
         });
         
     var news=<%=request.getAttribute("news")%>
     var arr="<%=request.getAttribute("arr")%>"
         arr =decodeURIComponent(arr);
         arr=arr.split(",");
   if(news!="null")
  {
    
    var data = {
     labels: [],
     datasets: [
         {
             fillColor : "transparent",
             strokeColor: "rgba(105,179,200,1)",
              pointColor : "rgba(105,179,200,1)",
             label: "2010年",
             data: []
         }
     ]
 }
 for(var i=0;i<news.length;i++)
 {
    data.labels.push(arr[i]);
    data.datasets[0].data.push(news[i]);
 }
 $(function () {
     var ctx = $("#myChart").get(0).getContext("2d");
     var myNewChart = new Chart(ctx);
     myNewChart.Line(data);
 });
 } 
     $(".check").click(function(){
          var name= $('select option:selected').val(); 
          window.location.href="StatisticsServlet?categoryid="+name;
          return false;
     }); 
     $("#statistics").click(function(){
        $("#change1").slideToggle(100);
        $("#change2").slideToggle(100);
        $("#change3").slideToggle(100);
        return false;
     
     }); 
  
    	  
     </script>
  
</html>
