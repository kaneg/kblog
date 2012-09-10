<%@ page import="gz.aws.bill.entity.BillType" %>
<%@ page import="java.util.Date" %>
<%@ page import="gz.aws.bill.entity.Bill" %>
<%@ page import="gz.aws.bill.WebUtil" %>
<%@ page import="gz.aws.bill.BillTypeManager" %>
<%@ page import="gz.aws.bill.BillManager" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<html>
<head><title>Simple jsp page</title>
    <link type="text/css" href="/css/le-frog/jquery-ui-1.8.14.custom.css" rel="stylesheet"/>
    <%@include file="_js.jsp"%>
</head>
<body>
<%
  BillTypeManager billTypeManager = WebUtil.getBillTypeManager();
  String btIdStr = request.getParameter("btId");
  if (btIdStr != null) {
      BillType bt = billTypeManager.get(Long.parseLong(btIdStr));
      pageContext.setAttribute("billType",bt);
  }
%>
<form action="edit_bill_type.action" method="POST">
    <input type="hidden" name="btId" value="${billType.id}">
    <table>
        <tr>
            <td>In/Out：</td>
            <td>
                <select name="isIn" id="isIn">
                    <option value="true" ${billType.in?'selected':''}>Input</option>
                    <option value="fasle" ${!billType.in?'selected':''}>Output</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>类型：</td>
            <td><input type="text" name="billType" id="billType" value="${billType.value}"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"></td>
            <td><input type="reset" value="Cancel" onclick="top.TINY.box.hide();"></td>
        </tr>
    </table>
</form>
</body>
</html>