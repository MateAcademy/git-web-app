package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //пишу код для получения всех юзеров из бд и передаю его на вьюху
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

//        User user = (User)req.getSession().getAttribute("user");
//        if (user!=null && user.getRole().equals("admin")) {
//        resp.getWriter().print("Здесь вывелись все юзеры");
            req.getRequestDispatcher("adminPage.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("accessDenied.jsp").forward(req, resp);
//
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
