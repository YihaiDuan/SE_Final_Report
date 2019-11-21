<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*,Extenddao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'topicdetail.jsp' starting page</title>
    
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
<style type="text/css">
.topicimages{
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
 z-index:1000;
}
.layui-layer-content{
  padding:8px;
  text-indent:2em;
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
    	.see{
    	   color:#F86731;
    	   text-align:center;
    	}
</style>
  </head>
  
  <body>
   <div id="content">
     <%
                    int id = Integer.parseInt(request.getParameter("id"));
                     Topicdao topicdao = new Topicdao();
                    Topic topic =topicdao.getTopic(id);
     %>
		<div id="content-header1">
			<h1 style="font-weight:bold;">专题详情</h1>
		</div>
		<div style="padding:0 20px;">
		    <form action="ChangeTopicsServlet" method="post" class="layui-form" enctype="multipart/form-data">
		 <div class="row">
               <a href="specialmanage.jsp" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
               <a href="changespecialdetail.jsp?id=<%=id%>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">修改专题图书</a> 
         </div> 
        
			<table class="layui-table">
				<colgroup>
					<col width="400">
				</colgroup>
				<thead>
					<tr>
						<th style="text-align:center;">专题信息</th>
						<th style="text-align:center;">内容</th>
					</tr>
				</thead>
				    <tbody>
                                   <tr>
                                   <td style="text-align:center">创建时间</td>
                                   <td style="text-align:center;"><input type="text" name="date" value="<%=topic.getDate()%>" style="width:400px;height:27px;margin-bottom:0px;color:#ccc"></td>
                                   </tr>
                                   <tr>
                                   <td style="text-align:center">标题</td>
                                   <td style="text-align:center;"><input type="text" name="topicname" value="<%=topic.getTopicname()%>" style="width:400px;height:27px;margin-bottom:0px;"></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">内容</td>
                                   <td style="text-align:center;"><textarea  name="describ" style="width:400px;height:60px;margin-bottom:0px;"><%=topic.getDescrib()%></textarea></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">图片</td>
                                   <td style="text-align:center;"><input type="file" name="topicimg"  style="width:400px;height:27px;margin-bottom:0px;"></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">显示</td>
                                   <td style="text-align:center;">       	
                                   <div class=" layui-form layui-form-item" style="margin-bottom:0;">
                                   <input type="checkbox" name="showstatus" title="显示" style="height:28px;line-height:26px"  value="1"
                                     <%
                                      if(topic.getShowstatus()==1)
                                      {
                                     %>
                                     checked="checked";
                                      <%
                                       }
                                      %>
                                    >
                                   </div></td>
                                   </tr>
             <input type="hidden" name="id" value="<%=topic.getId()%>">
			</table>

<table class="layui-table" >
  <colgroup>
    <col width="60">
    <col width="160">
    <col width="180">
    <col width="120">
  </colgroup>
				  <thead>
    <tr>
      <th>索书号</th>
      <th>书名</th>
      <th>作者</th>
      <th>藏书量</th>
    </tr> 
  </thead>
				<tbody>			                   
                 <% 
                    TopicBookDao topicbookdao = new TopicBookDao();
                    List<TopicBook> list = new ArrayList<TopicBook>(topicbookdao.getListTopicBook(id));
                    if(list!=null && list.size()>=0)
                    {
                       for(int i=0 ; i<list.size(); i++)
                       {
                           TopicBook topicbook = list.get(i);
                      
                           Book book = topicbookdao.getBook(topicbook.getId());     
                  %>
					<tr >

						<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getBookid()%></div></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getBooktitle()%></div></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getAuthor()%></div></td>
					
						<td><%=book.getBooknum() %></td>
					</tr>
					<%
					
					}
					}
					 %>
				</tbody>
			</table>

					    <input type="submit" class="layui-btn" value="提交数据" style="padding:0 30px;margin-left:490px;">

			</form>
			</div>
			
		</div>
	
			
    <script src="js/excanvas.min.js"></script> 
    <script src="js/jquery.min.js"></script> 
    <script src="js/jquery.ui.custom.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script> 
    <script src="layui/layui.js"></script>
<script src="js/maruti.html"></script>
<script src="layui/lay/modules/layer.js"></script>
    <script type="text/javascript">
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
    
    </script>     
  </body>
</html>
