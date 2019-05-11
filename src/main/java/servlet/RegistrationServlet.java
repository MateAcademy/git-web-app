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

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);
    private static final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("User with id (?) start registration");
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role= req.getParameter("role");

        Optional<User> userFromDb =  userDao.getUserByName(name, password);
        if (userFromDb.isPresent()) {
            logger.error("Can't add user in database, zero rows changes after sql query");
            req.setAttribute("error", "Пользователь не был добавлен, он уже есть в базе данных");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }else {

        int howManyUsersChanged = userDao.addUser(new User(name, password, "test@test.ru", 2));

        System.out.println(howManyUsersChanged);

        if (howManyUsersChanged == 1) {
            req.setAttribute("isRegistrated", true);
            logger.debug("User with name " + name + " was registered");
        } else {
            logger.error("Can't add user in database, zero rows changes after sql query");
            req.setAttribute("error", "Пользователь не был добавлен");
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }}

}
