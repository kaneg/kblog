package gz.aws.blog.user;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator.
 *
 * @version 1.0 11-5-1 上午8:42
 */
public class UserServiceFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session = request.getSession();
            UserService userService = (UserService) session.getAttribute(UserService.class.getSimpleName());
            if (userService != null) {
                UserServiceFactory.setUserService(userService);
            }
            chain.doFilter(req, resp);
        } finally {
            UserServiceFactory.clearUserService();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
