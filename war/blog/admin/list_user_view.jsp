<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/admin_login_required.jsp" %>
<html>
<head><title>List users</title>
    <style type="text/css">
        .users {
            border: black solid 1px;
        }
    </style>
</head>
<body>
<table>
    <%--@elvariable id="users" type="java.util.List<gz.aws.blog.user.User>"--%>
    <%--@elvariable id="user" type="gz.aws.blog.user.User"--%>
    <c:forEach var="user" items="${users}">
        <tr class="users">
            <td class="users">${user.id}</td>
            <td class="users">${user.name}</td>
            <td class="users">${user.nickname}</td>
            <td class="users">${user.email}</td>
            <td class="users">${user.admin}</td>
            <td><s:a action="delete_user"><s:param name="id">${user.id}</s:param>Delete</s:a></td>
            <td><s:a action="update_user_view"><s:param name="id">${user.id}</s:param>Update</s:a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>