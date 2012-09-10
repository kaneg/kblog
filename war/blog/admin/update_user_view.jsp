<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<head><title>Simple jsp page</title></head>
<body>Place your content here
<form action="update_user.action" method="post">
    <table>
        <tr>
            <td>Password:</td>
            <td><s:password name="password"/></td>
        </tr>
        <tr>
            <td>Nickname:</td>
            <td><s:textfield name="nickname"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><s:textfield name="email"/></td>
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