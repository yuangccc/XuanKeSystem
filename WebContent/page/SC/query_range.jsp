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
		<table class="tablelist">
			<thead>
				<tr>
					<th>课程编号</th>
					<th>科目</th>
					<th>[0-60)</th>
					<th>(60-70]</th>
					<th>(70-80]</th>
					<th>(80-90]</th>
					<th>(90-100]</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="qr">
				<tr>
					<td>${qr.cID}</td>
					<td>${qr.cName}</td>
					<td>${qr.A}</td>
					<td>${qr.B}</td>
					<td>${qr.C}</td>
					<td>${qr.D}</td>
					<td>${qr.E}</td>
				</tr>
				</c:forEach>
		</table>
	</body>
</html>
