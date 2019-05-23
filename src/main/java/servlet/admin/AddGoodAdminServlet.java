package servlet.admin;

import dao.GoodDaoJdbc;
import model.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/addGoodAdminServlet")
public class AddGoodAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double cost = Double.valueOf(request.getParameter("cost"));

        GoodDaoJdbc goodDaoJdbc = new GoodDaoJdbc();
        goodDaoJdbc.addGood(new Good(name, description, cost));

        List<Good> allGoods = goodDaoJdbc.getAllGoods();
        request.setAttribute("goods", allGoods);
        request.getRequestDispatcher("admin/goodsPageForAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
