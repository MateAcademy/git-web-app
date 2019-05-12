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

@WebServlet(name = "AddGoodAdminServlet", value = "/addGood")
public class AddGoodAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double cost = Double.valueOf(request.getParameter("cost"));

        GoodDao goodDao = new GoodDao();
        goodDao.addGood(new Good(name, description, cost));


        List<Good> allGoods = goodDao.getAllGoods();
        request.setAttribute("goods", allGoods);

        request.getRequestDispatcher("allGoodsPageForAdmin.jsp").forward(request, response);


    }

}
