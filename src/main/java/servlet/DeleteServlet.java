package servlet;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();
        User user = new User(name, password);
        userDao.delUser(user);

        List<User> list = userDao.getAllUsers();

        req.setAttribute("users", list);
        req.getRequestDispatcher("usersEditDelete.jsp").forward(req, resp);
    }
}
