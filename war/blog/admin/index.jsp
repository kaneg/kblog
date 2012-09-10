<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/admin_login_required.jsp" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<ul>
    <li><s:a action="list_user">List user</s:a></li>
    <li><s:a action="add_user_view">Add user</s:a></li>
</ul>

</body>
</html>