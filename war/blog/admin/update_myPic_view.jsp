<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<body>
<form action="admin/update_myPic.action" method="post" enctype="multipart/form-data">
  <input type="file" name="myPic">
  <input type="submit" value="上传头像">
</form>
</body>
</html>