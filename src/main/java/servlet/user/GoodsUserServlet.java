package servlet.user;

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

@WebServlet(value = "/user")
public class GoodsUserServlet extends HttpServlet {

    GoodDao goodDao = new GoodDaoImplHibernate();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Good> allGoods = goodDao.getAll(Good.class);
        request.setAttribute("goods", allGoods);
        request.getRequestDispatcher("/users/allGoodsPage.jsp").forward(request, response);
    }
}
