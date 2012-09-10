<%@ page import="gz.aws.blog.UserManager" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page import="gz.aws.blog.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<head><title>Simple jsp page</title></head>
<body>Place your content here
<form action="add_user.action" method="post">
    <table>
        <tr>
            <td> Name:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>Nickname:</td>
            <td><input type="text" name="nickname"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>isAdmin:</td>
            <td>
                Yes: <input type="radio" name="admin" value="true">
                No: <input type="radio" name="admin" value="false" checked="true">
            </td>
        </tr>
        <tr>
            <td><input type="submit"/></td>
            <td><input type="reset"/></td>
        </tr>
    </table>

    <input type="hidden" name="returnUrl" value="${param.returnUrl}">

</form>
</body>
</html>