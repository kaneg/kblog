<%@ page import="gz.aws.blog.user.User" %>
<%@ page import="gz.aws.blog.user.UserService" %>
<%@ page import="gz.aws.blog.user.UserServiceFactory" %><%
  UserService userService = UserServiceFactory.getUserService();
  User user = userService.getCurrentUser();
  if (user == null || !userService.isUserLoggedIn() || !userService.isUserAdmin()) {
    response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
    return;
  }
%>