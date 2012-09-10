package gz.aws.blog.action;

import com.opensymphony.xwork2.ActionSupport;
import gz.aws.blog.CommentManager;
import gz.aws.blog.entity.Comment;
import gz.aws.blog.ioc.WebBlogUtil;
import gz.aws.blog.user.User;
import gz.aws.blog.user.UserServiceFactory;

/**
 * User: Kane Gong
 * Date: Nov 28, 2010
 * Time: 2:01:33 PM
 */
public class CommentAction
        extends ActionSupport {
    long BId;
    long CId;
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getBId() {
        return BId;
    }

    public void setBId(long BId) {
        this.BId = BId;
    }

    public long getCId() {
        return CId;
    }

    public void setCId(long CId) {
        this.CId = CId;
    }

    public String add() {
        Long blogId = BId;
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setContent(content);
        comment.setOwner(getUser());
        CommentManager cm = WebBlogUtil.getCommentManager();
        cm.add(comment);
        cm.close();
        return SUCCESS;
    }

    private User getUser() {
        return UserServiceFactory.getUserService().getCurrentUser();
    }

    public String delete() {
        CommentManager cm = WebBlogUtil.getCommentManager();
        cm.remove(CId);
        cm.close();
        return SUCCESS;
    }
}
