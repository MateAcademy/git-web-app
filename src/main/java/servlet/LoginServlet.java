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

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String password = req.getParameter("password");

//проверяем есть ли такой пользователь по имени - в дальнейшем переделать можно на проверку по email
        User userFromDb = UserDaoHibImpl.getUserByLogin(name);
        if (userFromDb != null) {
//если есть то делаем хеш код из пароля что мы ввели и соли из б/д
            //       if (userFromDb.isPresent()) {
            //          User user = userFromDb.get();
            String hashPassword = HashUtil.getSHA512SecurePassword(password, userFromDb.getSalt());
            if (userFromDb.getPassword().equals(hashPassword)) {

                HttpSession session = req.getSession(); //из рекв достаем сессию
                session.setAttribute("sessionUser", userFromDb.getName());
                ServletContext servletContext = req.getServletContext();

                if (session.getAttribute("sessionUser") == null) {
                    session.setAttribute("user", userFromDb);
                    session.setAttribute("sessionUser", userFromDb.getName());
                    servletContext.setAttribute("name", userFromDb.getName());
                }
                req.setAttribute("name", userFromDb.getName());

                if (userFromDb.getRole().getName().equals("user")) {
                    logger.debug("User with id " + userFromDb.getId() + " logged in system like user");
                    req.getRequestDispatcher("/goods").forward(req, resp);
                    return;
                } else if (userFromDb.getRole().getName().equals("admin")) {
                    req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
                    req.setAttribute("servletContext", servletContext.getAttribute("name"));
                    session.setMaxInactiveInterval(60);
                    logger.debug("User with id " + userFromDb.getId() + " logged in system like admin");
                    req.getRequestDispatcher("admin/adminPage.jsp").forward(req, resp);
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
