package gz.aws.blog.action;

import gz.aws.blog.BlogManager;
import gz.aws.blog.entity.Blog;
import gz.aws.blog.ioc.WebBlogUtil;
import gz.aws.mail.MailSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: Kane Gong
 * Date: Nov 27, 2010
 * Time: 1:59:12 PM
 */
public class BlogAction
        extends AuthedAction {
    private final static Log logger = LogFactory.getLog(BlogAction.class);
    private static final String MAIL_ADDRESS = "";

    long bId;
    String content;
    String subject;
    String permission;

    public long getBId() {
        return bId;
    }

    public void setBId(long bId) {
        this.bId = bId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String add() throws Exception {
        BlogManager bm = WebBlogUtil.getBlogManager();
        Blog blog = new Blog();
        blog.setOwner(getUser());
        blog.setSubject(subject);
        blog.setContent(content);
        blog.setPermission(Blog.Permission.valueOf(permission));
        bm.add(blog);
        bm.close();
        sendMail("[Add Blog]" + subject);
        return SUCCESS;
    }

    private void sendMail(String subject) throws Exception {
        for (int i = 0; i < 3; i++) {//try three times to send the message
            try {
                MailSender.sendMessage(subject, content, getUser().getEmail(), MAIL_ADDRESS);
                return;
            } catch (Exception e) {
                logger.debug("Failed to send mail.", e);
            }
        }
    }

    public String update() throws Exception {
        BlogManager bm = WebBlogUtil.getBlogManager();
        Blog blog = bm.get(bId);
        blog.setOwner(getUser());
        blog.setSubject(subject);
        blog.setContent(content);
        blog.setPermission(Blog.Permission.valueOf(permission));
        bm.update(blog);
        bm.close();
        sendMail("[Edit Blog]" + subject);
        return SUCCESS;
    }

    public String delete() throws Exception {
        BlogManager bm = WebBlogUtil.getBlogManager();
        bm.delete(bId);
        bm.close();
        return SUCCESS;
    }
}
