<%@ page import="gz.aws.blog.PreferenceManager" %>
<%@ page import="gz.aws.blog.entity.FileData" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%
  FileData fd = (FileData) request.getAttribute("fileData");
  PreferenceManager pm = WebBlogUtil.getPrefManager();
  pm.setPref(request.getParameter("key"), String.valueOf(fd.getId()));
  pm.close();
  response.sendRedirect("/blog/main.action?pageType=pref");
%>