package servlet.admin;

import dao.UserDaoHibImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/delete")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        long  id = Long.parseLong(req.getParameter("id"));

        UserDaoHibImpl.delete(id);

        List<User> list = UserDaoHibImpl.getAllUsers();

        req.setAttribute("users", list);
        req.getRequestDispatcher("/admin/usersEditDelete.jsp").forward(req, resp);
    }
}
