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
						window.location.href="${basePath}student?method=delete&id="+$(this).attr("key");
					}
				})
			})		
		</script>
		
	</head>
	<body>
	<form action="${basePath}student?method=list" method="post">
			<div class="condition">
				ID:<input type="text"  name="sID" value="${student.sID}"/>
				姓名:<input type="text"  name="sName" value="${student.sName}"/>
				学号:<input type="text"  name="sNO" value="${student.sNO}"/>
				<button>
					<i class="fa fa-search"></i>
					查询
				</button>
				<button  type="button" onclick="window.location.href='page/Student/add.jsp'">
					<i class="fa fa-plus"></i>
					新增
				</button>
			</div>
		</form>
		<form action="${basePath}student?method=list"  id="tableList" method="post">
		<input type="hidden" name="pageNo" value="${pageInfo.pageNo}">
		<input type="hidden" name="sID" value="${student.sID}">
		<input type="hidden" name="sName" value="${student.sName}">
		<input type="hidden" name="sNO" value="${student.sNO}">
		<table class="tablelist">
			<tdead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>学号</th>
					<th width="120px">操作</th>
				</tr>
			</tdead>
			<c:forEach items="${pageInfo.list}" var="student">
				<tr>
					<td>${student.sID}</td>
					<td>${student.sName}</td>
					<td>${student.sNO}</td>
					<td>
						<button class="edit" type="button" onclick="window.location.href='student?method=edit&id=${student.sID}'">
							<i class="fa fa-edit"></i>
							修改
						</button>
						<button class="remove" type="button" key="${student.sID}" >
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
