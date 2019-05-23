package servlet.admin;

import dao.UserDaoHibImpl;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<User> userFromDb = UserDaoHibImpl.getUserByLoginOptional(name); //.getUserByName(name);

        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
            String hashPasswordFromForm = HashUtil.getSHA512SecurePassword(password, user.getSalt());
            UserDaoHibImpl.updatePassword(user, hashPasswordFromForm);
            List<User> list = UserDaoHibImpl.getAllUsers();
            request.setAttribute("users", list);
            request.getRequestDispatcher("/admin/usersEditDelete.jsp").forward(request, response);
        }
    }
}
