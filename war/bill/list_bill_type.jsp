<%@ page import="gz.aws.bill.entity.BillType" %>
<%@ page import="java.util.List" %>
<%@ page import="gz.aws.bill.WebUtil" %>
<%@ page import="gz.aws.bill.BillTypeManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<head><title>Simple jsp page</title>
  <style type="text/css" title="currentStyle">
    @import "/css/datatable/css/demo_page.css";
    @import "/css/datatable/css/demo_table.css";
    @import "/css/datatable/css/demo_table_jui.css";
  </style>
  <style type="text/css">
    .thread_tr {
      text-align: left;
    }
    .add_bill_type{
        float: right;
    }
  </style>
    <%@include file="_js.jsp"%>
  <script type="text/javascript">
    $(document).ready(function()
    {
      $('#list_table').dataTable({
          bJQueryUI: true
          ,sPaginationType: "full_numbers"
      });
    });
    function add_bill_type_view()
    {
      var content = $("#add_list_type_view").text()

      TINY.box.show(content, 0, 0, 0, 1);
    }

    function edit_bill_type_view(btId)
    {
      var bill_page = "edit_bill_type_view.action?btId="+btId;
      popupOpen(bill_page, 300, 100)
    }
    function popupOpen(popupUrl, popupWidth, popupHeight)
    {
      var content = '<iframe id="frameTiny" src="' + popupUrl + '" width="100%" height="100%" scrolling="no" frameborder="no" marginwidth="0" marginheight="0"></iframe>';
      TINY.box.show(content, 0, popupWidth, popupHeight, 0);
    }
    function createNewBillType()
    {
      var billType = $("#billType").val();
      var isIn = $("#isIn").val();
      var callback = function(txt)
      {
        document.location.href = "main.action?page=list_bill_type";
      }
      $.post("add_bill_type.action", {billType:billType,isIn:isIn}, callback)
      return false;
    }
    function confirmDelete() {
        return  window.confirm("Do you really want to delete it?")
    }
  </script>
</head>
<body>
<%
  BillTypeManager billTypeManager = WebUtil.getBillTypeManager();
  List<BillType> billTypes = billTypeManager.listAll();
  pageContext.setAttribute("billTypes", billTypes);
%>
<div class="add_bill_type">
  <button onclick="add_bill_type_view()">Add bill type</button>
</div>
<textarea style="display:none" id="add_list_type_view">
  <form action="" onsubmit="return createNewBillType()">
    <table>
      <tr>
        <td>In/Out：</td>
        <td><select name="isIn" id="isIn">
          <option value="true">Input</option>
          <option value="fasle">Output</option>
        </select></td>
      </tr>
      <tr>
        <td>类型：</td>
        <td><input type="text" name="billType" id="billType"></td>
      </tr>
      <tr>
        <td><input type="submit" value="Submit"></td>
        <td><input type="reset" value="Cancel" onclick="TINY.box.hide();"></td>
      </tr>
    </table>
  </form>
</textarea>

<div id="container">
  <table class="list_table display" id="list_table" cellpadding="0" cellspacing="0" border="0">
    <thead>
    <tr class="thread_tr">
      <th class="thread_th">In/Out</th>
      <th>Type</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${billTypes}" var="billType">
      <tr>
        <td>${billType.in?"In":"Out"}</td>
        <td>${billType.value}</td>
        <td>
            <a href="delete_bill_type.action?btId=${billType.id}" onclick="return confirmDelete()">Delete</a>
            <a href="javascript:void(0)" onclick="edit_bill_type_view(${billType.id})">Edit</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>