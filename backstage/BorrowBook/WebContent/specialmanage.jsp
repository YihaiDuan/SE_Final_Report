<%@ page language="java" import="java.util.*,entity.*,Extenddao.*,newServlet.*" pageEncoding="UTF-8"%>
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
    
    <title>My JSP 'specialmanage.jsp' starting page</title>
    
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
<link rel="stylesheet" href="layui/css/modules/layer/default/layer.css" />
 <link rel="stylesheet" href="dist/sweetalert.css">
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
    	.tbwidth{
    	width:160px;
    	overflow: hidden;
    	white-space: nowrap;
    	text-overflow: ellipsis;
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
    <li class="layui-this">专题列表</li>
    <li>添加专题</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
     	<div style="padding:0 20px;">
  	<table class="layui-table" >
  <colgroup>
    <col width="160">
    <col width="160">
    <col width="160">
    <col width="80">
     <col width="120">
    <col width="160">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>发表时间</th>
      <th>标题</th>
      <th>内容</th>
      <th>图片</th>
      <th>查看详请</th>
      <th>显示</th>
      <th colspan="2" style="text-align: center;">操作</th>
    </tr> 
  </thead>
  <tbody>
   <%
       Topicdao topicdao = new Topicdao();
       List<Topic> list = topicdao.getAllTopic();
       if(list!=null && list.size()>=0)
       {
          for(int i=0;i<list.size();i++)
          {
             Topic topic = list.get(i);
    %>
    <tr name=<%=topic.getId()%>>
       <td><%=topic.getDate()%></td>
       <td><div style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" class="title"><%=topic.getTopicname() %></div></td>
       <td><div style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" class="content"><%=topic.getDescrib() %></div></td>
       <td><div style="width:80px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" class="phone">
       <img src="<%=topic.getTopicimg()%>" class="slideimage"></img>
       <img src="<%=topic.getTopicimg()%>" class="bigslideimage"></img>
       </div></td>
       <td><a href="topicdetail.jsp?id=<%=topic.getId()%>" class="see" name="<%=topic.getId()%>">点击查看</a></td>
       <td>
       	<div class=" layui-form layui-form-item" style="margin-bottom:0;">
      <input type="checkbox" name="" title="显示" class="checkbox" style="height:28px;line-height:26px;"
       <%
           if(topic.getShowstatus()==1)
           {
        %>
           checked="checked";
        <%
         }
         %>
      >
        </div>
       </td>
       <td style="text-align:center;">
       	<a href="changetopic.jsp?id=<%=topic.getId()%>" class="layui-btn layui-btn-mini layui-btn-normal " style="padding:0 12px;"><i class="layui-icon ">&#xe63c;</i></a>
       	</td>
       <td style="text-align:center;">	<a href="DeleteTopicServlet?id=<%=topic.getId()%>" class="layui-btn layui-btn-mini  layui-btn-danger delete" style="padding:0 12px;"><i class="layui-icon ">&#xe640;</i></a></td>
    </tr>
    <%  
          }
       }
     %>
  </tbody>
</table>
	 

  </div>	
    </div>
    
    
    
    
    <div class="layui-tab-item" >
    <form action="AddTopicServlet" method="post" class="topic" enctype="multipart/form-data">
    	 <table style="width:500px;margin:0 auto;font-size:12px;" class="tatb">
    	 	<tr style="padding-bottom:18px;">
    	 		<td>标题</td>
    	 		<td><input type="text" style="height:27px;line-height:27px;width:330px;margin-top:10px;margin-right:10px;" name="topicname"></td>
    	 	</tr>
    	 
    	 	<tr>
    	 		<td>内容</td>
    	 		<td >
    	 			<textarea style="width:330px;height:80px;" name="describ"></textarea>
    	 		</td>
    	 	</tr>
    	 	<tr >
    	 		<td>图片</td>
    	 		<td><input type="file" style="height:22px;line-height:22px;width:330px;margin-right:10px;" name="topicimg"></td>
    	 	</tr>
    	 	
    	  <tr>
    	  	<td style="margin-top:6px;">显示</td>
    	  	<td> 
    	  		<div class=" layui-form layui-form-item" style="margin-bottom:0;">
                <input type="checkbox" name="showstatus" title="显示" value="show">
                </div>
    	  	</td>
    	  </tr>
    	  <tr>
    	 	<td>图书</td>
    	 	<td><input type="button" class="layui-btn choice" value="选择图书" style="min-width:95px;height:25px;line-height:25px;font-size:12px;" ></td>
    	  </tr>
    	 
    	 </table>
    	  <input type="hidden" name="topicbook" value="" class="topicbooks"> 	
    	  <input type="submit" class="layui-btn layui-btn-small layui-btn-danger submit" value="确定" style="width:120px;margin-left:420px;margin-top:25px;margin-bottom:25px;">
    </form>
    	 	<div class="content topicbook" style="margin-top:10px;" >
    	 	
    	 	      <button class="layui-btn  layui-btn-radius layui-btn-small return" style="height:32px;line-height:32px;padding:0 30px;font-size:12px;">返回主题</button>
    	 	     	 	<table class="layui-table" >
      <colgroup>
    	           	<col width="160">
					<col width="200">
					<col width="180">
					<col width="160">
					<col width="120">
					<col>
     </colgroup>
				  <thead>
    <tr>
      <th>索书号</th>
      <th>书名</th>
      <th>作者</th>
      <th>出版社</th>
      <th>藏书量</th>
      <th>操作</th>
    </tr> 
  </thead>
				<tbody id="table">			                   
				</tbody>
			</table>

			<div class="form" style="float:left;margin-left:320px;">
				<select id="city" class="select" name="searchtype" style="width:80px;">
					<option value="书名">书名</option>
					<option value="ISBN">ISBN</option>
				</select>
			</div>
			<input type="text" class="inputtext" name="content"
				style="height:32px;line-height:32px;box-shadow: 1px 1px 2px #ccc;" />
			<input type="button"
				class="layui-btn layui-btn-small layui-btn-normal sure" value="确定"
				style="margin-top:-10px;padding:0 24px; height:32px;line-height:32px;">
			 	<form action="TopicBookSerchServlet?id=<%-- <%=id%> --%>" method="post">
			<table class="layui-table">
				<colgroup>
					<col width="160">
					<col width="200">
					<col width="180">
					<col width="160">
					<col width="120">
					<col>
				</colgroup>
				<thead>
					<tr>		   
						<th>索书号</th>
						<th>书名</th>
						<th>作者</th>
						<th>出版社</th>
						<th>藏书量</th>	
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="searchtale">
<%-- 
					   <tr>
						<td><div class="tbwidth"></div><%=book.getBookid()%></td>
						<td><div class="tbwidth"><%=book.getBooktitle()%></div></td>
						<td><div class="tbwidth"><%=book.getAuthor()%></div></td>
						<td><div class="tbwidth"><%=book.getPublish()%></div></td>
						<td><div class="tbwidth"><%=book.getBooknum()%></div></td>
					    <td><a href="#" name="<%=book.getBookid() %>"
							class="layui-btn layui-btn-mini layui-btn-normal add"
							style="padding:0 12px;"><i class="layui-icon ">&#xe654;</i></a></td>
					</tr> --%>

				</tbody>
			</table>
			</form>
    </div>
  </div>
</div>
 </div>
</div>


<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></scrip1t> 
<script src="js/bootstrap.min.js"></script> 
<script src="layui/layui.js"></script>
<script src="js/maruti.html"></script>
<script src="layui/lay/modules/layer.js"></script>
<script src="dist/sweetalert.min.js"></script>
<script src="dist/sweetalert-dev.js"></script>
 <script src="js/jquery-jSelect.min.js"></script>
<script>
       var arr = new Array(); 
       var arr1 = new Array();
       $(".return").click(function(){
        $(".topic").show();
        $(".topicbook").hide();
       })
       $(".sure").click(function(){
      $("#searchtale").empty();
      var searchtype = $(".select option:selected").val();
      var content = $(".inputtext").val();
      var args={"searchtype":searchtype,"content":content,time:new Date()};
      $.post("AddTopicBokkSearchServlet",args,function(data){
    	  var dae =decodeURIComponent(data) ;
         news=eval("("+dae+")");
        // console.info(news);
        if( jQuery.isArray( news ))
        {
         for(var i=0;i<news.length;i++)
         {
          $("#searchtale").append("<tr><td><div class=\"tbwidth\">"+news[i].bookid+"</div></td><td><div class=\"tbwidth\">"+news[i].booktitle+"</div></td><td><div class=\"tbwidth\">"+news[i].author+"</div></td><td><div class=\"tbwidth\">"+news[i].publish+"</div></td><td><div class=\"tbwidth\">"+news[i].booknum+"</div></td><td><a href=\"#\" name=\""+news[i].bookid+"\" class=\"layui-btn layui-btn-mini layui-btn-normal add\" style=\"padding:0 12px;\"><i class=\"layui-icon \">&#xe654;</i></a></td></tr>");
 
         } 
        }else{
        $("#searchtale").append("<tr><td><div class=\"tbwidth\">"+news.bookid+"</div></td><td><div class=\"tbwidth\">"+news.booktitle+"</div></td><td><div class=\"tbwidth\">"+news.author+"</div></td><td><div class=\"tbwidth\">"+news.publish+"</div></td><td><div class=\"tbwidth\">"+news.booknum+"</div></td><td><a href=\"#\" name=\""+news.bookid+"\" class=\"layui-btn layui-btn-mini layui-btn-normal add\" style=\"padding:0 12px;\"><i class=\"layui-icon \">&#xe654;</i></a></td></tr>");
        
        
        }
      
      });  
       });
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
        
        
   $('.delete').click(function(){
	  var href=this.href;
	  swal({   title: "确定删除这条记录吗?",   text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
      window.location.href=href; 
	  
	});
	return false;
	})
  $("body").on("click",".layui-form-checkbox",function(){
	    	   var text= $(this).parent().parent().parent().eq(0).attr("name");
               var th=$(this);
               var args={"id":text,"time": new Date()};
              $.post("ChangeTopicServlet",args,function(data){ 
        	 
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
$(".slideimage").mouseover(function(e){
    var xx = e.originalEvent.x || e.originalEvent.layerX || 0;
   var yy = e.originalEvent.y || e.originalEvent.layerY || 0;  
  $(this).next().show();
   $(this).next().css("top",yy);
   $(this).next().css("left",xx);
  
});
$(".slideimage").mouseout(function(){
  $(this).next().hide();
});



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
 $(".select").jSelect();
 $(".topicbook").hide();
 $(".choice").click(function(){
  $(".topic").hide();
  $(".topicbook").show();
 });
 $(".add").live("click",function(){
        action = 1;
   for(var i=0;i<arr.length;i++){
       if(arr1[i]==$(this).attr("name"))
       {
       action = 0;
       }
   }
   if(action==1)
   {
   var t={"bookid":$(this).attr("name"),time:new Date()};
   $.post("AddTopicBookManageServlet",t,function(data){
	   var dae =decodeURIComponent(data) ;
      da=eval("("+dae+")");
      $("#table").append("<tr><td><div class=\"tbwidth\">"+da.bookid+"</div></td><td>"+da.booktitle+"</td><td><div class=\"tbwidth\">"+da.author+"</div></td><td><div class=\"tbwidth\">"+da.publish+"</div></td><td><div class=\"tbwidth\">"+da.boknum+"</div></td><td><a href=\"DeleteTopicBookServlet?topicbookid="+da.topicbookid+"\" class=\"layui-btn layui-btn-mini  layui-btn-danger deletel\" style=\"padding:0 12px;\" name=\""+da.topicbookid+"\" ><i class=\"layui-icon \">&#xe640;</i></a></td></tr>");
      arr.push(da.topicbookid);
      arr1.push(da.bookid);
   });
   }
  return false;
 }); 
 $(".deletel").live("click",function(){
        var th =$(this);
	  swal({   title: "确定删除管理员信息吗?",text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
      var args={"topicbookid":th.attr("name"),time:new Date()};
      $.post("DeleteTopicBookServlet",args,function(data){

          th.parent().parent().empty();
        arr.pop(data);
        swal.close(); 
      }); 
      })
      return false;
 }); 
 $(".submit").click(function(){
    var str = "";
    for(var i=0;i<arr.length;i++){
      if(i==arr.length-1)
      {
         str += arr[i];
      }else{
      str += arr[i]+",";
      }
    }

    $(".topicbooks").val(str);
  

 });
</script>
  </body>
</html>
