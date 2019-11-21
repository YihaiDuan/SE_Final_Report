<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'library.jsp' starting page</title>
    
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
  	#prompt,#smallclass{
    	   display:none; 
    	  position:fixed;
    	  top:90px;
    	  left:50%;
    	  margin-left:-200px;	
          width:290px;
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
  </style>
  </head>
  
  <body>
    <div id="content">
     <%
                    String bookid=request.getParameter("Bookid");
                    String pc=null;
                    if(request.getParameter("pc")!=null)
                    {
                    pc=request.getParameter("pc");
                    }
                     String booktypeid ="0";
                    String categoryid ="0";
                    String type = "borrow";
                    String mode = "bigsmall";
                    String searchtype="书名";
                    String content="";
                    if(request.getParameter("searchtype")!=null)
                    {
                       searchtype=request.getParameter("searchtype");
                    
                    }
                    if(request.getParameter("content")!=null){
                    
                        content = request.getParameter("content");
                    
                    }
                    if(request.getParameter("booktypeid")!=null)
                     {
                     booktypeid=request.getParameter("booktypeid");
                     }
                     if(request.getParameter("categoryid")!=null)
                     {
                      categoryid=request.getParameter("categoryid");
                     }
                     if(request.getParameter("type")!=null)
                     {
                     type=request.getParameter("type");
                     
                     }
                     if(request.getParameter("mode")!=null)
                     {
                     mode=request.getParameter("mode"); 
                     }
                 
                  //  BookDao bookdao=new BookDao();
                  //  Book book=bookdao.getIdBook(bookid);
                  BookSonDao booksondao = new BookSonDao();
                  List<BookSon> list=booksondao.getListBook(bookid);
     %>
		<div id="content-header1">
			<h1 style="font-weight:bold;">图书详情</h1>
		</div>
		<div style="padding:0 20px;">
		 <div class="row">
		 <%
		    if(request.getParameter("booktypeid")!=null)
		    {
		  %>
               <a href="modifybook.jsp?pc=${pb.pc}&Bookid=<%=bookid%>&booktypeid=<%=booktypeid %>&categoryid=<%=categoryid%>&type=<%=type %>&mode=<%=mode%>"  class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
                <a href="#" class="layui-btn layui-btn-radius add" style="letter-spacing:2px;height:32px;line-height:32px;">添加书籍</a> 
         <%
           }else{
           if(request.getParameter("action")==null)
           {
          %>  
            <a href="modifybook.jsp?pc=${pb.pc}&Bookid=<%=bookid%>&searchtype=<%=searchtype %>&content=<%=content %>"  class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
            <a href="#" class="layui-btn layui-btn-radius add" style="letter-spacing:2px;height:32px;line-height:32px;">添加书籍</a> 
          <%
          }else{
          %>
          
           <a href="detailBook.jsp?Bookid=<%=bookid%>&action=isbn"  class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
            <a href="#" class="layui-btn layui-btn-radius add" style="letter-spacing:2px;height:32px;line-height:32px;">添加书籍</a> 
          <%
          }
          }
           %>  
           </div> 
			<table class="layui-table">
				<colgroup>
					<col width="140">
					<col width="140">
					<col width="120">
					<col width="120">
					
				</colgroup>
			    <thead>
                                     <tr>
                                     <th> 图书编号</th>
                                     <th>书名 </th>
                                     <th> 作者</th>
                                     <th> 出版社</th>
                                     <th>版本号</th>
                                     <th>BookId</th>
                                     <th>操作</th>
                                     </tr>
                                    </thead>
                                    <tbody  id="table">
                                    <%
                                    if(list!=null&&list.size()>=0)
                                    {
                                           for(int i=0;i<=list.size()-1;i++)
                                   {
  
                                       BookSon bookson=list.get(i);
                                    
                                     %>
                                    <tr>
                                        <td><div style="width:140px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=bookson.getBook().getBookid()%></div></td>
                                        <td><div style="width:140px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=bookson.getBook().getBooktitle() %></div></td>
                                        <td><div style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=bookson.getBook().getAuthor() %></div></td>
                                        <td><div style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=bookson.getBook().getPublish() %></div></td>
                                        <td><%=bookson.getBook().getPublishnumber() %></td>
                                        <td><%=bookson.getBooksonid() %></td>
                                        <td><a class="example4 layui-btn layui-btn-mini  layui-btn-danger delete" name="<%=bookson.getBooksonid() %>"><i class="layui-icon ">&#xe640;</i></a></td>
                                     </tr>
                                    <%
                                       }
                                      }
                           
                                    %>
                                    </tbody>
			</table>
		</div>
		
		<div id="prompt">
			<div class="addtop">添加书籍</div>
			<img src="images/chahao.png" id="chahao" />
			
			    <div class="one">
					<label name="booktypeid" style="margin-right:6px;display:inline-block;">图书编号: </label><!-- <input
						type="text" name="booktypeid" style="width:180px;height:26px;line-height:26px;"/> -->
						<span style="width:180px;height:26px;line-height:26px;line-height:26px;display:inline-block;"><%=bookid %></span>
				</div>
				<div class="one">
					<label name="category" style="margin-right:6px;display:inline-block;">书籍编号: </label><input
						type="text" name="name" style="width:180px;height:26px;line-height:26px;" id="bookson"/>
				</div>
				<div class="one" style="margin-top:20px;">
				<input type="button" class="addsubmit" value="点击添加">
				</div>
		</div>
    <script src="js/excanvas.min.js"></script> 
    <script src="js/jquery.min.js"></script> 
    <script src="js/jquery.ui.custom.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script>
    <script src="dist/sweetalert.min.js"></script>
        <script src="dist/sweetalert-dev.js"></script> 
    <script type="text/javascript">
      $("#chahao").click(function(){
			$("#prompt").hide();
	  })
		 $(".add").click(function(){
			$("#prompt").show();
			return false;
		})
             $(".delete").live('click',function(){
             var name=$(this).attr("name");
            var args={"booksonid":name,"time": new Date()};
              $.post("deleteBookSonServlet",args,function(data){    
                  if(data==1)
                  {    
                      
                       window.location.reload(true);
                  

                    	
                    
                      
                  
                 };
         
             }) 
              return false;
       
       })
          

       
       $(".addsubmit").click(function(){
       var booksonid = $("#bookson").val();
       var args={"booksonid":booksonid,"bookid":<%=bookid%>,time:new Date()};
       $.post("ModifyBookSonServlet",args,function(data){
             news=eval("("+data+")");
             $("#prompt").hide();
             $("#bookson").empty();
          $("#table").append("<tr><td><div style=\"width:140px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;\">"+news.bookid+"</div></td> <td><div style=\"width:140px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;\">"+news.booktitle+"</div></td> <td><div style=\"width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;\">"+news.author+"</div></td><td><div style=\"width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;\">"+news.publish+"</div></td><td>"+news.publishnumber+"</td><td>"+news.booksonid+"</td><td><a class=\"example4 layui-btn layui-btn-mini  layui-btn-danger delete\" name=\""+news.booksonid+"\"><i class=\"layui-icon\" >&#xe640;</i></a></td>");   
            
       });
       
       });

     </script>   
  </body>
</html>
