<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<head>
    <title>welcome to kblog</title>
</head>
<body>
<img src="/img/banner.jpg" width="100%"/>
<br><br>

<form action="login.action" method="post">
    用户名 <input type="text" name="username" size="17" maxLength="8"/>
    <br><br>
    密&nbsp;&nbsp;码 <input type="password" name="password" size="17" maxLength="8"/>
    <br>
    <br>

    <input type="submit" value="登录"/>
    <input type="reset" value="取消"/>
</form>
</body>
</html>