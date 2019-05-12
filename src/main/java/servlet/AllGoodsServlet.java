package servlet;

import dao.GoodDao;
import model.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/goods")
public class AllGoodsServlet extends HttpServlet {

    private static final GoodDao goodDao = new GoodDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Good> allGoods = goodDao.getAllGoods();
        request.setAttribute("goods", allGoods);

        HttpSession session = request.getSession();
        if (session.getAttribute("sessionUser").equals("admin")) {
            request.getRequestDispatcher("allGoodsPageForAdmin.jsp").forward(request, response);
        }
        request.getRequestDispatcher("allGoodsPage.jsp").forward(request, response);
    }
}
