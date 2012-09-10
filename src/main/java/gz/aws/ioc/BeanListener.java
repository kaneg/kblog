/**
 *
 * History:
 *   2010-7-6 21:57:32 Created by ZGong
 */
package gz.aws.ioc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-7-6 21:57:32
 */
public class BeanListener
        implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        IocUtil.setServletContext(context);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        IocUtil.setServletContext(null);
    }
}
