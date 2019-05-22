package servlet;

import dao.UserDaoHibImpl;
import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");

//        User userFromDb = UserDaoHibImpl.getUserByLogin(name);
        Optional<User> userFromDb = UserDaoHibImpl.getUserByLoginOptional(name);
//        if (userFromDb != null) {
        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
            String hashPassword = HashUtil.getSHA512SecurePassword(password, user.getSalt());

            if (user.getPassword().equals(hashPassword)) {
                req.getSession().setAttribute("user", user);
                req.setAttribute("name", name);
//это для фильтра "sessionUser":
                req.getSession().setAttribute("sessionUser", name);
                req.getSession().setMaxInactiveInterval(60);
//                HttpSession session = req.getSession();
//                ServletContext servletContext = req.getServletContext();

//                if (session.getAttribute("sessionUser") == null) {
//                session.setAttribute("user", userFromDb);
//                session.setAttribute("sessionUser", user.getName());
//                servletContext.setAttribute("name", user.getName());
//                }


                if (user.getRole().getName().equals("user")) {
                    logger.debug("User with id " + user.getId() + " logged in system like user");
                    req.getRequestDispatcher("/admin/goods").forward(req, resp);
                    return;
                } else if (user.getRole().getName().equals("admin")) {
 //                   req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
//                    req.setAttribute("user", session.getAttribute("user"));
//                    req.setAttribute("servletContext", servletContext.getAttribute("name"));
//                    session.setMaxInactiveInterval(60);
                    logger.debug("User with id " + user.getId() + " logged in system like admin");
                    req.getRequestDispatcher("/admin/adminPage.jsp").forward(req, resp);
                    return;
                }
            }
        }
        req.setAttribute("error", "Пользователь с таким именем / паролем не найден!");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("admin/adminPage.jsp").forward(req, resp);
    }
}
