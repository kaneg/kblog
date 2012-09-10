package gz.aws.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: Kane Gong
 * Date: Nov 25, 2010
 * Time: 12:29:58 AM
 */
public class SafeFilter
        implements javax.servlet.Filter {
    boolean on;
    String[] excludes;

    public void init(FilterConfig config)
            throws ServletException {
        String s = config.getInitParameter("on");
        on = Boolean.valueOf(s);
        s = config.getInitParameter("excludes");
        if (s != null) {
            excludes = s.split(",");
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        if (!on) {
            chain.doFilter(req, resp);
            return;
        }
        HttpServletRequest request = (HttpServletRequest) req;
        boolean isContinue = true;
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf(".jsp") > 0) {
            isContinue = false;
            if (excludes != null && excludes.length > 0) {
                for (String s : excludes) {
                    if (requestURI.indexOf(s) != -1) {
                        isContinue = true;
                        break;
                    }
                }
            }
        }

        if (isContinue) {
            chain.doFilter(req, resp);
        } else {
            request.getRequestDispatcher("/errors/access_denied.jsp").forward(req, resp);
        }
    }

    public void destroy() {
    }
}
