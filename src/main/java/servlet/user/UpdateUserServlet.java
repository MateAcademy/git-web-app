package servlet.user;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import servlet.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/admin/users/update" )
public class UpdateUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);
    private static final UserDao userDao =new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        logger.debug("user updating");
        String name = request.getParameter("name");
        Optional<User> userOptional = userDao.getUserByName(name);
        if (userOptional.isPresent()) {
            request.setAttribute("user", userOptional.get());
            request.getRequestDispatcher("/users/editUser.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        Optional<User> userOptional = userDao.getUserByName(req.getParameter("name"));
        if (userOptional.isPresent()) {
            User userFromDb = userOptional.get();
//TODO проверить данные на нулл емпти и роль валидная или нет
//После изменения пароль разлогинить нужно, грохнуть сессию
            userFromDb.setName(req.getParameter("name"));
            userFromDb.setPassword(req.getParameter("password"));
            userFromDb.setEmail(req.getParameter("email"));
            userFromDb.setRole(Integer.valueOf(req.getParameter("role")));
            userDao.updateUser(userFromDb);
            req.setAttribute("users", userDao.getAllUsers());
            logger.debug("redirect to admin page");
//          req.getRequestDispatcher("/admin/users").forward(req, resp);
            resp.sendRedirect("/admin/users");
        }
    }
}
