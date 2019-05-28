package servlet.admin;

import dao.impl.GoodDaoImplHibernate;
import model.Good;
import dao.GoodDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/admin/redactGood")
public class RedactGoodServlet extends HttpServlet {

    GoodDao goodDao = new GoodDaoImplHibernate();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.valueOf(request.getParameter("id"));

//        GoodDaoJdbc goodDao = new GoodDaoJdbc();
        Optional<Good> goodOptional = goodDao.getGoodByIdOptional(id);

       // System.out.println(good);

        if (goodOptional.isPresent()) {
            Good good = goodOptional.get();
            request.setAttribute("id", good.getId());
            request.setAttribute("name", good.getName());
            request.setAttribute("description", good.getDescription());
            request.setAttribute("price", good.getPrice());
            request.getRequestDispatcher("/admin/redactGood.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
