package servlet;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        String name = req.getParameter("name");
        System.out.println(name);

        String password = req.getParameter("password");
        System.out.println(password);

        Optional<User> userFromDb =  userDao.getUserByName(name, password);
        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
                                                    System.out.println(user.getName());
            if (user.getPassword().equals(password)) {

                req.getSession().setAttribute("user", user);
 //               req.setAttribute("name", name);
                if (user.getRole().equals(2)) {
                    logger.debug("User with id " + user.getId() + " logged in system like user");
                    req.getRequestDispatcher("/goods").forward(req, resp);
                    return;
                } else if (user.getRole().equals(1)){
                    logger.debug("User with id " + user.getId() + " logged in system like admin");
                    req.getRequestDispatcher("/admin").forward(req, resp);
                    return;
                }
            }
        }
        req.setAttribute("error", "Пользователь с таким сочетанием логина/пароля не найден!" );
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
