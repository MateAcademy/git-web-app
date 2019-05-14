package servlet.admin;

import dao.GoodDao;
import model.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/redactGood")
public class RedactGoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        Long id = Long.valueOf(request.getParameter("id"));

        GoodDao goodDao = new GoodDao();
        Optional<Good> goodOptional = goodDao.getGoodById(id);

       // System.out.println(good);

        if (goodOptional.isPresent()) {
            Good good = goodOptional.get();
            request.setAttribute("id", good.getId());
            request.setAttribute("name", good.getName());
            request.setAttribute("description", good.getDescription());
            request.setAttribute("price", good.getPrice());
            request.getRequestDispatcher("admin/redactGood.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
