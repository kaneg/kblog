<%@ page import="gz.aws.blog.BlogManager" %>
<%@ page import="gz.aws.blog.CommentManager" %>
<%@ page import="gz.aws.blog.entity.Blog" %>
<%@ page import="gz.aws.blog.entity.Comment" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page import="gz.aws.blog.user.User" %>
<%@ page import="gz.aws.blog.user.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    request.setAttribute("user", user);
    request.setAttribute("userService", userService);
%>
<%
    int pageSize = 5;
    BlogManager bm = WebBlogUtil.getBlogManager();
    List<Blog> blogs;
    PageList<Blog> byPage = null;

    String s = request.getParameter("monthOfYear");
    if (s != null) {
        String[] split = s.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        blogs = bm.getAllByMonthOfYear(year, month);
    } else {
        final String pageIndexStr = request.getParameter("pageIndex");
        int pageIndex = 0;
        try {
            pageIndex = Integer.parseInt(pageIndexStr);
        } catch (Exception e) {
        }
        blogs = byPage = bm.getByPage(pageIndex, pageSize);
        request.setAttribute("need_paging",true);
    }
    request.setAttribute("blogs", blogs);
%>
<script type="text/javascript">
    function send_mail(bId) {
        var callback = function() {
            alert("ok");
        }
        $.get("admin/send_blog_to_mail.action", {bId:bId}, callback);
    }
    function confirmDelete() {
        return  window.confirm("Do you really want to delete it?")
    }
</script>
<c:if test="${empty blogs}">
    No content
</c:if>
<c:forEach items="${blogs}" var="blog">
    <%
        CommentManager cm = WebBlogUtil.getCommentManager();
        Blog blog = (Blog) pageContext.getAttribute("blog");
        List<Comment> list = cm.getAllByBlog(blog.getId());
        pageContext.setAttribute("comments", list);

    %>
    <div class="post type-post hentry">

        <h2 class="entry-title">
            <a href="main.action?pageType=view&bId=${blog.id}" rel="bookmark">${blog.subject}</a>
        </h2>

        <div class="entry-meta">
            <span class="meta-sep">由</span>
            <span class="author vcard">
                    ${blog.owner.nickname}
            </span>
            <span class="meta-prep meta-prep-author">发表于</span>
            <span class="entry-date"><f:formatDate value="${blog.createDate}" pattern="yyyy-MM-dd hh:mm"/></span>
            <span class="meta-prep meta-prep-author">修改于</span>
            <span class="entry-date"><f:formatDate value="${blog.modifyDate}" pattern="yyyy-MM-dd hh:mm"/></span>
            <span><a href="main.action?pageType=view&bId=${blog.id}#comment_anchor">评论(<%=list.size()%>)</a></span>

        </div>
        <!-- .entry-meta -->
        <div class="entry-utility">
            <c:if test="${userService.userLoggedIn&&userService.userAdmin}">
                <span class="comments-link">
                    <a href="admin/delete_blog.action?bId=${blog.id}" onclick="return confirmDelete()">删除</a>
                </span>
                <span class="meta-sep">|</span>
                <span class="comments-link">
                    <a href="admin/update_blog_view.action?bId=${blog.id}">编辑</a>
                </span>
                <span class="meta-sep">|</span>
                <span class="comments-link">
                    <!--<a href="javascript:send_mail(${blog.id})">发送到邮箱</a>-->
                </span>
            </c:if>
        </div>
        <div class="entry-content">
                ${blog.content}
        </div>
        <!-- .entry-content -->

        <div class="entry-utility">
            <c:if test="${userService.userLoggedIn&&userService.userAdmin}">
                <span class="comments-link">
                    <a href="admin/delete_blog.action?bId=${blog.id}" onclick="return confirmDelete()">删除</a>
                </span>
                <span class="meta-sep">|</span>
                <span class="comments-link">
                    <a href="admin/update_blog_view.action?bId=${blog.id}">编辑</a>
                </span>
                <span class="meta-sep">|</span>
                <span class="comments-link">
                    <!--<a href="javascript:send_mail(${blog.id})">发送到邮箱</a>-->
                </span>
            </c:if>
        </div>
        <!-- .entry-utility -->
    </div>
</c:forEach>
<c:if test="${need_paging}">
    <div style="text-align:right;">共：<%=byPage.getTotalCount()%>条, <%=byPage.getTotalPage()%>页,
        当前页:<%=byPage.getPageIndex() + 1%>,
        <%if (byPage.getPageIndex() > 0) {%>
        <a href="main.action?pageIndex=<%=byPage.getPageIndex()-1%>">上一页</a>,
        <%}%>
        <%if (byPage.getPageIndex() < byPage.getTotalPage() - 1) {%>
        <a href="main.action?pageIndex=<%=byPage.getPageIndex()+1%>">下一页</a>
        <%}%>
    </div>
</c:if>
