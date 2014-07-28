<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>东华采购平台</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.8.3.min.js"></script>
<link href="css/login/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dhcc/login/fun.base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dhcc/login/script.js"></script>
</head>

<body>
	<div class="login">
		<form method="post" name="mpLogin" id="mpLogin">
			<div class="box png">
				<div class="logo png"></div>
				<div class="input">
					<div class="log">
						<div class="name">
							<label>用户名</label><input type="text" class="text"
								id="mp_userName" placeholder="用户名" name="userName" tabindex="1" value="">
						</div>
						<div class="pwd">
							<label>密 码</label><input type="password" class="text"
								id="mp_password" placeholder="密码" name="password" tabindex="2" value="">
							<input type="button" class="submit" tabindex="3" value="登录"
								onclick="javascript: SendAuthen();">
							<div class="check"></div>
						</div>

						<div class="tip"></div>
					</div>
				</div>
			</div>
		</form>
	</div>


	<div class="air-balloon ab-1 png"></div>
	<div class="air-balloon ab-2 png"></div>
	<div class="footer"></div>
</body>
<script src="<%=request.getContextPath()%>/js/security/aes.js"></script>
<script src="<%=request.getContextPath()%>/js/security/login.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dhcc/pms/login/login.js"></script>
</html>