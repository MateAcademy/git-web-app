package servlet.admin;

import dao.UserDao;
import dao.impl.UserDaoImplHibernate;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/editDeleteUsersServlet")
public class EditDeleteUsersServlet extends HttpServlet {

    private UserDao userDao = new UserDaoImplHibernate();

    final static Logger logger = Logger.getLogger(EditDeleteUsersServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("user list getting");
//        request.setAttribute("users", userDao.getAllUsers());
//        request.getRequestDispatcher("admin/usersEditDelete.jsp").forward(request, response);
//Этот сервлет берет юзеров из базы данных и передает на usersEditDelete.jsp, внизу код который работал:
//        UserDaoJdbc userDao = new UserDaoJdbc();
//        List<User> list = userDao.getAllUsers();
        List<User> list = userDao.getAll(User.class);

        request.setAttribute("users", list);
        request.getRequestDispatcher("/admin/usersEditDelete.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
