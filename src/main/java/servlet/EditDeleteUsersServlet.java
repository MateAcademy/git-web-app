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

@WebServlet(value = "/editDeleteUsersServlet")

public class EditDeleteUsersServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//Этот сервлет берет юзеров из базы данных и передает на usersEditDelete.jsp
        UserDao userDao = new UserDao();
        List<User> list = userDao.getAllUsers();
        request.setAttribute("users", list);
        request.getRequestDispatcher("usersEditDelete.jsp").forward(request, response);
    }
}
