<%@ page import="gz.aws.bill.BillTypeManager" %>
<%@ page import="gz.aws.bill.WebUtil" %>
<%@ page import="gz.aws.bill.entity.BillType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Edit bill type</title></head>
<body>

<center><img src="/js/TinyBox/images/preload.gif" alt="Processing..."></center>
<script type="text/javascript">
  function return_to_main()
  {
    top.document.location.href = "main.action?page=list_bill_type";
  }
  window.setTimeout("return_to_main()", 100)
</script>
</body>
</html>