<%@ page import="gz.aws.blog.PreferenceManager" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<%
    PreferenceManager pm = WebBlogUtil.getPrefManager();
    String myPicFId = pm.getPref("myPicture");
    String myMusicFId = pm.getPref("myMusic");
    pm.close();
    pageContext.setAttribute("my_pic_fId", myPicFId);
    pageContext.setAttribute("my_music_fId", myMusicFId);
%>
<style type="text/css">
    .logo {
        width: 150px;
    }
</style>
<div id="primary" class="widget-area">
    <ul class="xoxo">

        <li id="search-2" class="widget-container widget_search">
            <div>
                <c:if test="${userService.userLoggedIn&&userService.userAdmin}">
                    <button onclick="javascript:document.location.href='admin/add_blog_view.action'">发表新文章</button>
                </c:if>
            </div>
        </li>
        <li class="widget-container widget_search">
            <c:if test="${not empty my_pic_fId}">
                <div class="logo"><img src="/servlet/file_download?fId=${my_pic_fId}" alt="" width="150"></div>
            </c:if>
        </li>
        <%
            Calendar monthStart = Calendar.getInstance();
            int year = monthStart.get(Calendar.YEAR);
            int month = monthStart.get(Calendar.MONTH);
            pageContext.setAttribute("_year", year);
            pageContext.setAttribute("_month", month);
        %>
        <li id="archives-2" class="widget-container widget_archive"><h3 class="widget-title">文章归档</h3>
            <ul>
                <c:forEach begin="1" end="12" varStatus="vs">
                    <li>
                        <c:set var="year" value="${_month-vs.index+2>0?_year:(_year-1)}"/>
                        <c:set var="month" value="${(12+_month-vs.index+1) mod 12}"/>
                        <%
                            int blogCount = WebBlogUtil.getBlogManager().getBlogCountByMonthOfYear(
                                    ((Number) pageContext.getAttribute("year")).intValue(),
                                    ((Number) pageContext.getAttribute("month")).intValue());
                            request.setAttribute("blogCount", blogCount);
                        %>
                        <a href="main.action?monthOfYear=${year}-${month}">${year}年${month+1}月 (${blogCount})</a>
                    </li>
                </c:forEach>
            </ul>
        </li>

        <li id="meta-2" class="widget-container widget_meta">
                <li><a href="feed.action?feedType=rss_2.0" title="使用 RSS 2.0 同步站点内容">
                    <img src="/img/rss_logo.gif" alt="RSS"/>
                </a></li>
        </li>
    </ul>
</div>