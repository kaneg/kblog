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
<head><title>Edit bill view</title>
    <link type="text/css" href="/css/le-frog/jquery-ui-1.8.14.custom.css" rel="stylesheet"/>
    <%@include file="_js.jsp"%>
</head>
<%
  BillManager billManager = WebUtil.getBillManager();
  String bIdStr = request.getParameter("bId");
  if (bIdStr != null) {
      Bill bill = billManager.get(Long.parseLong(bIdStr));
      pageContext.setAttribute("bill",bill);
      BillTypeManager billTypeManager = WebUtil.getBillTypeManager();
      List<BillType> billTypes = billTypeManager.listAll(bill.isIn());
      pageContext.setAttribute("billTypes", billTypes);
  }
    billManager.close();
%>
<script type="text/javascript">
  $(function()
  {
    $("#datepicker").datepicker({
      dateFormat:'yy-mm-dd'
    }).datepicker('setDate', new Date());
  });

  function createNewBillType(options) {
      if (options[options.selectedIndex].value != -1) {
          return false;
      }
      var prompt = window.prompt('请输入分类名：');
      if (prompt) {
          var billType = prompt;
          var callback = function(billId) {
              if (billId && $.trim(billId)) {
                  billId = $.trim(billId);
                  var o = new Option(billType, billId);
                  var select = $("#billType").get(0);
                  select.options.add(o);
                  select.selectedIndex = select.options.length - 1;
              }
          }
          $.post("add_bill_type.action", {billType:billType,isIn:${bill.in}}, callback);
      } else {
          options.selectedIndex = 0;
          return false;
      }
  }
</script>
<body>

<form action="edit_bill.action" method="post">
  <input type="hidden" name="bId" value="${bill.id}">
  <table>
    <tr>
      <td>时间：</td>
        <f:formatDate value="${bill.issueDate}" pattern="yyyy-MM-dd" var="issueDate"/>
      <td><input type="text" name="issueDate" id="datepicker" readonly="true" value="${issueDate}"></td>
    </tr>
    <tr>
      <td>金额：</td>
      <td><input type="text" name="amount" value="${bill.amount}"></td>
    </tr>
    <tr>
      <td>地点：</td>
      <td><input type="text" name="location" value="${bill.location}"></td>
    </tr>
    <tr>
      <td>描述：</td>
      <td><input type="text" name="desc" value="${bill.desc}"></td>
    </tr>
    <tr>
      <td>类型：</td>
      <td>
        <select name="billType" id="billType" onchange="return createNewBillType(this)">
          <option value="0">未分类</option>
            <option value="-1" >新建分类</option>
            <c:forEach items="${billTypes}" var="billType">
              <option value="${billType.id}" ${bill.billTypeId eq billType.id?'selected':''}>${billType.value}</option>
          </c:forEach>
        </select>
      </td>
    </tr>
    <tr>
      <td><input type="submit" value="Edit"></td>
      <td><button onclick="top.TINY.box.hide();return false;">Cancel</button></td>
    </tr>
  </table>
</form>
</body>
</html>