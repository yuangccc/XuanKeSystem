<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>查询统计</title>
		<link rel="stylesheet" href="${basePath}static/css/styles.css" />
		<link rel="stylesheet" href="${basePath}static/css/font-awesome-4.7.0/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>	
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
	</br>
	<span style="color: #467FE6; margin-left: 10px; font-weight: bold;">成绩查询</span>
		<table class="tablelist">
			<thead>
				<tr>
					<th style="width: 100px;">课程编号</th>
					<th>科目</th>
					<th>成绩</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="qrt">
				<tr>
					<td>${qrt.cID}</td>
					<td>${qrt.cName}</td>
					<td>${qrt.score}</td>
				</tr>
				</c:forEach>
		</table>
		
	</body>
</html>
