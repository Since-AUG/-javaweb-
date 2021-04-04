<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>会员注册页面</title>
		<%@include file="/pages/common/head.jsp"%>
		<script type="text/javascript">
			$(function () {
				$("#username").blur(function () {
					var username = $(this).val();
					$.getJSON("${pageScope.basePath}userservlet","action=ajaxExistsUsername&username="+username,
					function (data) {
						if(data.exist){
							$("span.errorMsg").text("用户名已存在!");
						}else{
							$("span.errorMsg").text("该用户名可用");
						}
					});
				});

				$("#code_img").click(function () {
					this.src="kaptcha.jpg?d=" + new Date();
				});

				$("#sub_btn").click(function () {
					// 验证用户名:字母、数字下划线组成,5-12位
					var username = $("#username").val();
					var usernamePatt = /^\w{5,12}/;
					if(!usernamePatt.test(username)){
						$("span.errorMsg").text("用户名不合法!");
						return false;
					}
					//验证密码
					var password = $("#password").val();
					var passwordPatt = /^\w{5,12}/;
					if(!passwordPatt.test(password)){
						$("span.errorMsg").text("密码不合法!");
						return false;
					}
					//验证确认密码和密码是否一样
					var repwd = $("#repwd").val();

					if(repwd != password){
						$("span.errorMsg").text("确认密码和密码不一致！");
						return false;
					}
					var email = $("#email").val();
					var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
					if(!emailPatt.test(email)){
						$("span.errorMsg").text("邮箱不合法！");
						return false;
					}
					var codeText = $("#code").val();
					codeText = $.trim(codeText);
					if(codeText == null || codeText==""){
						$("span.errorMsg").text("验证码不能为空！");
						return false;
					}
					$("span.errorMsg").text("");
				})
			})
		</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userservlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
									       value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 50px; height: 24px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
	</body>
</html>