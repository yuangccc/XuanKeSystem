<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>选课</title>
		<link rel="stylesheet" href="${basePath}static/css/styles.css" />
		<link rel="stylesheet" href="${basePath}static/css/font-awesome-4.7.0/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>	
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<form action="${basePath}sc?method=list"  id="tableList" method="post">
		<table class="tablelist">
			<thead>
				<tr>
					<th>ID</th>
					<th>科目</th>
					<th>老师</th>
					<th width="120px">操作</th>
				</tr>
			</thead>
			<c:forEach items="${pageInfo.list}" var="course">
				<tr>
					<td>${course.cID}</td>
					<td>${course.cName}</td>
					<td>${course.teacher.tName}</td>
					<td>
						<button class="edit" type="button" onclick="window.location.href='sc?method=cs&cID=${course.cID}'">
							<i class="fa fa-edit"></i>
							评分
						</button>
					</td>
				</tr>
				</c:forEach>
		</table>
		<%@include file="../inc/page.jsp" %>
		</form>
	
	</body>
</html>