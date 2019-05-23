package servlet.admin;

import dao.GoodDaoHibImpl;
import model.GoodHib;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/deleteGoods")
public class DeleteGoodAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer code = Integer.valueOf(id);

//        GoodDaoJdbc goodDao = new GoodDaoJdbc();
//        goodDao.delGood(code);

        GoodDaoHibImpl.deleteGood(code);

//        List<Good> allGoods = goodDao.getAllGoods();
        List<GoodHib> allGoods = GoodDaoHibImpl.getAllGoods();


        request.setAttribute("goods", allGoods);
        request.getRequestDispatcher("/admin/goodsPageForAdmin.jsp").forward(request, response);
    }
}
