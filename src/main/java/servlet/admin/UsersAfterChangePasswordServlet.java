package servlet.admin;

import dao.UserDao;
import dao.impl.UserDaoImplHibImpl;
import model.User;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/admin/usersAfterChange")
public class UsersAfterChangePasswordServlet extends HttpServlet {

    UserDao userDao = new UserDaoImplHibImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<User> userFromDb = userDao.getUserByNameOptional(name); //.getUserByName(name);

        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
            String hashPasswordFromForm = HashUtil.getSHA512SecurePassword(password, user.getSalt());
            userDao.updatePassword(user, hashPasswordFromForm);
//          List<User> list = userDao.getAllUsers();
            List<User> list = userDao.getAll(User.class);
            request.setAttribute("users", list);
            request.getRequestDispatcher("/admin/usersEditDelete.jsp").forward(request, response);
        }
    }
}
