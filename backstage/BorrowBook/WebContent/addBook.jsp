<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*,Extenddao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addBook.jsp' starting page</title>
    
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
<link rel="stylesheet" href="css/j-select.css" />
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
    	.box{
    	 width:300px;
    	 height:200px;
    	 background:#E6F1FB;
    	 position:fixed;
    	 left:50%;
    	 margin-left:-150px;
    	 top:50%;
    	 margin-top:-100px;
    	 z-index:100;
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
    	   background:#666;
    	   color:#fff;
    	   padding:6px 10px;
    	   border-radius:8px;
    	   margin-right:10px;
    	   font-size:13px;
    	}
    	.putin:hover{
    	  background:#31708f;
    	  color:#fff;
    	   cursor: pointer;
    	}
    	.return:hover{
    	 background:#007bff;
    	  color:#fff;
    	   cursor: pointer;
    	}
    	.select1{
    	  padding:4px;
    	
    	}
    	#add{
    	cursor:pointer;
    	}
    	.position{
    	 width
    	
    	}
    	.floor,.room{
    	
    	width:134px;
    	
    	}
    	.j_select .current_select .select_icon {
       margin: 0;
       padding: 0;
       position: absolute;
       right: 15px;
       top: 10px;
       }
       input[type="text"]{
       height:30px;
       width:272px;
       }
       input[readonly]{
       background:#fff;
       }
       .layui-select-title{
       padding-top:16px;
       }
    .layui-form-select .layui-edge {
    position: absolute;
    right: 209px;
    top: 55%;
    cursor: pointer;
    border-width: 6px;
    border-top-color: #c2c2c2;
    border-top-style: solid;
    transition: all .3s;
    -webkit-transition: all .3s;
}
.layui-form-select dl {
    display: none;
    position: absolute;
    left: 193px;
    top: 50px;
    padding: 5px 0;
    z-index: 999;
    min-width: 272px;
    border: 1px solid #d2d2d2;
    max-height: 300px;
    overflow-y: auto;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
    box-sizing: border-box;
}
 .layui-form-selected dl {
    display: block;
}
.myselect{
 display:inline-block;
 width:272px;
 height:30px;
 line-height:30px;
 overflow:hidden;
 position:relative;
 font-size:18px;
 
}
.floorselect,.roomselect{
 display:inline-block;
 width:134px;
 height:30px;
 line-height:30px;
 overflow:hidden;
 position:relative;
 font-size:18px;
}
.xiajiantou{
position:absolute;
right:1px;
top:1px;
width:37px;
height:28px;
}
a{
text-decoration:none;
}
    </style>
 
  </head>
  
  <body>
  
      <div id="content" >
       <form action="addBookServlet"  method="post" enctype="multipart/form-data"  style="display:block;">
		<div id="content-header1">
			<h1 style="font-weight:bold;">添加馆藏图书</h1>
		</div>
		<div style="padding:0 20px;" id="catalogue">
			<table class="layui-table">
				<colgroup>	
					<col width="400">		
				</colgroup>
				<thead>
				<tr>
				<th style="text-align:center">基本信息</th>
				<th style="text-align:center">内容</th>
				</tr>
				</thead>
                     <tbody>
                                    <tr>
                                    <td >父类</td>
                                    <td > 
   		                            <div class="myselect">
                                    <select style="width:272px;border-radius:0;height:30px;line-height:30px;" name="categoryid"  class="form-control">
                                      <%
                                      BookTypeDao booktypedao=new BookTypeDao();
                                      List<BookType> list=booktypedao.getListBookType();
                                      if(list!=null&&list.size()>=0)
                                      {
                                            for(int i=0;i<=list.size()-1;i++)
                                        {
                                           BookType booktype=list.get(i);
                                     
                                       %>
                                    <option value="<%=booktype.getId()%>" class="lick"><%=booktype.getName()%></option>
                                    <%
                                         }
                                      }
                                     %>
                                    </select>
                                   <image src="images/xiajiantou.png" class="xiajiantou"></image>
                                    </div>
                                    </td>
                                    </tr>
                                    
                                      <tr>
                                    <td >子类</td>
                                    <td > 
   		                            <div class="myselect">
                                    <select style="width:272px;border-radius:0;height:30px;line-height:30px;" name="categoryid"  class="form-control"  id="select2">
                                            <%
                                      CategoryDao categorydao=new CategoryDao();
                                      List<Category> clist = categorydao.getListCategory(list.get(0).getId());
                                      if(clist!=null&&clist.size()>=0)
                                      {
                                            for(int i=0;i<=clist.size()-1;i++)
                                        {
                                           Category categroy=clist.get(i);
                                     
                                       %>
                                    <option value="<%=categroy.getCategoryid()%>" class="lick"><%=categroy.getName()%></option>
                                    <%
                                         }
                                      }
                                     %>
                                    </select>
                                   <image src="images/xiajiantou.png" class="xiajiantou"></image>
                                    </div>
                                    </td>
                                    </tr>
                                    
                                     <tr>
                                    <td>图书位置</td>
                                    <td>
                                        <div class="floorselect">                                    
                                    	<select name="floor" class="floor form-control" style="display:inline-block;border-radius:0;height:30px;line-height:30px;font-size:13px;">
			                                 <option value="第一层">第一层</option>
			                                 <option value="第二层">第二层</option>
			                                 <option value="第三层">第三层</option>
			                                 <option value="第四层">第四层</option>
			                                 <option value="第五层">第五层</option>
		                                 </select>
		                                  <image src="images/xiajiantou.png" class="xiajiantou"></image>
		                                 </div>  
		                                 <div class="roomselect">
		                                 <select name="room" class="room form-control" style="display:inline-block;border-radius:0;height:30px;line-height:30px;font-size:13px;">
			                                 <option value="101室">1001室</option>
			                                 <option value=“102室”>1002室</option>
			                                 <option value=“103室”>1003室</option>
			                                 <option value=“104室”>1004室</option>
		                                 </select>
		                                  <image src="images/xiajiantou.png" class="xiajiantou"></image>
		                                 </div>

                                    </td>
                                    </tr> 
                                    <tr>
                                    <td>目录编号</td>
                                    <td><input type="text" class="bookid" style="margin:8px 0;width:272px;height:30px;" name="bookid"></td>
                                    </tr> 
                                     <tr>
                                    <td>书名</td>
                                    <td><input type="text" class="booktitle" style="margin:8px 0;width:272px;height:30px;" name="booktitle"></td>
                                    </tr>
                                    <tr>
                                    <td>作者</td>
                                    <td><input type="text" class="author" style="margin:8px 0;width:272px;height:30px;" name="author"></td>
                                    </tr>
                                    <tr>
                                    <td>封面上传</td>
                                    <td style="margin:0 auto;"><input type="file" class="bookimages" style="margin:8px 0;display:inline-block;width:272px;" name="bookimages" ></td>
                                    </tr>
                                     <tr>
                                    <td>出版社</td>
                                    <td><input type="text" class="publish" style="margin:8px 0;width:272px;height:30px;" name="publish"></td>
                                    </tr>
                                     <tr>
                                    <td>版本号</td>
                                    <td><input type="text" class="publishnumber" style="margin:8px 0;width:272px;height:30px;" name="publishnumber"></td>
                                    </tr>
                                       <tr>
                                    <td>图书介绍</td>
                                    <td><textarea  class="introduce" style="margin:8px 0;width:400px;height:100px;" name="introduce"></textarea></td>
                                    </tr>
                                       <tr>
                                    <td>书评</td>
                                    <td><textarea class="review" style="margin:8px 0;width:400px;height:100px;" name="review"></textarea></td>
                                    </tr>
                                        <tr>
                                    <td>导读</td>
                                    <td><textarea class="guidreading" style="margin:8px 0;width:400px;height:100px;" name="guidreading"></textarea></td>
                                    </tr>
                                       <tr>
                                  <td  colspan="2" style="align:center">   
                                      
                                    <a class="return" id="nextstep" >下一步</a>
                                 
                                    
                                  </td>
                                    </tr>
                                    </tbody>
			</table>
		</div>
                <div  id="library" style="margin-left:20px;;">
            <div class="container-fluid">
                <div class="row">
                    <div >
                        <div class="card">
                            <div class="header">
                              <h4 class="title" style="float:right;font-size:16px;margin-right:80px;margin-bottom:20px;">添加藏书信息 <a id="add">+</a></h4>                             
                            </div>
                            <div class="content">
                                <table class="layui-table">
                                    <colgroup>	
					               <col width="400">		
				                    </colgroup>
				                     <thead>
				                     <tr>
				                     <th style="text-align:center">图书信息</th>
				                     <th style="text-align:center">内容</th>
				                     </tr>
				                     </thead>
                                    <tbody id="table">     
                                    <tr class="addbookson" id="addbookson">
                                    <td>图书编号</td>
                                    <td><input type="text" class="author" style="margin:8px 0;width:272px;" name="booksonid"></td>
                                    </tr>
                                    
                                    </tbody>
                                      <tr >
                                  <td  colspan="2" style="align:center;">  
                                   <a class="return" href="BookPageServlet?pc=1" id="laststep">返回上一层</a>     
                                   <input type="submit" class="return" value="点击上传" style="border:0;"/>
                                  
                                  </td>
                                    </tr>
                                </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </form>
    <script src="js/excanvas.min.js"></script> 
    <script src="js/jquery.min.js"></script> 
    <script src="js/jquery.ui.custom.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script>
    <script src="dist/sweetalert-dev.js"></script>  
    <script src="js/jquery-jSelect.min.js"></script> 
    <script src="layui/layui.js"></script>
     <script type="text/javascript">
             layui.use('form', function(){
             var form = layui.form();
             //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
          });
           });
           $("dd").click(function(){
           alert("点击了");
               var floor=$(this).text();       
               	if(floor=="第一层")
			{
				var a=["1001室","1002室","1003室","1004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
				
				
			}
			if(floor=="第二层")
			{
				var a=["2001室","2002室","2003室","2004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
			}
			if(floor=="第三层")
			{
				var a=["3001室","3002室","3003室","3004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
			}
			if(floor=="第四层")
			{
				var a=["4001室","4002室","4003室","4004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}	
			}
				if(floor=="第五层")
			{
				var a=["5001室","5002室","5003室","5004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}	
			}
    
           });
           
             $(".floor").change(function(){
			var floor=$(".floor").find("option:selected").val();
			if(floor=="第一层")
			{
				var a=["1001室","1002室","1003室","1004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
				
				
			}
			if(floor=="第二层")
			{
				var a=["2001室","2002室","2003室","2004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
			}
			if(floor=="第三层")
			{
				var a=["3001室","3002室","3003室","3004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
			}
			if(floor=="第四层")
			{
				var a=["4001室","4002室","4003室","4004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}	
			}
				if(floor=="第五层")
			{
				var a=["5001室","5002室","5003室","5004室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}	
			}
			});
      $("#library").hide();
      $("#nextstep").click(function(){ 
        $("#library").show();
        $("#catalogue").hide();
        return false;
      });
      $("#laststep").click(function(){
        $("#library").hide();
        $("#catalogue").show();
         return false;
      
      });
      $("#add").click(function(){
      var newobj=$("#addbookson").clone();
      newobj.find('.author').val('');
      $("#table").append(newobj);
      });
      <%String action = (String)request.getParameter("action"); %>
       var action='<%=action%>'; 
       if(action=='success')
       {
        swal("添加成功!", "图书信息已成功添加!", "success");
        }
       $(".lick").click(function(){
           var val = $(this).val();
           var args={"id":val,"time": new Date()};
           $.post("UploadSmallClassServlet",args,function(data){
             var news = eval("("+data+")");
             $("#select2").empty();
             for(var i=0;i<news.id.length;i++)
             {
             $("#select2").append("<option value='"+news.id[i]+"'>"+news.name[i]+"</option>");
             }
           });
           
         }); 
     </script>
  </body>
</html>
