<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>List Album</title>
</head>
<body>
<p>
    <a href="main.action?pageType=list_album">List Album</a>
    <a href="admin/add_album_view.action">Add Album</a>
</p>
<%
    AlbumManager albumManager = WebBlogUtil.getAlbumManager();
    List<Album> albums = albumManager.listAll();
%>
<c:set var="albums" value="<%=albums%>"/>
<div id="wrapper">
    <div id="container">
        <div id="slider">
            <ul>
                <c:forEach var="album" items="${albums}">
                    <li style="text-align: center;">
                        <p>
                            <span>${album.name}</span>
                            <span>${album.description}</span>
                        </p>
                            <%--<c:forEach var="photo" items="${album.photo}">--%>
                            <%--<img src="admin/view_photo.action?pId=${photo.id}" width1="558" height="235" alt="${photo.name}"/>--%>
                            <%--</c:forEach>--%>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>