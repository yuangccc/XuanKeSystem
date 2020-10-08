<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>学生选课系统登录</title>
		
		<style type="text/css">
			*{
				margin: 0px;
				padding: 0px;
				font-family: "microsoft yahei";
			}
			html,body{
				background-image: url(static/img/loginbg.jpg);
				background-size: 100% 100%;
				height: 100%;
			}
			.login{
				position: absolute;
				background-color: rgb(255,255,255,1);
				top: 25%;
				bottom: 25%;
				left: 60%;
				right: 10%;
				border-radius: 5px;
			}
			.title,.user,.password,.s,.loginbutton,.tips{
				position: absolute;
				/* border: red solid 1px; */
				width: 100%;
			}
			input{
				height: 35px;
				background-color: aliceblue;
				border: 0px;
				border-radius: 0.3125rem;
				width: 80%;
			}
			button{
				background-color: #467FE6;
				height: 35px;
				width: 80%;
				border-radius: 5px;
				border: 0px;
				color: #FFFFFF;
			}
			input{
				height: 35px;
				border: 0px;
				border-radius: 0.3125rem;
				width: 80%;
				padding-left: 23px;
				box-sizing: border-box;
			}
			.uname{
				background: url(static/img/User.png)no-repeat left;
				background-size: 20px 20px;
				background-color: aliceblue;
			}
			.pwd{
				background: url(static/img/pwd.png)no-repeat left;
				background-size: 20px 20px;
				background-color: aliceblue;
			}
			.title{
				text-align: center;
				top: 0%;
				bottom: 80%;
				font-size: 25px;
				font-weight: bold;
				padding-top: 10px;
				box-sizing: border-box;
			}
			.user{
				left: 10%;
				top: 20%;
				bottom: 60%;
			}
			.password{
				left: 10%;
				top: 40%;
				bottom: 40%;
			}
			.s{
				top: 57%;
				bottom: 27%;
				left: 10%;
			}
			select{
				width: 80%;
				height: 35px;
				border-radius: 4px;
				border: 1px solid #e1e1e1e;
			}
			.loginbutton{
				top: 75%;
				bottom: 15%;
				position: absolute;
				left: 10%;
			}
			.tips{
				top: 90%;
				text-align: center;
				font-size: 14px;
				color: red;
			}
		</style>
		
	</head>
	<body>
		<div class="login">
			<div class="title">
				学生选课系统
			</div>
			<form action="login" method="post">
				<div class="user">
					<input type="text" class="uname" name="userName" />
				</div>
				<div class="password">
					<input type="password" class="pwd" name="password" />
				</div>
				<div class="s">
					<select name="type">
						<option value="">请选择登录类型</option>
						<option value="0">学生</option>
						<option value="1">教师</option>
						<option value="2">管理员</option>
					</select>			
				</div>
				<div class="loginbutton">
					<button type="submit">登录</button>
				</div>
			</form>
			<div class="tips">		
					${error}
			</div>
			
		</div>
	</body>
</html>
