//package servlet;
//
//import dao.UserDao;
//import model.User;
//import org.apache.log4j.Logger;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet(value = "/registration")
//public class RegistrationServlet extends HttpServlet {
//
//    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);
//    private static final UserDao userDao = new UserDao();
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html");
//
//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
//
//        HttpSession session = req.getSession();
//        ServletContext servletContext = req.getServletContext();
//
//        if (session.getAttribute("sessionUser") == null) {
//            session.setAttribute("sessionUser", name);
//            servletContext.setAttribute("name", name);
//        }
//
//        resp.setStatus(HttpServletResponse.SC_OK);
//
//        Optional<User> userFromDb = userDao.getUserByName(name);
//        if (userFromDb.isPresent()) {
//            logger.error("Can't add user in database, zero rows changes after sql query");
//            req.setAttribute("error", "Пользователь не был добавлен, он уже есть в базе данных");
//            req.getRequestDispatcher("index.jsp").forward(req, resp);
//        } else {
//
//            int howManyUsersChanged = userDao.addUser(new User(name, password, "s.klunniy@gmail.com", 2));
//
//            if (howManyUsersChanged == 1) {
//                req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
//                req.setAttribute("servletContext", servletContext.getAttribute("name"));
//                session.setMaxInactiveInterval(60);
//                req.setAttribute("registered", true);
//                logger.debug("User with name " + name + " was registered");
//            } else {
//                logger.error("Can't add user in database, zero rows changes after sql query");
//                req.setAttribute("error", "Пользователь не был добавлен");
//            }
//            req.getRequestDispatcher("index.jsp").forward(req, resp);
//        }
//    }
//}
