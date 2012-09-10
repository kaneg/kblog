/**
 *
 * History:
 *   2010-6-25 23:04:37 Created by ZGong
 */
package gz.aws.blog.entity;

import gz.aws.blog.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-25 23:04:37
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long blogId;
    @Column(nullable = false)
    String content;
    Date createDate;
    @OneToOne
    private UserBean owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getOwner() {
        if (owner != null) {
            return owner.getUser();
        } else {
            return null;
        }
    }

    public void setOwner(User owner) {
        if (owner != null) {
            this.owner = new UserBean(owner);
        }
    }
}
