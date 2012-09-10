<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" media="all" href="/css/custom-theme/jquery-ui-1.8.6.custom.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="/css/common.css"/>
  <style type="text/css">
    #pref_tab{
        font-weight:100;
        font-size:12px;
    }
  </style>
</head>
<body>
<div id="pref_tab">
  <ul>
    <li><a href="#myPic">我的头像</a></li>
  </ul>
  <div id="myPic">
    <%@include file="update_myPic_view.jsp" %>
  </div>
</div>
<script type="text/javascript">
  $("#pref_tab").tabs();
</script>
</body>
</html>