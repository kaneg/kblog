<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<s:actionmessage/>
<s:actionerror/>
<form action="login.action" method="post">
    <table>
        <tr>
            <td> Name:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
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