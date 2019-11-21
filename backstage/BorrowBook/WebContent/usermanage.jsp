<%@ page language="java"
	import="java.util.*,hfhdao.*,hfhServlet.*,entity.*"
	pageEncoding="UTF-8"%>
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

<title>My JSP 'usermanage.jsp' starting page</title>

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
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style2.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="layui/css/layui.css" />
<style>
.layui-form-checkbox {
	height: 24px;
	line-height: 22px;
	margin-right: 0;
}

.layui-form-checkbox span {
	font-size: 11px;
}
input[type="checkbox"]{
display:none;
}
.layui-form{
    display:block;
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
	<div id="content">
		<div id="content-header1" style="padding-top:20px">
			<h1 style="font-weight:bold;">用户列表</h1>
		</div>
		<div style="padding:0 20px;">
			<table class="layui-table">
				<colgroup>
					<col width="160"></col>
					<col width="140"></col>
					<col width="80"></col>
					<col width="140"></col>
					<col width="140"></col>
					<col width="140"></col>
					<col width="80"></col>
				</colgroup>
				<thead>
					<tr>
						<th>用户账号</th>
						<th>昵称</th>
						<th>性别</th>
						<th>E-mail</th>
						<th>手机号</th>
						<th>注册时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<User> blist=new ArrayList<User>();
					                     Page<User> pbean=(Page<User>)request.getAttribute("pb");
					                     blist=pbean.getBeanlist();
					                     if(blist!=null&&blist.size()>=0)
					                     {
					                     
					                      for(int i=0;i<=blist.size()-1;i++)
					                   {
					  
					                           User b=blist.get(i);
					%>
					<tr>
						<td><%=b.getUserid() %></td>
                        <td><%=b.getNickname() %></td>
                        <%
                        if(b.getSex()=="0")
                        {
                         %>
                          <td>男</td>
                         <%
                         }else{
                          %>
                          <td>女</td>
                          <%
                          }
                           %>
                        <td><%=b.getE_mail() %></td>
                        <td><%=b.getPhone() %></td>
                        <td><%=b.getStarttime()%></td>
						<td>
							  <div class=" layui-form layui-form-item" style="margin-bottom:0;">
                              <input type="checkbox" name="" title="黑名单" style="height:28px;line-height:26px;" class="blacklist" 
                              <% 
                                 if(b.getJurisdiction()==1)
                                 {
                               %>
                               checked="true"
                              <%
                              }
                               %>
                              >
                               </div>
						</td>
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
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/fullcalendar.min.js"></script>
	<script src="js/matrix.js"></script>
	<script src="js/matrix.calendar.js"></script>
	<script src="layui/layui.js"></script>
	<script>
		layui.use('form', function() {
			var form = layui.form();

			//监听提交
			form.on('submit(formDemo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				return false;
			});   
		});

          $("body").on("click",".layui-form-checkbox",function(){
	    	   var text= $(this).parent().parent().parent().find('td').eq(0).text();
               var th=$(this);
               var args={"userid":text,"time": new Date()};
              $.post("ChangeUserServlet",args,function(data){ 
        	 
                    if(data==1)
                    {  
                    
                         if(th.attr("name")==true)
                         {
                          th.attr("name")==false;
                         }else{
                         
                         th.attr("name")==true;
                         }
                    }
              
                   });
			});
    </script>
		</body>
		</html>
	