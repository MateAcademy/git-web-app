package filter;

import model.Roles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

//        User user = (User) request.getSession().getAttribute("user");
//        if (user != null && user.getRole().equals("admin")) {
//            filterChain.doFilter(servletRequest, servletResponse);

        String name =  String.valueOf(request.getSession().getAttribute("sessionUser"));
        if (name != null && name.equals(Roles.admin.toString())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher("accessDenied.jsp").forward(request, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
