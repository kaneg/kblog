package gz.aws.common;

import gz.aws.blog.user.User;
import gz.aws.blog.user.UserService;
import gz.aws.blog.user.UserServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator.
 *
 * @version 1.0 11-6-4 上午9:20
 */
public class AdminFilter implements Filter {

    private String adminPattern = "/admin/";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        int i = requestURI.indexOf(adminPattern);
        if (i != -1) {
            UserService userService = UserServiceFactory.getUserService();
            User user = userService.getCurrentUser();
            if (user == null || !userService.isUserLoggedIn() || !userService.isUserAdmin()) {
                response.sendRedirect(userService.createLoginURL(requestURI+"?"+request.getQueryString()));
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        String s = config.getInitParameter("admin_pattern");
        if (s != null) {
            adminPattern = s;
        }
    }
}
