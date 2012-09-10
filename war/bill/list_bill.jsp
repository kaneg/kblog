<%@ page import="gz.aws.bill.entity.Bill" %>
<%@ page import="java.util.List" %>
<%@ page import="gz.aws.bill.WebUtil" %>
<%@ page import="gz.aws.bill.BillManager" %>
<%@ page import="gz.aws.bill.BillTypeManager" %>
<%@ page import="gz.aws.bill.entity.BillType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<head><title>List bill</title>
  <link rel="stylesheet" href="/js/TinyBox/style.css"/>
  <style type="text/css">
    .list_table {
    /*border:1px black solid*/
    }

    .add_bill {
      height: 50px;
      float: right;
    }
  </style>
  <style type="text/css" title="currentStyle">
    @import "/css/datatable/css/demo_page.css";
    @import "/css/datatable/css/demo_table.css";
    @import "/css/datatable/css/demo_table_jui.css";
  </style>
  <style type="text/css">
    .thread_tr{
      text-align:left
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
    function add_bill_view()
    {
      var bill_page = "add_bill_view.action?isIn=${param.isIn}";
      popupOpen(bill_page, 500, 300)
    }
    function popupOpen(popupUrl, popupWidth, popupHeight)
    {
      var content = '<iframe id="frameTiny" src="' + popupUrl + '" width="100%" height="100%" scrolling="no" frameborder="no" marginwidth="0" marginheight="0"></iframe>';
      TINY.box.show(content, 0, popupWidth, popupHeight, 0);
    }
    function confirmDelete() {
        return  window.confirm("Do you really want to delete it?")
    }
    function edit_bill_view(bId)
    {
      var bill_page = "edit_bill_view.action?bId="+bId;
      popupOpen(bill_page, 500, 300)
    }
  </script>
</head>
<body>
<%
  BillManager billManager = WebUtil.getBillManager();
  BillTypeManager billTypeManager = WebUtil.getBillTypeManager();
  List<Bill> bills = billManager.listAllByInOrOut(Boolean.valueOf(request.getParameter("isIn")));
  pageContext.setAttribute("bills", bills);
%>
<div style="float:left">
    <c:if test="${param.isIn}">
        收入明细：
    </c:if>
    <c:if test="${!param.isIn}">
        支出明细：
    </c:if>
</div>
<div class="add_bill">
  <button onclick="add_bill_view()">Add bill</button>
</div>

<div id="container">
  <table class="list_table display" id="list_table" cellpadding="0" cellspacing="0" border="0">
    <thead>
    <tr class="thread_tr">
      <th>Type</th>
      <th>Amount</th>
      <th>Desc</th>
      <th>location</th>
      <th>issueDate</th>
      <th>action</th>
    </tr>
    </thead>
    <tbody>
    <%
      float total=0;
    %>
    <c:forEach items="${bills}" var="bill">
      <%
        Bill bill = (Bill) pageContext.getAttribute("bill");
        total+=bill.getAmount();
        BillType type = billTypeManager.get(bill.getBillTypeId());
        pageContext.setAttribute("billType", type);
      %>
      <tr>
        <td>${empty billType?'默认':billType.value}</td>
        <td>${bill.amount}</td>
        <td>${bill.desc}</td>
        <td>${bill.location}</td>
        <td>${bill.issueDate}</td>
        <td>
            <a href="delete_bill.action?bId=${bill.id}&isIn=${param.isIn}" onclick="return confirmDelete()">Delete</a>
            <a href="javascript:edit_bill_view(${bill.id})">Edit</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div>Total:<%=total%>元</div>
</div>
</body>
</html>