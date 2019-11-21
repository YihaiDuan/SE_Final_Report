<%@ page language="java" import="java.util.*,Extenddao.*,newServlet.*,entity.*,hfhdao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'activitymanage.jsp' starting page</title>
    
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
<link rel="stylesheet" href="css/matrix-style2.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="layui/css/layui.css" />
<link rel="stylesheet" href="css/j-select.css" />  
<style>
	.layui-form-checkbox {
		height:24px;
		line-height:22px;
		margin-right:0;
	}
	.layui-form-checkbox span {
   font-size:11px;
}
  .tatb td{
  	height:40px;
  	line-height:40px;
  	
  	
  }
  .layui-input{
  	height:31px;
  	
  }
  input[type="text"]
  {
  	height:32px;
  	background:#fff;
  	width:263px;
  }
  .layui-form-select .layui-edge {
    position: absolute;
    right: 160px;
    top: 50%;
    margin-top: -7px;
    cursor: pointer;
    border-width: 6px;
    border-top-color: #c2c2c2;
    border-top-style: solid;
    transition: all .3s;
    -webkit-transition: all .3s;
}
.layui-form-select dl
{
	min-width:263px;
	
}
.layui-form-item{
	margin-bottom:0;
}
.layui-form-checkbox {
		height:24px;
		line-height:22px;
		margin-right:0;
	}
	.layui-form-checkbox span {
   font-size:11px;
}
  .tatb td{
  	height:40px;
  	line-height:40px;
  	
  	
  }
  .layui-input{
  	height:31px;
  	
  }
  input[type="text"]
  {
  	height:32px;
  	background:#fff;
  	width:80px;
  }
  .layui-form-select .layui-edge {
    position: absolute;
    right: 160px;
    top: 50%;
    margin-top: -7px;
    cursor: pointer;
    border-width: 6px;
    border-top-color: #c2c2c2;
    border-top-style: solid;
    transition: all .3s;
    -webkit-transition: all .3s;
}
.layui-form-select dl
{
	min-width:263px;
	
}
.layui-form-item{
	margin-bottom:0;
}
.layui-form-select
{
	display:inline-block;
	width:80px;
}
.layui-form-checkbox {
		height:24px;
		line-height:22px;
		margin-right:0;
	}
	.layui-form-checkbox span {
   font-size:11px;
}
  .tatb td{
  	height:40px;
  	line-height:40px;
  	
  	
  }
  .layui-input{
  	height:31px;
  	
  }
  input[type="text"]
  {
  	height:32px;
  	background:#fff;
  	width:263px;
  }
  .layui-form-select .layui-edge {
    position: absolute;
    right: 160px;
    top: 50%;
    margin-top: -7px;
    cursor: pointer;
    border-width: 6px;
    border-top-color: #c2c2c2;
    border-top-style: solid;
    transition: all .3s;
    -webkit-transition: all .3s;
}
.layui-form-select dl
{
	min-width:263px;
	
}
.layui-form-item{
	margin-bottom:0;
}
input[type="checkbox"]{
display:none;
}
.layui-form{
    display:block;
}
input[type="text"] {
    height: 27px;
    background: #fff;
    width: 263px;
}
.slideimage{
    width:20px;
}
.bigslideimage{
display:none;
 width:160px;
 position:absolute;
 top:0;
 left:0;
}
	.pation{
  border-radius:4px;
 display:inline-block;
 
}
.pation li{
float:left
}
.pation li a{
    float:left;
    display:inline-block;
     padding: 6px 12px;
    line-height: 1.42857143;
    text-decoration: none;
    color: #428bca;
    background-color: #fff;
    border: 1px solid #ddd;
    margin-left: -1px;
    font-size:11px;
}
.pation li.active a{
background:#428bca;
color:#fff;

}
.pation li.active a:hover{
background:#428bca;
color:#fff;

}
.pation li a:hover{
   background-color: #eee;
   color:#000;
}
 	 	.da
        {
      float:left;
      margin-top:20px;
      padding:4px 7px 0 32%;
      margin-right:6px;
      font-size:13px;
 	font-family:微软雅黑
        }
</style>
  </head>
  
  <body>
    <div id="content" style="padding-top:20px;">
	<div class="layui-tab layui-tab-card" style="margin:10px 20px;">
  <ul class="layui-tab-title">
    <li class="layui-this">活动列表</li>
    <li id="addactive">添加活动</li>
  </ul>
  <div class="layui-tab-content">

    <div class="layui-tab-item layui-show">
    	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li id="reduce">降价</li>
    <li class="layui-this">优惠</li>
    <li id="grounp">拼团</li>
  </ul>
  <div class="layui-tab-content">
  	<div class="layui-tab-item " style="clear:both;padding-bottom:40px;">
     
  	
  	</div>
  	
  		<div class="layui-tab-item layui-show">
  			<div class="row">
  		    <a href="DeleteOutTimeDiscountServlet?pc=${pb.pc}" class="layui-btn" style="float:right;font-size:11px;">删除全部已过期</a>
  	    </div>
  	   
  		<table class="layui-table" >
  <colgroup>
   

  </colgroup>
  <thead>
    <tr>
      <th>优惠时间段</th>
      <th>类型</th>
      <th>总数量</th>
      <th>描述</th>
      <th colspan="2" style="text-align: center;">操作</th>
      <td>状态</td>
    </tr> 
  </thead>
  <tbody>
      <%
                     List<Discount> blist=new ArrayList<Discount>();
                     Page<Discount> pbean=(Page<Discount>)request.getAttribute("pb");
                     blist=pbean.getBeanlist();
                     BookDao bookdao=new BookDao();
                     if(blist!=null&&blist.size()>=0)
                     {
                     
                      for(int i=0;i<=blist.size()-1;i++)
                     {         
                           Discount b=blist.get(i);
                          // Book book = bookdao.getIdBook(String.valueOf(b.getId())); 
       %>
    <tr>
       <td><%=b.getCreatedate()%>-<%=b.getDeadline()%></td>
       <%
         if(b.getTypestatus()==0)
         {
        %>
         <td>打折</td>
        <%
         }else
         {
         %>
         <td>代金券</td>
         <%
         }
          %>
       <td><%=b.getNum()%></td>
        <td><%=b.getTypename() %></td>
       <td style="text-align:center;">
       	<a href="changediscount.jsp?id=<%=b.getId()%>&pc=${pb.pc}" class="layui-btn layui-btn-mini layui-btn-normal" style="padding:0 12px;"><i class="layui-icon ">&#xe63c;</i></a>
       	</td>
       <td style="text-align:center;">	<a href="DeleteDiscountServlet?id=<%=b.getId()%>&pc=${pb.pc}" class="layui-btn layui-btn-mini  layui-btn-danger" style="padding:0 12px;"><i class="layui-icon ">&#xe640;</i></a></td>
       <%
         if(b.getShowstatus()==0)
         {
        %>
       <td>过期</td>
       <%
         }
        %>
          <%
         if(b.getShowstatus()==1)
         {
        %>
       <td>未过期</td>
       <%
         }
        %>
          <%
         if(b.getShowstatus()==2)
         {
        %>
       <td>过期</td>
       <%
         }
        %>
       </tr>
       <%
       }
       }
        %>
   
  </tbody>
</table>
<div class="da"    style="font-size:13px;color:#333;margin-top:16px;">共 ${pb.all}条记录 ，第${pb.pc} /${pb.tp}页  </div> 
         
         
  <div style="margin-top:24px;">
  <ul class="pation">
  <!--当前页为第1页时不为首页分配链接-->
 <c:choose>
 <c:when test="${pb.pc eq 1}"></c:when>  
 <c:otherwise  ><li><a href="${pb.url}&pc=${1}">首页</a></li></c:otherwise>
 </c:choose>

    <!--当前页为第1页时不为尾页分配链接-->

 <c:choose>
 <c:when test="${pb.pc eq 1}"></c:when>  
 <c:otherwise  ><li><a href="${pb.url }&pc=${pb.pc-1}">上页</a></li></c:otherwise>
 </c:choose>
   <!--如果小于6个数字时   是的真的话 -->   <!--如果是不是的话  -->
 <c:choose>
 <c:when test="${pb.tp <6}">
<c:set value="1" var="begin"></c:set>
<c:set value="${pb.tp}" var="end"></c:set>
</c:when>

 
<c:otherwise>
<c:set  value="${pb.pc-2 }" var="begin"></c:set>
<c:set  value="${pb.pc+3}" var="end"></c:set>


<c:if test="${pb.pc<3 }">
<c:set value="1" var="begin"></c:set>
<c:set value="6" var="end"></c:set>
</c:if>

<c:if test="${pb.pc+3>pb.tp }">
<c:set  value="${pb.tp-5 }" var="begin"></c:set>
<c:set  value="${pb.tp}" var="end"></c:set>
</c:if>
</c:otherwise>
 </c:choose>   
    
    
    
    
    
    
    
    <c:forEach begin="${begin }" end="${end }" var="page">
    <c:choose>
    <c:when test="${pb.pc eq page}"><li class="active"   ><a style="color:#fff;cursor:pointer">${page }</a></li></c:when>
    <c:otherwise ><li><a  style="cursor:pointer" href="${pb.url }&pc=${page}">${page}</a></li> </c:otherwise>
    </c:choose>
    </c:forEach>




    <!--当前页为最后一页时时不分配连接  -->
 <c:choose>
 <c:when test="${pb.pc eq pb.tp or pb.pc eq null }"></c:when>  
 <c:otherwise  ><li><a href="${pb.url }&pc=${pb.pc+1}">下页</a></li></c:otherwise>
 </c:choose>
 
 
   <!--当前页为最后一页时不为尾页分配链接-->
   <c:choose>
 <c:when test="${pb.pc eq pb.tp}"></c:when>  
 <c:otherwise  ><li><a href="${pb.url }&pc=${pb.tp}">尾页</a></li></c:otherwise>
 </c:choose>
  
 
 
 </ul>
  
   </div> 

  	</div>
  	
  		<div class="layui-tab-item">
  	</div>
  	
  </div>
</div>      
    	
    </div>
    
    
    
    
    <div class="layui-tab-item" >
    
  </div>
</div>
 
</div>
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/maruti.html"></script>
<script src="layui/layui.js"></script>
<script src="src/jquery.easydropdown.js"></script>
<script src="js/nicescroll/jquery-nicescroll.js"></script>
<script src="js/jquery-jSelect.min.js" ></script>
<script>
$("#reduce").click(function(){
 window.location = "NewReducePageServlet";
 return false;

});
$("#discount").click(function(){
  
 window.location = "DiscountPageServlet";
 return false;
});
$("#grounp").click(function(){
 window.location = "grounpmanage.jsp";
 return false;
});
$("#addactive").click(function(){
window.location ="addactive.jsp";
return false;
});
	//			$("#mySelect").jSelect();
//			$("#city").jSelect();

			$(".select").jSelect();
			$("#city_select").css("width","80px");
layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});
layui.use('element', function(){
  var element = layui.element();
});
layui.use('laydate', function () {
var laydate = layui.laydate;
var start =
{

event: 'click',
format: 'YYYY-MM-DD',
isclear: true,
istoday: true, 
issure: true,
start: laydate.now(),
fixed: false,
zIndex: 99999999,
choose: function (dates) {
end.min = dates; 
end.start = dates
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
laydate(start)
});

 $("#LAY_end_time").click(function () {
end.elem = this;
laydate(end)
});

})
</script>
  </body>
</html>
