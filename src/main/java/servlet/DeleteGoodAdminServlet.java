package servlet;

import dao.GoodDao;
import model.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteGoodAdminServlet", value = "/deleteGoods")
public class DeleteGoodAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer code = Integer.valueOf(id);

        GoodDao goodDao = new GoodDao();
        goodDao.delGood(code);

        List<Good> allGoods = goodDao.getAllGoods();

        request.setAttribute("goods", allGoods);
        request.getRequestDispatcher("allGoodsPageForAdmin.jsp").forward(request, response);
    }
}
