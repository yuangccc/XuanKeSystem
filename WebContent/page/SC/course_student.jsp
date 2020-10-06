<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>评分</title>
		<link rel="stylesheet" href="${basePath}static/css/styles.css" />
		<link rel="stylesheet" href="${basePath}static/css/font-awesome-4.7.0/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>	
		<script src="http://localhost:8080/xuan_ke/static/js/jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
		<script src="http://localhost:8080/xuan_ke/static/js/jquery-validation-1.14.0/localization/messages_zh.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$("#addForm").validate({
					rules:{
						score:{
							required:true,
							digits:true,
							max:100,
							min:0
						}
					}
				});
			});
		
		</script>
	</head>
	<body>
		<c:if test="${param.msg == 0}">
		 <span style="color:green;">操作成功!</span>
		</c:if>	
		<c:if test="${param.msg == 1}">
		 <span style="color:red;">操作失败!</span>
		</c:if>
		<form id="addForm" method="post" action="${basePath}sc?method=score">
		<input type="hidden" name="cID" value="${cID}">
			<table class="tablelist">
				<thead>
					<tr>
						<th>ID</th>
						<th>学生姓名</th>
						<th>录入成绩</th>
					</tr>
				</thead>
				<c:forEach items="${list}" var="student">
					<tr>
						<td>${student.sID}</td>
						<td>${student.sName}</td>
						<td style="color: red;">
							<input type="text" name="score" value="${student.score}">
						</td>
							<input type="hidden" name="sID" value="${student.sID}">
					</tr>
				</c:forEach>
			</table>
			</br>
			<button class="mybtn" type="submit">
				<i class="fa fa-save"></i>
				提交评分
			</button>
		</form>
	</body>
</html>
