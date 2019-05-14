package servlet;

import dao.UserDao;
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
    private static final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String nameFromForm = req.getParameter("name");
        String passwordFromForm = req.getParameter("password");

//проверяем есть ли такой пользователь по имени - в дальнейшем переделать можно на проверку по email
        Optional<User> userFromDb =  userDao.getUserByName(nameFromForm);
//если есть то делаем хеш код из пароля что мы ввели и соли из б/д
        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
            String hashPasswordFromForm = HashUtil.getSHA512SecurePassword(passwordFromForm, user.getSalt());
//сравниваем хеш из БД и тот что мы сделали
            if (user.getPassword().equals(hashPasswordFromForm)) {

                HttpSession session = req.getSession(); //из рекв достаем сессию
//                session.setAttribute("sessionUser", user.getName());
                ServletContext servletContext = req.getServletContext();

//                if (session.getAttribute("sessionUser") == null) {
                    session.setAttribute("user", user);
                    session.setAttribute("sessionUser", user.getName());
                    servletContext.setAttribute("name", user.getName());
//                }

                req.setAttribute("name", user.getName());
                if (user.getRole().equals(2)) {

                    logger.debug("User with id " + user.getId() + " logged in system like user");
                    req.getRequestDispatcher("/goods").forward(req, resp);
                    return;
                } else if (user.getRole().equals(1)){
                    req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
                    req.setAttribute("servletContext", servletContext.getAttribute("name"));
                    session.setMaxInactiveInterval(60);

                    logger.debug("User with id " + user.getId() + " logged in system like admin");
                    req.getRequestDispatcher("admin/adminPage.jsp").forward(req, resp);
                    return;
                }
            }
        }
        req.setAttribute("error", "Пользователь с таким именем / паролем не найден!" );
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
