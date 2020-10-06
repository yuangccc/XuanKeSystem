<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>修改</title>
		<link rel="stylesheet" href="${basePath}static/css/styles.css" />
		<link rel="stylesheet" href="${basePath}static/css/font-awesome-4.7.0/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
		<script src="${basePath}static/js/jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
		<script src="${basePath}static/js/jquery-validation-1.14.0/localization/messages_zh.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$("#editForm").validate({
					rules:{
						sNO:{
							required:true,
							digits:true
						},
						sName:"required",
						sPwd:{
							required:true,
							rangelength:[3,15]
						}
					}
				});
			});
		
		</script>
	</head>
	<body>
		<div class="add">
		<form id="editForm" action="${basePath}student?method=editsubmit" method="post">
			<input type="hidden" name="sID" value="${student.sID}">
			<table class="tableadd">
				<tr>
					<td>姓名</td>
					<td style="color: red;" ><input type="text" name="sName" value="${student.sName}"></td>
				</tr>
				<tr>
					<td>学号</td>
					<td style="color: red;" ><input type="text" name="sNO" value="${student.sNO}"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td style="color: red;" >
					<input type="password" name="sPwd" value="${student.sPwd}" />
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
					<td colspan="4" align="center">
						<button class="reuturn" type="button" onclick="window.history.back(-1)">
							<i class="fa fa-arrow-left"></i>
							返回
						</button>
						<button class="save" type="submit">
							<i class="fa fa-save"></i>
							提交
						</button>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</body>
</html>
