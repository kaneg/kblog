/**
 *
 * History:
 *   11-5-1 上午10:38 Created by ZGong
 */
package gz.aws.blog.entity;

import gz.aws.blog.user.User;

import javax.persistence.*;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-5-1 上午10:38
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true,nullable = false)
    String name;
    String nickname;
    String email;
    String password;
    @Column()
    boolean isAdmin;

    public UserBean(User owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.nickname = owner.getNickname();
        this.email = owner.getEmail();
        this.isAdmin=owner.isAdmin();
    }

    public UserBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setUser(User owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.nickname = owner.getNickname();
        this.email = owner.getEmail();
        this.isAdmin = owner.isAdmin();
    }

    public User getUser() {
        return new User(id, name, nickname, email, isAdmin);
    }
}
