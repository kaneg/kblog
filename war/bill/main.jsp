<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<head>
  <title>Main</title>
  <link type="text/css" href="/css/le-frog/jquery-ui-1.8.14.custom.css" rel="stylesheet" />
  <style type="text/css">
    .main_layout {
      background: #999;
      height: 100%;
      margin: 0 auto;
      width: 100%; /*max-width: 900px;*/
      min-width: 700px;
      /*_width: 700px; *//* min-width for IE6 */
    }

  </style>
    <%@include file="_js.jsp"%>
  <script>
    $(document).ready(function ()
    {
      $('#main_layout').layout({
          applyDefaultStyles: true
          ,east__size:0
          ,north__spacing_open:	0
          ,south__spacing_open:	0
          ,west__spacing_open:	0
      });
    });
  </script>
</head>
<body>
<div id="main_layout" class="main_layout">
  <div class="ui-layout-north">
      <div><a href="/">返回博客</a> </div>
      <h3 style="text-align:center;">Online Bill</h3>
  </div>
  <div class="ui-layout-south" style="text-align:center">&copy;Created by Kane 2010/09</div>
  <div class="ui-layout-west">
    <ul>
      <li><a href="main.action?page=list_bill&isIn=true">收入</a></li>
      <li><a href="main.action?page=list_bill&isIn=false">支出</a></li>
      <li><a href="main.action?page=list_bill_type">收支分类</a></li>
    </ul>
  </div>
  <div class="ui-layout-center">
    <c:choose>
      <c:when test="${param.page eq 'list_bill'}">
        <%@include file="list_bill.jsp" %>
      </c:when>
      <c:when test="${param.page eq 'list_bill_type'}">
        <%@include file="list_bill_type.jsp" %>
      </c:when>
      <c:otherwise>
        <%@include file="list_bill.jsp"%>
      </c:otherwise>
    </c:choose>
  </div>
</div>
</body>
<%@ include file="../analytics.jsp"%>
</html>