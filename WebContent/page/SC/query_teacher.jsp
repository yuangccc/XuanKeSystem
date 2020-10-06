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
					<th>课程编号</th>
					<th>科目</th>
					<th>[0-60)</th>
					<th>(60-70]</th>
					<th>(70-80]</th>
					<th>(80-90]</th>
					<th>(90-100]</th>
					<th>详细分数</th>
				</tr>
			</thead>
			<c:forEach items="${list1}" var="qrt">
				<tr>
					<td>${qrt.cID}</td>
					<td>${qrt.cName}</td>
					<td>${qrt.A}</td>
					<td>${qrt.B}</td>
					<td>${qrt.C}</td>
					<td>${qrt.D}</td>
					<td>${qrt.E}</td>
					<td>
						<button class="edit" onclick="window.location.href='scquery?method=listScoreBycID&cID=${qrt.cID}'">
								<i class="fa fa-edit"></i>
								查看
						</button>
					</td>
				</tr>
				</c:forEach>
		</table>
		</br>
		<span style="color: #467FE6; margin-left: 10px; font-weight: bold;">及格率查询</span>
		<table class="tablelist">
			<thead>
				<tr>
					<th>课程编号</th>
					<th>科目</th>
					<th>及格人数</th>
					<th>总人数</th>
					<th>及格率</th>
				</tr>
			</thead>
			<c:forEach items="${list2}" var="qr">
				<tr>
					<td>${qr.cID}</td>
					<td>${qr.cName}</td>
					<td>${qr.passed}</td>
					<td>${qr.totals}</td>
					<td>${qr.pass_rate}%</td>
				</tr>
				</c:forEach>
		</table>
	</body>
</html>
