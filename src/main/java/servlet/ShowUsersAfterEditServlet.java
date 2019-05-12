package servlet;

import dao.UserDao;
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

@WebServlet(name = "ShowUsersAfterEditServlet", value = "/ShowUsersAfterEdit")
public class ShowUsersAfterEditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();

        Optional<User> userFromDb =  userDao.getUserByName(name);
//если есть то делаем хеш код из пароля что мы ввели и соли из б/д
        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
            String hashPasswordFromForm = HashUtil.getSHA512SecurePassword(password, user.getSalt());
//сравниваем хеш из БД и тот что мы сделали

        userDao.editUser(name, hashPasswordFromForm );
        List<User> list = userDao.getUsers();

        request.setAttribute("users", list);
        request.getRequestDispatcher("EditDelete.jsp").forward(request, response);
        }
    }
}
