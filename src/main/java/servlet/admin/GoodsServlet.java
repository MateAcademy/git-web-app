package servlet.admin;

import dao.GoodDao;
import dao.impl.GoodDaoImplHibImpl;
import model.GoodHib;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/goods")
public class GoodsServlet extends HttpServlet {

    private GoodDao goodDao = new GoodDaoImplHibImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       List<GoodHib> allGoods = goodDao.getAllGoods();
        List<GoodHib> allGoods = goodDao.getAll(GoodHib.class);
        request.setAttribute("goods", allGoods);

        HttpSession session = request.getSession();
        if (session.getAttribute("sessionUser").equals("admin")) {
            request.getRequestDispatcher("/admin/goodsPageForAdmin.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/users/allGoodsPage.jsp").forward(request, response);
    }
}
