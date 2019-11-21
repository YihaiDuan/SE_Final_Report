<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>欢迎管理员登录</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />

<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
</head>
<body onkeydown="keydown()">
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>欢迎你</h3>
						<form name="f" action="loginServlet?action=login" method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="username" class="text" style=" important" type="text" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password" class="text" style=" important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
							</div>
							<div class="mb2">
							<input type="submit" class="act-but submit" onclick="checkLogin()" value=" 登录" style="color: #FFFFFF; width: 330px;"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
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