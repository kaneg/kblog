/**
 *
 * History:
 *   11-4-16 下午1:23 Created by ZGong
 */
package gz.aws.blog.user;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-4-16 下午1:23
 */
public class UserService {
    User currentUser;

    UserService(User currentUser) {
        this.currentUser = currentUser;
    }

    UserService() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public boolean isUserAdmin() {
        return isUserLoggedIn()&&currentUser.isAdmin();
    }

    public String createLoginURL(String loginURL) {
        return "/blog/login_view.action?returnUrl=" + loginURL;
    }

    public String createLogoutURL(String logoutURL) {
        return "/blog/admin/logout.action?returnUrl=" + logoutURL;
    }
}
