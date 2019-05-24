package servlet.admin;

import dao.GoodDao;
import dao.impl.GoodDaoImplHibImpl;
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

    private GoodDao goodDao = new GoodDaoImplHibImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");

        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String cost = request.getParameter("cost");

//        GoodDaoJdbc goodDao = new GoodDaoJdbc();
//        goodDao.addGood(new Good(name, description, cost));

        GoodHib good = goodDao.getById(GoodHib.class, id);
//      GoodHib good = goodDao.getGoodById(id);
        good.setName(name);
        good.setDescription(description);
        good.setPrice(cost);

        goodDao.update(good);

//      List<GoodHib> allGoods = goodDao.getAllGoods();
        List<GoodHib> allGoods = goodDao.getAll(GoodHib.class);
        request.setAttribute("goods", allGoods);
        request.getRequestDispatcher("/admin/goodsPageForAdmin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
