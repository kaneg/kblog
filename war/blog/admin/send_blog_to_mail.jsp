<%@ page import="gz.aws.blog.BlogManager" %>
<%@ page import="gz.aws.blog.entity.Blog" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>
<%@ page import="java.util.Properties" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="javax.mail.*" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/admin_login_required.jsp" %>
<%
  BlogManager bm = WebBlogUtil.getBlogManager();
  Blog blog = bm.get(Long.parseLong(request.getParameter("bId")));
  Properties props = new Properties();
  Session mailSession = Session.getDefaultInstance(props, null);
  String from = user.getEmail();
  String to = user.getEmail();

  try {
    Message msg = new MimeMessage(mailSession);
    msg.setFrom(new InternetAddress(from));
    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    msg.setSubject(blog.getSubject());
    Multipart mp = new MimeMultipart();
    MimeBodyPart htmlPart = new MimeBodyPart();
    htmlPart.setContent(blog.getContent(), "text/html");
    mp.addBodyPart(htmlPart);
    msg.setContent(mp);
    Transport.send(msg);
  }
  catch (AddressException e) {
    e.printStackTrace();
    // ...
  }
  catch (MessagingException e) {
    e.printStackTrace();
    // ...
  }
%>