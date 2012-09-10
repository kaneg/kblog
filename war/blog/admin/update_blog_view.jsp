<%@ page import="gz.aws.blog.BlogManager" %>
<%@ page import="gz.aws.blog.entity.Blog" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Update</title></head>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/admin_login_required.jsp" %>
<%@include file="../_js.jsp"%>
<script type="text/javascript">
  /*KE.show({
    id : 'blog_content',
    width : '720px',
    height : '400px'
  });*/
 KindEditor.ready(function(K) {
	K.create('#blog_content', {
        width : '720px',
        height : '400px'
	});
});
</script>
<body style="background-color:pink;margin-right:auto;margin-left:auto;width:800px;">
<br>
<br>
<h3 style="text-align:center;">修改文章</h3>
<%
  String bId = request.getParameter("bId");

  BlogManager bm = WebBlogUtil.getBlogManager();
  Blog blog=bm.get(Long.parseLong(bId));
  bm.close();
  request.setAttribute("blog",blog);
%>
<form action="update_blog.action" method="post">
  <input type="hidden" name="bId" value="${blog.id}">
  <table>
    <tr>
      <td>主题:</td>
      <td><input type="text" name="subject" size="100" value="${blog.subject}"></td>
    </tr>
    <tr>
      <td>内容：</td>
      <td><textarea id="blog_content" name="content" cols="100" rows="8">${blog.content}</textarea></td>
    </tr>
      <tr>
          <td></td>
          <td>
              公开: <input type="radio" name="permission" value="PUBLIC"
               ${empty blog.permission or blog.permission eq 'PUBLIC'?'checked="true"':''}><br>
              私有: <input type="radio" name="permission" value="SECRET"
              ${blog.permission eq 'SECRET'?'checked="true"':''}></td>
      </tr>
    <tr>
      <td></td>
      <td align="right"><input type="button" value="取消" onclick="javascript:history.back();">
        <input type="submit" value="发表"></td>
    </tr>
  </table>
</form>
</body>
</html>
