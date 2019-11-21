<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*,Extenddao.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/matrix-style2.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="layui/css/layui.css" />
<link rel="shortcut icon" type="image/x-icon"
	href="http://kunkalabs.com/im/favicon.ico" />
<link rel="stylesheet" type="text/css" href="demo.css" />
<link rel="stylesheet" type="text/css" href="themes/easydropdown.css" />
<link rel="stylesheet" href="css/j-select.css" />
 <link rel="stylesheet" href="dist/sweetalert.css">
 <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style: none;
}

body {
	font-size: 12px;
	overflow-y: scroll;
}

/*.form {
				width:100px; margin:10px 0;padding:5px;
			}*/
.select2-container {
	display: none;
}

.j_select {
	float: left;
	margin-left: 360px;
	border-right: 0;
}

.j_select .current_select .select_icon {
	top: 9px;
}
  	#prompt,#smallclass{
    	  display:none;
    	  position:fixed;
    	  top:90px;
    	  left:50%;
    	  margin-left:-200px;	
          width:380px;
          background:#dfecfa;
          z-index:100;
          padding:20px;
          color:#999;
    	}
    	#chahao,#chahao1{
    	 position:absolute;
    	 top:10px;
    	 right:10px;
    	 width:16px;
    	 height:16px;
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
    		font-size:16px;
    		margin-right:20px;
    	}
    	.search:hover{
    		color:#204D74;	
    	}
    	.searchtext{
    		width:180px;
    		border:0;
    		font-size:12px;
    		color:#eee;
    	}
    	.searchsubmit{
    	   border:0;
    	   width:26px;
    	   height:26px;
    	   border-left:1px solid #F4EA2A;
    	   background:url("assets/img/搜索 .png");
    	    background-size: 100%;
    	    
    	}
    	.deleteimage{
    		width:30px;
    		height:30px;
    		
    		
    	}
    	.addtop{
    		font-size:22px;
    		line-height:40px;
    		text-align: center;
    	
    	}
    	.one,.tow{
    		margin:10px 20px;
    		text-align: center;	
    	}
    	.tow{
    		margin-bottom:20px;
    		
    	}
    	.addsubmit{
    		border:0;
    		background:#204D74;
    		padding:6px 30px;
    		color:#fff;
    		
    	}
    	#addmanage{
    		color:#46B8DA;
    		cursor: pointer;
    		
    		
    	}
    	#addmanage:hover{
    		color:#23527C;
    		
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
        label{
        display:inline-block;
        }
        div.uploader {
    position: relative;
    overflow: hidden;
    cursor: default;
    background: #DFECFA;
}
        div.uploader {
    width: 184px;
    cursor: pointer;
}
        div.uploader {
    background-position: 0px -297px;
    height: 28px;
}
div.uploader span.action {
   width: 179px;
    text-align: center;
    text-shadow: #fff 0px 1px 0px;
    font-size: 11px;
    font-weight: bold;
}
 div.uploader span.filename {
    padding:0;
    color: #777;
    width: 0px;
    border-right: solid 1px #bbb;
    font-size: 11px;
}
div.uploader span.action {
    background-color:#DFECFA;
}
.layui-btn-danger {
    background-color: #FF5722;
    color:#fff;
}
a:hover, a:focus {
    text-decoration: none;
    color:#fff;
}  
/* .layui-btn-primary:hover {
    border-color: #005580;
    color: #000;
}    */  
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
.j_select .current_select {
    margin: 0;
    padding: 0;
    height: 32px;
    line-height: 32px;
    text-indent: 1em;
    border: 1px solid #ccc;
        border-right-width: 1px;
        border-right-style: solid;
        border-right-color: rgb(204, 204, 204);
    box-shadow: 1px 1px 2px #ccc;
    outline: none;
    border-right: 0;
}
   .layui-btn:hover {
	opacity: .8;
	filter: alpha(opacity=80);
	color: #d2d2d2;
}
div.uploader span.action {
    background-position: right -409px;
    height: 27px;
    line-height: 27px;
}
</style>
</head>
<body>
    <div id="content">
		<div id="content-header1">
			<h1 style="font-weight:bold;">图书管理</h1>
		</div>
	<div style="padding:0 20px;">
			<table class="layui-table">
				
				<thead>
					   <tr>
                                     <th>订单号</th>
                                     <th>图书编号</th>
                                     <th>用户名</th>
                                     <th>手机号</th>
                                     <th>到期时间</th>
                                     <th>操作</th>
                       </tr>
				</thead>
				<tbody>
				   <%
                                 List<OnlineOrder> blist=new ArrayList<OnlineOrder>();
                                 Page<OnlineOrder> pbean=(Page<OnlineOrder>)request.getAttribute("pb");
                                 blist=pbean.getBeanlist();
                                    if(blist!=null&&blist.size()>=0)
                                    {
                                           for(int i=0;i<=blist.size()-1;i++)
                                   {
                                      OnlineOrderDao odao = new OnlineOrderDao();
                                      OnlineOrder onlineorder=blist.get(i);
                                      User user = odao.getUserOnlineOrder(onlineorder.getId()); 
                                     %>
                                     <tr>
                                       <td><%=onlineorder.getId()%></td>
                                        <td><%=onlineorder.getBookson().getBooksonid()%></td>
                                         <td><%=user.getNickname()%></td>
                                        <td><%=user.getPhone()%></td> 
                                        <td><%=onlineorder.getOrderdate() %></td>
                                        <td><a href="deleteOnlineOrderServlet?id=<%=onlineorder.getId()%>&pc=${pb.pc}">操作完成</a></td>
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
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.ui.custom.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.uniform.js"></script>
		<script src="js/select2.min.js"></script>
		<script src="js/jquery.validate.js"></script>
		<script src="js/matrix.js"></script>
		<script src="js/matrix.form_validation.js"></script>
		<script src="layui/layui.js"></script>
		<script src="src/jquery.easydropdown.js"></script>
		<script src="js/nicescroll/jquery-nicescroll.js"></script>
		<script src="js/jquery-jSelect.min.js"></script>
		<script src="dist/sweetalert.min.js"></script>
        <script src="dist/sweetalert-dev.js"></script>
        <script scr="layui/lay/modules/laypage.js"></script>
		   <script type="text/javascript">
      var action="<%=request.getParameter("action")%>";
      
      if(action=="success")
     {
      swal("操作完成!", "订单已完成处理!", "success"); 	   
     }
      $("#statistics").click(function(){
	       $("#change1").slideToggle(100);
	       $("#change2").slideToggle(100);
	       $("#change3").slideToggle(100);
	       return false;
	    
	    });
        $('#search').click(function(){
	    $('#inputtext').css({"border-bottom":"3px solid #68B3C8"});
	    var val=$("#inputtext").val();
	     if($('#inputtext').width()>100)
	     {
	       window.location.href="reserveBook-search.jsp?content="+val;
	     
	     }
	     else
	     {
	     $('#inputtext').animate({"width":"200px"});
	     }
	  
	  })
     /*  document.querySelector('.example4').onclick = function(){
           var name=$(this).attr("name");
            var args={"booksonid":name,"time": new Date()};
              $.post("deleteBookSonServlet",args,function(data){    
                  if(data==1)
                  {  

                    	swal("上传成功!", "图书信息已经上传!", "success");
                    	setTimeout(function(){
                    	 window.location.reload(true);
                    	},2000)
                      
                  }
                  else
                  {
                  
                  }
                 });
           return false;

       };
         */
 </script>
</body>
</html>