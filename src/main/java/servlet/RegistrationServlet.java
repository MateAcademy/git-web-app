package servlet;

import dao.UserDaoJdbc;
import dao.UserDaoHibImpl;
import model.Role;
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

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);
    private static final UserDaoJdbc USER_DAO_JDBC = new UserDaoJdbc();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();

//        if (session.getAttribute("sessionUser") == null) {
        session.setAttribute("sessionUser", name);
        servletContext.setAttribute("name", name);
        session.setMaxInactiveInterval(60);
//        }
        resp.setStatus(HttpServletResponse.SC_OK);
        Optional<User> userFromDb = UserDaoHibImpl.getUserByLoginOptional(name);
        if (userFromDb.isPresent()) {
            logger.error("Can't addUser user in database, zero rows changes after sql query");
            req.setAttribute("error", "Пользователь не был добавлен, он уже есть в базе данных");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            String salt = HashUtil.getRandomSalt();
            String password2 = HashUtil.getSHA512SecurePassword(password, salt);
            int howManyUsersChanged = UserDaoHibImpl.addUser(new User(name, password2, "s.klunniy@gmail.com", new Role("user"), salt));

            if (howManyUsersChanged == 1) {
                req.setAttribute("sessionUser", name);
//                req.setAttribute("servletContext", servletContext.getAttribute("name"));
//                session.setMaxInactiveInterval(60);
                req.setAttribute("registered", true);
                logger.debug("User with name " + name + " was registered");
            } else {
                logger.error("Can't addUser user in database, zero rows changes after sql query");
                req.setAttribute("error", "Пользователь не был добавлен");
            }
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
