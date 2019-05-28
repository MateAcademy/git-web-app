package servlet.user;

import dao.GoodDao;
import dao.OrderDao;
import dao.UserDao;
import dao.impl.GoodDaoImplHibernate;
import dao.impl.OrderDaoImplHibernate;
import dao.impl.UserDaoImplHibernate;
import model.Good;
import model.Order;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(value = "/user/addToOrder")
public class AddToOrderServlet extends HttpServlet {

    private static final GoodDao goodDao = new GoodDaoImplHibernate();
    private static final OrderDao orderDao = new OrderDaoImplHibernate();
    private static final UserDao userDao = new UserDaoImplHibernate();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long goodId = Long.valueOf(request.getParameter("id"));
        Good good = goodDao.getById(Good.class, goodId);
        User userFromSession = (User) request.getSession().getAttribute("user");
        User userFromDb = userDao.getById(User.class, userFromSession.getId());
        Order currentUserOrder = userFromDb.getOrder();

        if (currentUserOrder == null) {
            Order order = new Order(Arrays.asList(good), userFromDb);
            orderDao.add(order);
        } else {
            currentUserOrder.getGoods().add(good);
            orderDao.update(currentUserOrder);

        }
        response.sendRedirect("/user");
//        request.getRequestDispatcher("/user").forward(request, response);
    }
}
