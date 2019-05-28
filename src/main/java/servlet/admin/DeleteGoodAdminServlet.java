package servlet.admin;

import dao.GoodDao;
import dao.impl.GoodDaoImplHibernate;
import model.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/deleteGoods")
public class DeleteGoodAdminServlet extends HttpServlet {

    private GoodDao goodDao = new GoodDaoImplHibernate();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer code = Integer.valueOf(id);

//        GoodDaoJdbc goodDao = new GoodDaoJdbc();
//        goodDao.delGood(code);
        Good good = goodDao.getById(Good.class, code);
        goodDao.delete(good);

//        List<Good> allGoods = goodDao.getAllGoods();
//        List<Good> allGoods = goodDao.getAllGoods();
        List<Good> allGoods = goodDao.getAll(Good.class);

        request.setAttribute("goods", allGoods);
        request.getRequestDispatcher("/admin/goodsPageForAdmin.jsp").forward(request, response);
    }
}
