<%@ page language="java" import="java.util.*,hfhdao.*,hfhServlet.*,entity.*" pageEncoding="UTF-8"%>
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
    
    <title>My JSP 'admin.jsp' starting page</title>
    
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

 <style type="text/css">
			* {
				margin:0;padding:0;list-style:none;
			}
			body {
				font-size:12px;overflow-y: scroll;
			}

			/*.form {
				width:100px; margin:10px 0;padding:5px;
			}*/
            .select2-container
            {
            	display:none;
            }
            .j_select{
            	
            	border-right:0;
            	display:inline-block;
            }
            .news{
            	font-size:14px;
            	line-height:22px;
            	font-weight:bold;
            	display:inline-block;
            	margin:0 6px;
                margin-left:26px;
            }
            .j_select .current_select {
    margin: 0;
    padding: 0;
    height: 26px;
    line-height:26px;
    text-indent: 1em;
    border: 1px solid #ccc;
        border-right-width: 1px;
        border-right-style: solid;
        border-right-color: rgb(204, 204, 204);
    box-shadow: 1px 1px 2px #ccc;
    outline: none;
    border-right: 0;
}
 	#prompt{
    	  display:none;
    	  position:fixed;
    	  top:90px;
    	  left:50%;
    	  margin-left:-200px;	
          width:300px;
          background:#dfecfa;
          z-index:100;
          padding:20px;
          color:#999;
    	}
    	#chahao{
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
    		font-size:18px;
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
    		margin-left:100px;
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
      padding:4px 7px 0 20%;
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
.radio input[type="radio"], .checkbox input[type="checkbox"] {
    float: left;
    margin-left: 0px;
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
      padding:4px 7px 0 20%;
      margin-right:6px;
      font-size:13px;
      	font-family:微软雅黑
        }
		</style>
  </head>
  
  <body>
    <div id="content">
    	<div class="row " style="padding-top:16px;clear:both;">
  	  <div id="content-header1">
      <h1 style="font-weight:bold;">添加管理员</h1>
     </div>
     <div style="padding:6px 40px;">
     	<form action="addAdminServlet" method="post">
     	 <span class="news">账号:</span><input type="text" name="username" style="margin-top:8px;height:26px;line-height:26px;width:155px;" >	
     	 <span class="news">密码:</span><input type="text" name="password" style="margin-top:8px;height:26px;line-height:26px;width:155px;" >	
     	 <span class="news">性别:</span>
     	 <div class="form" style="display:inline-block;">
			<select id="city" class="select" name="age" >
				<option value="男">男</option>
				<option value="女">女</option>
			</select>	
          </div>	
           <input type="hidden" name="pc" value="${pb.pc}"> 
     		 <span class="news">联系方式:</span><input type="text" name="phonenumber" style="margin-top:6px;height:26px;line-height:20px;width:155px;">	
     	     <input type="submit" class="layui-btn layui-btn-small layui-btn-danger" style="margin-left:15px;margin-top:-6px;font-size:11px;"  value="确定">
     	</form>
     </div>
 </div>
  <div id="content-header1">
      <h1 style="font-weight:bold;">管理员列表</h1>
  </div>
		<div style="padding:0 20px;">
				<table class="layui-table" >
  <colgroup>
    <col width="180">
    <col width="80">
    <col width="180">
    <col width="120">
    <col width="120">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>账号</th>
      <th>性别</th>
      <th>联系方式</th>
      <th colspan="2" style="text-align: center;">操作</th>
    </tr> 
  </thead>
				<tbody>
				                   
                                   <% 
                     List<Admin> blist=new ArrayList<Admin>();
                     Page<Admin> pbean=(Page<Admin>)request.getAttribute("pb");
                     blist=pbean.getBeanlist();
                     if(blist!=null&&blist.size()>=0)
                     {
                     
                      for(int i=0;i<=blist.size()-1;i++)
                   {
  
                           Admin b=blist.get(i);
                     
                  %>
					<tr name="<%=b.getId()%>" pass="<%=b.getPassword()%>">
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=b.getUsername() %></div></td>
							<%
                                        	 if(b.getAge()==0)
                                        	 {
                                        		 %>
                                        		<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">男</div></td>
                                        	<% }
                                        	 else
                                        	 {
                                        		 %>
                                        		<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">女</div></td>
                                            <% 		 
                                        	 }
                                        	%>
						
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=b.getPhonenumber()%></div></td>
						<td><a href="#" name="<%=b.getId()%>"
							class="layui-btn layui-btn-mini layui-btn-normal add"
							style="padding:0 12px;"><i class="layui-icon ">&#xe63c;修改</i></a></td>
						<td><a href="deleteAdminServlet?id=<%=b.getId()%>&pc=${pb.pc}"
							class="layui-btn layui-btn-mini  layui-btn-danger delete"
							style="padding:0 12px;"><i class="layui-icon ">&#xe640;删除</i></a></td>
					</tr>
					<%
					}
					}
					 %>
				</tbody>
			</table>
		</div>
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

          
	

		<div id="prompt">
	<div class="addtop">修改管理人员</div>
	  <img src="images/chahao.png" id="chahao"/>
	   <form action="UpdateServlet" method="post">
		<div class="one">
		<label name="username" style="margin-right:6px;">账 号: </label><input type="text" name="username" id="u"/>
	    </div>
	    <div class="tow">
		<label name="password" style="margin-right:6px;">密 码: </label><input type="password" name="password" id="p"/>
	    </div>
	    <div class="tow">
		<label name="age" style="margin-right:6px;">性 别: </label><span style="display:inline-block;width:203px;" >男：<input type="radio" name="age" value="男" id="man" checked="false"><span style="margin-left:30px;">女：</span><input type="radio" name="age" value="女" id="woman" checked="false"/></span>
	    </div>
	    <div class="tow">
		<label name="phonenumber" style="margin-right:6px;">手机号: </label><input type="text" name="phonenumber" id="phone"/>
	    </div>
	    <input type="hidden" name="id" value="" id="id"> 
	    <input type="hidden" name="pc" value="${pb.pc}"> 
		<input type="submit"  class="addsubmit" value="点击上传">
		</form>
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
    <script src="js/jquery-jSelect.min.js" ></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
     <script type="text/javascript">
     var u;
        $(".add").click(function(){
         $("#id").val($(this).parent().parent().eq(0).attr("name"));
         $("#p").val($(this).parent().parent().eq(0).attr("pass"));
          $("#u").val($(this).parent().parent().children().children().eq(0).text());
          var age=$(this).parent().parent().children().children().eq(1).text();
          if(age=="女")
          {
           $("#man").attr('checked','true');
          }else{
           $("#woman").attr('checked','true');
          }
          $("#phone").val($(this).parent().parent().children().children().eq(2).text()); 
          
        /*   var args={"id":$(this).attr("name"),"time": new Date()};
          $.post("UpdateServlet",args,function(data){
            news=eval("("+data+")");
            id=news.id;
            u=news.username;
            age=news.age;
            phone=news.phonenumber;
          });
         $("#u").val(u);
          */
         $("#prompt").show();
         return false;
        });
        $("#chahao").click(function(){
        $("#prompt").hide();
        });
  $(".select").jSelect();
  $("#city_select").css("width","80px");
  $('.delete').click(function(){
	  var href=this.href;
	  swal({   title: "确定删除管理员信息吗?",   text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
      window.location.href=href;   
	});
	return false;
	});
       document.querySelector('.example4').onclick = function(){
           var pc=$(this).attr("name");
           var username=$(".username").val();
           var password=$(".password").val();
            var args={"username":username,"password":password,"pc":pc,"time": new Date()};
            $.post("addAdminServlet",args,function(data){    
                  if(data==1)
                  {  
                    	swal("上传成功!", "管理员信息已经上传!", "success");
                  }
                  else
                  {
                    swal("上传失败!", "管理员信息上传失败请重新上传!", "error");
                  }
                 });
           return false;
    
       };
     </script>
  </body>
</html>
