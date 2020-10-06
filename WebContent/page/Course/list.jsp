<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>列表</title>
		<link rel="stylesheet" href="${basePath}static/css/styles.css" />
		<link rel="stylesheet" href="${basePath}static/css/font-awesome-4.7.0/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$('.remove').click(function(){
					if(confirm("确定要删除吗？")){
						window.location.href="${basePath}course?method=delete&id="+$(this).attr("key");
					}
				})
			})		
		</script>
		
	</head>
	<body>
	<form action="${basePath}course?method=list" method="post">
			<div class="condition">
				课程编号:<input type="text"  name="cID" value="${course.cID}"/>
				课程名:<input type="text"  name="cName" value="${course.cName}"/>
				老师编号:<input type="text"  name="tID" value="${course.teacher.tID}"/>
				老师姓名:<input type="text"  name="tName" value="${course.teacher.tName}"/>
				<button>
					<i class="fa fa-search"></i>
					查询
				</button>
				<button  type="button" onclick="window.location.href='course?method=v_add'">
					<i class="fa fa-plus"></i>
					新增
				</button>
			</div>
		</form>
		<form action="${basePath}course?method=list"  id="tableList" method="post">
		<input type="hidden" name="pageNo" value="${pageInfo.pageNo}">
		<input type="hidden" name="cID" value="${course.cID}">
		<input type="hidden" name="cName" value="${course.cName}">
		<input type="hidden" name="tNO" value="${course.teacher.tNO}">
		<input type="hidden" name="tID" value="${course.teacher.tID}">
		<input type="hidden" name="tName" value="${course.teacher.tName}">
		<table class="tablelist">
			<thead>
				<tr>
					<th>课程编号</th>
					<th>课程名</th>
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
						<button class="edit" type="button" onclick="window.location.href='course?method=edit&id=${course.cID}'">
							<i class="fa fa-edit"></i>
							修改
						</button>
						<button class="remove" type="button" key="${course.cID}" >
							<i class="fa fa-remove"></i>
							删除
						</button>
					</td>
				</tr>
				</c:forEach>
		</table>
		<%@include file="../inc/page.jsp" %>
		</form>
	</body>
</html>
