/**
 *
 * History:
 *   11-5-4 下午12:24 Created by ZGong
 */
package gz.aws.blog.action;

import gz.aws.blog.UserManager;
import gz.aws.blog.entity.UserBean;
import gz.aws.blog.ioc.WebBlogUtil;
import gz.aws.blog.user.User;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-5-4 下午12:24
 */
public class UserAction extends AuthedAction {
    private User[] users;
    long id;
    String name;
    String nickname;
    String email;
    String password;
    boolean isAdmin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public void validate() {
        try {
            User admin = getAdmin();
            if (admin == null) {
                throw new IllegalStateException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String index() {
        return SUCCESS;
    }

    public String get() throws Exception {
        UserManager userManager = WebBlogUtil.getUserManager();
        UserBean userBean = userManager.get(id);
        name = userBean.getName();
        email = userBean.getEmail();
        nickname = userBean.getNickname();
        isAdmin = userBean.isAdmin();
        return SUCCESS;
    }

    public String list() throws Exception {
        UserManager userManager = WebBlogUtil.getUserManager();
        users = userManager.listAllUsers();
        return SUCCESS;
    }

    public String add() throws Exception {
        UserManager userManager = WebBlogUtil.getUserManager();
        UserBean userBean = new UserBean(new User(null, name, nickname, email, isAdmin));
        userBean.setPassword(password);
        userManager.add(userBean);
        return SUCCESS;
    }

    public String delete() throws Exception {
        UserManager userManager = WebBlogUtil.getUserManager();
        userManager.remove(id);
        return SUCCESS;
    }

    public String update() throws Exception {
        UserManager userManager = WebBlogUtil.getUserManager();
        User user = getUser();
        UserBean userBean = userManager.get(user.getId());
        if (nickname != null && !nickname.trim().isEmpty()) {
            userBean.setName(nickname);
        }
        if (email != null && !email.trim().isEmpty()) {
            userBean.setEmail(email);
        }
        if (password != null && !"".equals(password)) {
            userBean.setPassword(password);
        }
        userManager.update(userBean);
        return SUCCESS;
    }
}
