/**
 *
 * History:
 *   11-6-4 上午11:59 Created by ZGong
 */
package gz.aws.common;

import gz.aws.blog.UserManager;
import gz.aws.blog.ioc.WebBlogUtil;
import gz.aws.blog.user.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-6-4 上午11:59
 */
public class AdminGenerateListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UserManager userManager = WebBlogUtil.getUserManager();
        createAdmins(userManager, "admin");
        userManager.close();
    }

    private void createAdmins(UserManager userManager, String name) {
        User user = null;
        try {
            user = userManager.getUser(name);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (user == null) {
            userManager.create(name, name, name, "", true);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
