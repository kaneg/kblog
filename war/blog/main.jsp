<%@ page import="java.util.Calendar" %>
<%@ page import="gz.aws.blog.*" %>
<%@ page import="gz.aws.blog.entity.*" %>
<%@ page import="gz.aws.blog.user.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%
    UserService service = UserServiceFactory.getUserService();
    request.setAttribute("userService", service);
    request.setAttribute("user", service.getCurrentUser());
    String login = service.createLoginURL("/blog/main.action");
    String logout = service.createLogoutURL("/blog/main.action");
%>
<!doctype html>
<html dir="ltr" lang="zh-CN">
<head>
    <title>KBlog</title>
    <link type="text/css" href="/css/le-frog/jquery-ui-1.8.14.custom.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" media="all" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="/css/mystyle.css"/>
    <link rel="alternate" type="application/rss+xml" title="kblog" href="feed.action?feedType=rss_2.0"/>
    <%@include file="_js.jsp" %>
    <script type="text/javascript">
        function showMoodEditor()
        {
            $("#mood_text").hide();
            $("#mood_editor").show();
            $("#mood_editor input").focus();
        }

        function changeMood()
        {
            var mood = $("input[name='mood']").val();
            var callback = function()
            {
                $("#mood_editor").hide();
                $("#mood_text").text("我的心情:" + mood)
                $("#mood_text").show();
            }
            $.post("admin/change_mood.action", {mood:mood}, callback)
        }
    </script>
</head>

<body class="home blog logged-in">
<div id="wrapper" class="hfeed">
    <div id="header">
        <div id="masthead">
            <div>
                <h1 id="site-title">
					<span>
						<a href="/"><div style="line-height:1;font-family:Comic Sans MS;color:pink;font-size:60px;">KBlog</div></a>
					</span>
                </h1>
                <%if(!request.getServerName().equals("localhost")){%>
                <div class="header"><img src="http://bbs.seedit.com/labimg/165/492357.png" border="0" /></div>
                <%}%>
                <%
                    MoodManager mm = WebBlogUtil.getMoodManager();
                    Mood latestMood = mm.getLatestMood();
                    String mood = "";
                    if (latestMood != null) {
                        mood = latestMood.getMood();
                    }
                %>
                <c:set var="mood" value="<%=mood%>"/>
                <div class="mood" style="text-align:right">
                    <c:if test="${not empty mood}">
                        <c:choose>
                            <c:when test="${userService.userLoggedIn&&userService.userAdmin}">
                                <div onclick="showMoodEditor()" id="mood_text">我的心情:${mood}</div>
                                <div id="mood_editor" style="display:none;">
                                    <input type="text" name="mood" value="${mood}" size="100">
                                    <button onclick="changeMood()">保存</button>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div id="mood_text">${mood}</div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                    <c:if test="${empty mood and userService.userLoggedIn&&userService.userAdmin}">
                        <div onclick="showMoodEditor()" id="mood_text" style="display:none">我的心情:${mood}</div>
                        <div id="mood_editor">
                            我的心情:<input type="text" name="mood" value="${mood}" size="100">
                            <button onclick="changeMood()">保存</button>
                        </div>
                    </c:if>

                </div>
                <c:if test="${userService.userLoggedIn}">
                    <div style="text-align:right">(Welcome, ${user.nickname})<a href="<%=logout%>">登出</a></div>
                </c:if>

                <c:if test="${!userService.userLoggedIn}">
                    <div style="text-align:right"><a href="<%=login%>">登录</a></div>
                </c:if>
                <%--<img src="http://localhost/wordpress/wp-content/themes/twentyten/images/headers/path.jpg"--%>
                <%--width="940" height="50" alt=""/>--%>
            </div>
            <!-- #branding -->
            <div id="access">
                <div class="menu">
                    <ul>
                        <li class="${empty param.pageType or param.pageType eq 'list_blog' or param.pageType eq 'view'?'current_page_item':''}">
                            <a href="main.action">日志</a>
                        </li>

                        <c:if test="${userService.userLoggedIn&&userService.userAdmin}">
                            <li class="${param.pageType eq 'list_photo'?'current_page_item':''}"><a
                                    href="main.action?pageType=list_photo">相册</a></li>
                            <li class="${param.pageType eq 'list_mood'?'current_page_item':''}"><a
                                    href="main.action?pageType=list_mood">心情</a></li>
                            <li class="${param.pageType eq 'list_mood'?'current_page_item':''}"><a
                                    href="/bill">账簿</a></li>
                            <li class="${param.pageType eq 'pref'?'current_page_item':''}"><a
                                    href="main.action?pageType=pref">设置</a></li>
                            <li class="${param.pageType eq 'statistics'?'current_page_item':''}"><a
                                    href="main.action?pageType=statistics">统计</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <!-- #access -->
        </div>
        <!-- #masthead -->
    </div>
    <!-- #header -->

    <div id="main">
        <div id="container">
            <div id="content">
                <c:choose>
                    <c:when test="${param.pageType eq 'view'}">
                        <%@include file="view_blog.jsp" %>
                    </c:when>
                    <c:when test="${param.pageType eq 'pref'}">
                        <%@include file="admin/update_preference_view.jsp" %>
                    </c:when>
                    <c:when test="${param.pageType eq 'list_photo'}">
                        <%@include file="list_photo.jsp" %>
                    </c:when>
                    <c:when test="${param.pageType eq 'photo_slider'}">
                        <%@include file="photo_slider.jsp" %>
                    </c:when>
                    <c:when test="${param.pageType eq 'list_album'}">
                        <%@include file="list_album.jsp" %>
                    </c:when>
                    <c:when test="${param.pageType eq 'list_mood'}">
                        <jsp:include page="list_mymood.action" flush="true"/>
                    </c:when>
                    <c:when test="${param.pageType eq 'statistics'}">
                        <jsp:include page="admin/statistics.jsp" flush="true"/>
                    </c:when>
                    <c:otherwise>
                        <%@include file="list_blog.jsp" %>
                    </c:otherwise>
                </c:choose>


            </div>
            <!-- #content -->
        </div>
        <!-- #container -->

        <%@include file="right.jsp" %>

        <!-- #primary .widget-area -->

    </div>
    <!-- #main -->

    <div id="footer">
        <div id="colophon">
            <div>
                <div style="text-align:center;">&copy;Created by Kane 2011</div>
            </div>
        </div>
    </div>
    <!-- #footer -->
</div>
<!-- #wrapper -->
</body>
<%@ include file="../analytics.jsp"%>
</html>
