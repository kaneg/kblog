<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/admin_login_required.jsp" %>
<%@include file="../_js.jsp" %>
<html>
<head><title>Compose</title></head>
<script type="text/javascript">
    KindEditor.ready(function(K) {
	K.create('#blog_content', {
        width : '720px',
        height : '400px'
	});
});
</script>
<body style="background-color:pink;margin-right:auto;margin-left:auto;width:800px;">
<br>
<h3 style="text-align:center;">撰写新文章</h3>
    <form action="add_blog.action" method="post">
        <table>
            <tr>
                <td>主题:</td>
                <td><input type="text" name="subject" size="100"></td>
            </tr>
            <tr>
                <td>内容：</td>
                <td><textarea id="blog_content" name="content" cols="100" rows="8"></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    公开: <input type="radio" name="permission" value="PUBLIC" checked="true"><br/>
                    私有: <input type="radio" name="permission" value="SECRET"></td>
            </tr>
            <tr>
                <td></td>
                <td align="right">
                    <input type="button" value="取消" onclick="javascript:history.back();">
                    <input type="submit" value="发表">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>