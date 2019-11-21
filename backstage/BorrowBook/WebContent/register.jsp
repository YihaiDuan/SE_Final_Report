<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="styles.css">
	
    <link rel="stylesheet" href="dist/sweetalert.css">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="css/matrix-login.css" />
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
	
    <style type="text/css">
        input{
            font-family: "Microsoft Yahei";
        }
        .control-label{
            color: black;
            padding-left: 4px;
        }
    </style>
  </head>
  
 <body onkeydown="keydown()" >
    <div id="loginbox" style="margin-left: 700px;"> 
        <div class="control-group normal_text" > 
            <h2 style="color:#007bff;font-size:28px;text-shadow:1px 1px 2px #FBFAB7;">后台管理系统</h2>
        </div>      
        
            
        <form id="loginform" class="form-vertical" action="loginServlet?action=login" method="post">
            <div class="control-group">
                <label class="control-label" style="color: ">登陆账号</label>
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_lg"><i class="icon-user" style="font-size:16px;"></i></span>
                        <input type="text" value="" name="username" />
                    </div>
                </div>
            </div>
            <div class="control-group2">
                <label class="control-label" style="color:black">登陆密码</label>
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_ly"><i class="icon-lock" style="font-size:16px;"></i></span>
                        <input type="password" name="password" />
                    </div>
                </div>
            </div>
            <div class="form-actions" style="align:center;">
                <span style="align:center;margin-left:58px;display:block;"><input type="submit" id="checkBtn" onclick="checkLogin()" class="btn btn-success" style="width:335px;" value=" 登&nbsp;&nbsp;&nbsp;&nbsp;录"/></span>
            </div>
        </form>
        

        <form id="recoverform" action="#" class="form-vertical" style="padding-top:10px;">
            <label class="control-label">登陆账号</label>
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg"><i class="icon-user" style="font-size:16px;"></i></span><input type="text" name="re_username" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">新密码</label>
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_ly"><i class="icon-lock" style="font-size:16px;"></i></span><input type="password" name="re_password"/>
                    </div>
                </div>
            </div>
            <div class="control-group" style="padding-top:0px;">
                <label class="control-label">确认新密码</label>
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_ly"><i class="icon-lock" style="font-size:16px;"></i></span><input type="password" name="re_confirmpassword" />
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回登录</a></span>
                <span class="pull-right"><a id="changePwd" class="btn btn-info" style="width:310px;">重置密码</a></span>
            </div>
            <div class="control-group normal_text">
                <div style="font-size:14px;color:gray;">推荐使用webkit内核浏览器，如chrome等</div>
            </div>
        </form>

    </div>
    
    <!--  <script src="js/jquery.min.js"></script>  
    <script src="js/matrix.login.js"></script> 
        <script src="dist/sweetalert.min.js"></script>
    <script src="dist/sweetalert-dev.js"></script>
    <script type="text/javascript">-->
    <script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>
		<div style="text-align:center;">
</div>
<script type="text/javascript">
  
          $(function(){
        	  var act="<%=request.getParameter("action")%>"; 
        	  if(act=="success")
        		  {
        		  swal("登陆失败!", "请重新登录!", "error");
        		  }
   	  
          });
    </script>
</body>
</html>
