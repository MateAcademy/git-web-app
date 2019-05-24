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

@WebServlet(value = "/addGoodAdminServlet")
public class AddGoodAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String cost = request.getParameter("cost");

//        GoodDaoJdbc goodDaoJdbc = new GoodDaoJdbc();
//        goodDaoJdbc.addGood(new Good(name, description, cost));
//        List<Good> allGoods = goodDaoJdbc.getAllGoods();

        GoodDao goodDao = new GoodDaoImplHibImpl();
        goodDao.add(new GoodHib(name, description, cost));
        List<GoodHib> allGoods = goodDao.getAll(GoodHib.class);

        request.setAttribute("goods", allGoods);
        request.getRequestDispatcher("admin/goodsPageForAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
