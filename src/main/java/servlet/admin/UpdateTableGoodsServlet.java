package servlet.admin;

import dao.GoodDao;
import dao.GoodDaoHibImpl;
import model.Good;
import model.GoodHib;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/updateTableGoods")
public class UpdateTableGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");

        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String cost = request.getParameter("cost");

//        GoodDao goodDao = new GoodDao();
 //       goodDao.addGood(new Good(name, description, cost));
GoodHib good = GoodDaoHibImpl.getGoodById(id);
      good.setName(name);
      good.setDescription(description);
      good.setPrice(cost);

        GoodDaoHibImpl.updateGood(good);

        List<GoodHib> allGoods = GoodDaoHibImpl.getAllGoods();
        request.setAttribute("goods", allGoods);
        request.getRequestDispatcher("/admin/goodsPageForAdmin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
