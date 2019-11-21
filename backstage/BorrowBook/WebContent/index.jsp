<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 

Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="css/matrix-style.css" />
    <link rel="stylesheet" href="css/matrix-media.css" />
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    
  </head>
  
  <body>
    <!--Header-part-->
    <div id="header"  >
      <h1 style="margin-left:640px;width:400px"><a href="dashboard.html" style="color:#fff" >个性图书推荐后台管理系统</a></h1>
    </div>
    <!--close-Header-part--> 

    <!--top-Header-menu-->
    <div id="user-nav" class="navbar navbar-inverse">
        <ul class="nav">
            <li  class="dropdown"  >
                <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                    <i class="icon icon-user"></i>&nbsp;
                    <span class="text">欢迎你，<%=request.getSession().getAttribute("admin") %></span>&nbsp;
                </a>
               
            </li>
            <li class=""><a title="" href="exitloginServlet"><i class="icon icon-share-alt"></i> <span class="text">&nbsp;退出系统</span></a></li>
        </ul>
    </div>
    <!--close-top-Header-menu-->


    <!--sidebar-menu-->
    <div id="sidebar" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
        <ul>
            <li class="submenu"> 
                <a href="#">
                    <i class="icon icon-table"></i> 
                    <span>图书信息</span> 
                    <span class="label label-important">3</span>
                </a>
                <ul>
                <!--form-validation.html  -->
                   <!--<li><a class="menu_a" link="datastatistics.jsp"><i class="icon icon-caret-right"></i>数据统计</a></li>-->
                    <li><a class="menu_a" link="BookPageServlet"><i class="icon icon-caret-right"></i>图书管理</a></li>
                    <li><a class="menu_a" link="addBook.jsp"><i class="icon icon-caret-right"></i>添加图书</a></li>
                    <li><a class="menu_a" link="batchaddbook.jsp"><i class="icon icon-caret-right"></i>批量上传</a></li>
                </ul>
            </li>
            <li class="submenu">
				<a class="menu_a" link="AdminPageServlet">
                    <i class="icon icon-th"></i> 
                    <span>管理员信息</span> 
                    <span class="label label-important">1</span>
                </a>
            </li>
            <li class="submenu"> 
                <a href="#">
                    <i class="icon icon-stop"></i> 
                    <span>用户信息</span> 
                    <span class="label label-important">2</span>
                </a>
                <ul>
                      <li><a class="menu_a" link="UserPageServlet"><i class="icon icon-caret-right"></i>用户管理</a></li>
                    <li><a class="menu_a" link="gallery.jsp"><i class="icon icon-caret-right"></i>数据统计</a></li>
                  
                </ul>
            </li>
        
        </ul>
    </div>
    <!--sidebar-menu-->

    <!--main-container-part-->
    <div id="content">
        <!--breadcrumbs-->
        <div id="content-header">
          <div id="breadcrumb" > 
          </div>
        </div>
        <!--End-breadcrumbs-->
        <iframe  id="iframe-main" frameborder='0' style="width:100%;" src="welcome.html"></iframe>
    </div>
    <!--end-main-container-part-->

    <script src="js/excanvas.min.js"></script> 
    <script src="js/jquery.min.js"></script> 
    <script src="js/jquery.ui.custom.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script> 


    <script type="text/javascript">

    //初始化相关元素高度
    function init(){
        $("body").height($(window).height()-80);
        $("#iframe-main").height($(window).height()-90);
        $("#sidebar").height($(window).height()-50);
    }

    $(function(){
        init();
        $(window).resize(function(){
            init();
        });
    });

    // This function is called from the pop-up menus to transfer to
    // a different page. Ignore if the value returned is a null string:
    function goPage (newURL) {
        // if url is empty, skip the menu dividers and reset the menu selection to default
        if (newURL != "") {
            // if url is "-", it is this page -- reset the menu:
            if (newURL == "-" ) {
                resetMenu();            
            } 
            // else, send page to designated URL            
            else {  
                document.location.href = newURL;
            }
        }
    }

    // resets the menu selection upon entry to this page:
    function resetMenu() {
        document.gomenu.selector.selectedIndex = 2;
    }

    // uniform使用示例：
    // $.uniform.update($(this).attr("checked", true));
    </script>
  </body>
</html>
