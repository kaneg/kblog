/**
 *
 * History:
 *   2010-6-12 17:14:23 Created by ZGong
 */
package gz.aws.blog.entity;

import gz.aws.blog.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-12 17:14:23
 */
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String subject;
    private Date createDate;
    private Date modifyDate;
    @Embedded
    private Text content;
    @OneToOne
    private UserBean owner;
    @Enumerated(EnumType.STRING)
    private Status status = Status.NORMAL;
    @Enumerated(EnumType.STRING)
    private Permission permission = Permission.PUBLIC;

    public enum Status {
        NORMAL, DELETED
    }

    public enum Permission {
        PUBLIC, SECRET;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getContent() {
        return content.getValue();
    }

    public void setContent(String content) {
        this.content = new Text(content);
    }

    public UserBean getOwner() {
        return owner;
    }

    public void setOwner(UserBean owner) {
        this.owner = owner;
    }

    public void setOwner(User owner) {
        this.owner = new UserBean(owner);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", content='" + content + '\'' +
                ", owner=" + owner +
                '}';
    }
}
