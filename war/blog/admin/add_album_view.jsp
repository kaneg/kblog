<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/admin_login_required.jsp" %>
<html>
<head><title>Add Album</title></head>
<body>

<form action="<s:url action='add_album'/>" method="post">
    <table>
        <tr>
            <td>名称：</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>描述：</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td><input type="button" value="取消" onclick="javascript:history.back();"></td>
            <td><input type="submit" value="添加"></td>
        </tr>
    </table>
</form>
</body>
</html>