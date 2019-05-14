package servlet.user;

import dao.UserDao;
import org.apache.log4j.Logger;
import servlet.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/users")
public class AllUsersServlet extends HttpServlet {

//TODO: этот сервлет я пока не спользую нигде!

    private static final Logger logger = Logger.getLogger(AllUsersServlet.class);
    private static final UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");


        logger.debug("user list getting");
        request.setAttribute("users", userDao.getAllUsers());

        request.getRequestDispatcher("/users/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
