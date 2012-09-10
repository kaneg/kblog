package gz.aws.blog.action;

import com.opensymphony.xwork2.ActionSupport;
import gz.aws.blog.user.User;
import gz.aws.blog.user.UserService;
import gz.aws.blog.user.UserServiceFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kane Gong
 * Date: Nov 27, 2010
 * Time: 2:02:51 PM
 */
public class AuthedAction
        extends ActionSupport {
    public User getUser() throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null || !userService.isUserLoggedIn()) {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
        return user;
    }

    public User getAdmin() throws Exception {
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
