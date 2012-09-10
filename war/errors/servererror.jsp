<%@page isErrorPage="true" %>
<h1>This is an error page!</h1>
<a href="/">Go back to home page!</a>
The page will go back to home page in 5 seconds...
<script type="text/javascript">
//    window.setTimeout("document.location.href='/'",5000);
    <%
    exception.printStackTrace(response.getWriter());
    %>
</script>