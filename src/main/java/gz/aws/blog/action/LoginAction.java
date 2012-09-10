/**
 *
 * History:
 *   11-5-4 上午10:03 Created by ZGong
 */
package gz.aws.blog.action;

import com.opensymphony.xwork2.ActionSupport;
import gz.aws.blog.UserManager;
import gz.aws.blog.ioc.WebBlogUtil;
import gz.aws.blog.user.User;
import gz.aws.blog.user.UserService;
import gz.aws.blog.user.UserServiceFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-5-4 上午10:03
 */
public class LoginAction extends ActionSupport {

    private String username;
    private String password;
    private String returnUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String login() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        UserManager userManager = WebBlogUtil.getUserManager();
        User user = userManager.auth(username, password);
        if (user == null) {
            this.addActionError("Password or username error");
            return ERROR;
        }

        UserService userService = UserServiceFactory.createUserService(user);
        HttpSession session = request.getSession();
        session.setAttribute(UserService.class.getSimpleName(), userService);

        if (returnUrl != null && !"".equals(returnUrl)) {
            response.sendRedirect(returnUrl);
            return null;
        }
        return SUCCESS;
    }

    public String logout() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        UserServiceFactory.logout();
        session.invalidate();
        String returnUrl = request.getParameter("returnUrl");
        if (returnUrl != null && !"".equals(returnUrl)) {
            response.sendRedirect(returnUrl);
            return null;
        }
        return SUCCESS;
    }
}
