package servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/editPassword")
public class EditPasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");

        request.setAttribute("name", name);
        request.getRequestDispatcher("/admin/usersEditPassword.jsp").forward(request, response);
    }
}
