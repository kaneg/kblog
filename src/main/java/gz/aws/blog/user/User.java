/**
 *
 * History:
 *   11-4-16 下午1:22 Created by ZGong
 */
package gz.aws.blog.user;

import java.io.Serializable;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-4-16 下午1:22
 */
public class User implements Serializable {
    protected Long id;
    protected String name;
    protected String nickname;
    protected String email;
    boolean isAdmin;

    public User() {
    }

    public User(Long id, String name, String nickname, String email, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.isAdmin = isAdmin;
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

    public void setEmail(String mail) {
        this.email = mail;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (!id.equals(user.id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
