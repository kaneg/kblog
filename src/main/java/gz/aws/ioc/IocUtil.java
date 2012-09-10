/**
 *
 * History:
 *   2008-12-16 23:18:38 Created by ZGong
 */
package gz.aws.ioc;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by ZGong.
 *
 * @version 1.0 2008-12-16 23:18:38
 */
public class IocUtil {
    private static ServletContext CONTEXT;

    public static WebApplicationContext getWebApplicationContext(ServletContext context) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(context);
    }

    public static Object get(String name) {
        WebApplicationContext applicationContext = getWebApplicationContext(CONTEXT);
        return applicationContext.getBean(name);
    }

    public static Object get(ServletContext context, String name) {
        if (context == null) {
            context = CONTEXT;
        }
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        return applicationContext.getBean(name);
    }

    public static Object get(HttpServletRequest request, String name) {
        ServletContext context = request.getSession().getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        return applicationContext.getBean(name);
    }

    public static void setServletContext(ServletContext context) {
        CONTEXT = context;
    }
}
