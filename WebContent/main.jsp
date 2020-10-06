<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
       <script type="text/javascript" src="${basePath}static/echarts/echarts.min.js"></script>
   </head>
<body style="height: 100%; margin: 0">
	<div id="container1" style="height: 100%; width: 48%; float: left;border: 1px solid #e1e1e1;margin-top: 10px;">
	</div>
	<div id="container2"  style="height: 100%; width: 48%; float: right;border: 1px solid #e1e1e1;margin-top: 10px;">
	</div>

	<script type="text/javascript">
var dom = document.getElementById("container1");
var myChart = echarts.init(dom);
option1 = {
	title: {
        text: '及格率柱状图',
        left: "center",
        top:"2%"
    },
	tooltip: {
        trigger: 'axis'
    },
    xAxis: {
        type: 'category',
        axisLabel: {
               interval:0
           },
        data: [
        	<c:forEach items="${list1}" var="temp">
       		'${temp.cName}',
       		</c:forEach>
        ]
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [
        	<c:forEach items="${list1}" var="temp">
       		${temp.pass_rate},
       		</c:forEach>
        ],
        type: 'bar',
        showBackground: true,
        backgroundStyle: {
            color: 'rgba(220, 220, 220, 0.8)'
        }
    }]
};
myChart.setOption(option1, true);


var dom2 = document.getElementById("container2");
var myChart2 = echarts.init(dom2);
option2 = {	
	title: {
    	text: '成绩区间统计图',
    	fontSize: 10,
        fontWeight: "lighter",
        left: "center"
	},
    legend: {
        top: "5%" ,
    },
    tooltip: {},
    dataset: {
        source: [
        	['科目', '[0-60)', '(60-70]', '(70-80]', '(80-90]', '(90-100]'],
			<c:forEach items="${list2}" var="qr">
			
				['${qr.cName}', ${qr.A}, ${qr.B}, ${qr.C}, ${qr.D}, ${qr.E}],
			
			</c:forEach>
        ]
    },
    xAxis: {type: 'category'},
    yAxis: {},
    // Declare several bar series, each will be mapped
    // to a column of dataset.source by default.
    series: [
        {type: 'bar'},
        {type: 'bar'},
        {type: 'bar'},
        {type: 'bar'},
        {type: 'bar'}
    ]
};
;
if (option2 && typeof option2 === "object") {
    myChart2.setOption(option2, true);
}

</script>
</body>
</html>