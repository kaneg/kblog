/**
 *
 * History:
 *   11-6-4 上午10:35 Created by ZGong
 */
package gz.aws.blog.util;

import gz.aws.blog.user.User;
import gz.aws.blog.user.UserService;
import gz.aws.blog.user.UserServiceFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-6-4 上午10:35
 */
public class UserUtil {
    public static User getUser() throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null || !userService.isUserLoggedIn()) {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
        return user;
    }

    public static User getAdmin() throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null || !userService.isUserLoggedIn() || !userService.isUserAdmin()) {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
        return user;
    }
}
