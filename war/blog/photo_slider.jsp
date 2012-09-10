<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@ page import="gz.aws.blog.PhotoManager" %>
<%@ page import="gz.aws.blog.entity.Photo" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>TinySlider - JavaScript Slideshow</title>
    <link rel="stylesheet" type="text/css" href="/js/tinyslider2/style.css"/>
    <script type="text/javascript" src="/js/tinyslider2/script.js"></script>
</head>
<body>
<%
    PhotoManager photoManager = WebBlogUtil.getPhotoManager();
    List<Photo> photos = photoManager.listAll();
%>
<c:set var="photos" value="<%=photos%>"/>
<div id="wrapper">
    <div id="container">
        <div class="sliderbutton" id="slideleft" onclick="slideshow.move(-1)"></div>
        <div id="slider">
            <ul>
                <c:forEach var="photo" items="${photos}">
                    <li style="text-align: center;"><img src="admin/view_photo.action?pId=${photo.id}" width1="558" height="235" alt="${photo.name}"/></li>
                </c:forEach>
            </ul>
        </div>
        <div class="sliderbutton" id="slideright" onclick="slideshow.move(1)"></div>
        <%--<ul id="pagination" class="pagination">--%>
            <%--<li onclick="slideshow.pos(0)"></li>--%>
            <%--<li onclick="slideshow.pos(1)"></li>--%>
            <%--<li onclick="slideshow.pos(2)"></li>--%>
            <%--<li onclick="slideshow.pos(3)"></li>--%>
        <%--</ul>--%>
    </div>
</div>
<script type="text/javascript">
    var slideshow = new TINY.slider.slide('slideshow', {
        id:'slider',
        auto:3,
        resume:false,
        vertical:false,
//        navid:'pagination',
        activeclass:'current',
        position:0,
        rewind:false,
        elastic:true,
        left:'slideleft',
        right:'slideright'
    });
</script>
</body>
</html>