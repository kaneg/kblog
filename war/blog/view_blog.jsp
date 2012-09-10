<%@ page import="gz.aws.blog.BlogManager" %>
<%@ page import="gz.aws.blog.CommentManager" %>
<%@ page import="gz.aws.blog.entity.Blog" %>
<%@ page import="gz.aws.blog.entity.Comment" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    request.setAttribute("user", user);
    request.setAttribute("userService", userService);
    Long blogId = Long.parseLong(request.getParameter("bId"));

    BlogManager bm = WebBlogUtil.getBlogManager();
    Blog blog = bm.get(blogId);
    request.setAttribute("blog", blog);
    bm.close();
%>
<%
    CommentManager cm = WebBlogUtil.getCommentManager();
    List<Comment> list = cm.getAllByBlog(blogId);
    pageContext.setAttribute("comments", list);

%>
<script type="text/javascript">
    function postComment(f1) {
        f1.ajaxForm({
            success:function() {
                f1.hide()
            }
        })
        return false;
    }
    function confirmDeleteBlog() {
        return  window.confirm("Do you really want to delete it?")
    }
    function confirmDeleteComment() {
        return  window.confirm("Do you really want to delete it?")
    }
    function send_mail(bId) {
        var callback = function() {
            alert("ok");
        }
        $.get("admin/send_blog_to_mail.action", {bId:bId}, callback);
    }
    function checkComment() {
        if ($('#comment').val().trim() != '') {
            return true
        } else {
            alert("No comment input.")
            $('#comment').focus();
            return false;
        }
    }
</script>
<div id="nav-above" class="navigation">
    <div class="nav-previous"></div>
    <div class="nav-next"></div>
</div>
<!-- #nav-above -->

<div id="post-1" class="post-1 post type-post hentry category-1">
    <h1 class="entry-title">${blog.subject}</h1>

    <div class="entry-meta">
        <span class="meta-sep">由</span>
            <span class="author vcard">
                ${blog.owner.nickname}
            </span>
        <span class="meta-prep meta-prep-author">发表于</span>
        <span class="entry-date"><f:formatDate value="${blog.createDate}" pattern="yyyy-MM-dd hh:mm"/></span>
        <span class="meta-prep meta-prep-author">修改于</span>
        <span class="entry-date"><f:formatDate value="${blog.modifyDate}" pattern="yyyy-MM-dd hh:mm"/></span>
        <span>评论(<%=list.size()%>)</span>
    </div>
    <div class="entry-utility">
        <c:if test="${userService.userLoggedIn&&userService.userAdmin}">
            <span class="comments-link">
                <a href="admin/delete_blog.action?bId=${blog.id}" onclick="return confirmDeleteBlog()">删除</a>
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
    <!-- .entry-meta -->
    <div class="entry-content">${blog.content}</div>
    <!-- .entry-content -->
    <div class="entry-utility">
        <c:if test="${userService.userLoggedIn&&userService.userAdmin}">
            <span class="comments-link">
                <a href="admin/delete_blog.action?bId=${blog.id}" onclick="return confirmDeleteBlog()">删除</a>
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
<!-- #post-## -->
<div id="nav-below" class="navigation">
    <div class="nav-previous"></div>
    <div class="nav-next"></div>
</div>
<!-- #nav-below -->
<a name="comment_anchor"></a>
<div id="comments"><h3 id="comments-title">有 <s:property value="#attr.comments.size()"/> 条评论:
</h3>
    <ol class="commentlist">
        <c:forEach var="comment" items="${comments}">
            <li class="comment even thread-even depth-1" id="li-comment-1">
                <div id="comment-1">
                    <div class="comment-author vcard">
                        <cite class="fn">${empty comment.owner?"匿名":comment.owner.nickname}</cite> <span
                            class="says">说：</span>
                    </div>
                    <!-- .comment-author .vcard -->
                    <div class="comment-meta commentmetadata">
                        <a href="#comment-1">
                            <f:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></a>
                        <c:if test="${userService.userLoggedIn&&userService.userAdmin}">
                            <a class="comment-edit-link"
                               href="admin/delete_comment.action?cId=${comment.id}&bId=${blog.id}"
                               onclick="return confirmDeleteComment();">删除</a>
                        </c:if>
                    </div>
                    <div class="comment-body">${comment.content}</div>
                </div>
                <!-- #comment-##  -->
            </li>
        </c:forEach>
    </ol>
    <div id="respond">
        <h3 id="reply-title">发表评论
        </h3>

        <form action="add_comment.action" method="post" id="commentform" onsubmit="return checkComment();">
            <p class="logged-in-as">身份：${empty user?'访客':user.nickname} </p>

            <p class="comment-form-comment">
                <label for="comment">评论</label>
                <textarea id="comment" name="content" cols="40" rows="4"></textarea>
            </p>

            <p class="form-submit">
                <input type="hidden" name="bId" value="${blog.id}">
                <input name="submit" type="submit" id="submit" value="发表评论"/>
            </p>
        </form>
    </div>
    <!-- #respond -->
</div>
<!-- #comments -->