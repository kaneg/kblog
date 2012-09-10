<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/admin_login_required.jsp" %>
<c:url value="/file_upload" var="url">
  <c:param name="forward_url" value="/blog/admin/add_photo_action.jsp"/>
  <c:param name="key" value="myPicture"/>
</c:url>

<form action="<s:url action='add_photo'/>" method="post" enctype="multipart/form-data" accept-charset="utf-8">
  <table>
    <tr>
      <td>名称：</td>
      <td><input type="text" name="photoName"></td>
    </tr>
    <tr>
      <td>图片：</td>
      <td><input type="file" name="photo"></td>
    </tr>
    <tr>
      <td><input type="button" value="取消" onclick="javascript:history.back();"></td>
      <td><input type="submit" value="上传"></td>
    </tr>
  </table>


</form>