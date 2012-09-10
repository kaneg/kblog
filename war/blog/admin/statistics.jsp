<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<html>
<%
    Calendar monthStart = Calendar.getInstance();
    int year = monthStart.get(Calendar.YEAR);
    int month = monthStart.get(Calendar.MONTH);
    pageContext.setAttribute("_year", year);
    pageContext.setAttribute("_month", month);
%>

<head>
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', '月份');
            data.addColumn('number', '博客篇数');
            data.addRows(12);
        <c:forEach begin="1" end="12" varStatus="vs">
        <c:set var="year" value="${_month-vs.index+2>0?_year:(_year-1)}"/>
        <c:set var="month" value="${(12+_month-vs.index+1) mod 12}"/>
        <%
            int blogCount = WebBlogUtil.getBlogManager().getBlogCountByMonthOfYear(
                    ((Number) pageContext.getAttribute("year")).intValue(),
                    ((Number) pageContext.getAttribute("month")).intValue());
            request.setAttribute("blogCount", blogCount);
        %>
            data.setValue(${12-vs.index}, 0, '${year}年${month+1}月');
            data.setValue(${12-vs.index}, 1, ${blogCount});
        </c:forEach>
            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
            chart.draw(data, {width: 600, height: 360, title: '每月发帖数量统计'});
        }
    </script>
</head>

<body>
<div id="chart_div"></div>
</body>
</html>