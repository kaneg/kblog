<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head><title>Simple jsp page</title></head>
<script type="text/javascript" src="/js/TinyBox/tinybox.js"></script>

<body>
<h1>Back to main page in 2 seconds...</h1>
<button onblur="return_to_main()">OK</button>
<script type="text/javascript">
  function return_to_main()
  {
    top.document.location.href = "main.action?page=list_bill&isIn=${param.isIn}";
  }
  window.setTimeout("return_to_main()", 1000)
</script>
</body>
</html>