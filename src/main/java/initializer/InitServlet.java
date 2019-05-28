package initializer;

//import dao.HibernateStorage;

import dao.*;
import dao.impl.GoodDaoImplHibernate;
import dao.impl.OrderDaoImplHibernate;
import dao.impl.RoleDaoImplHibernate;
import dao.impl.UserDaoImplHibernate;
import model.Good;
import model.Order;
import model.Role;
import model.User;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        OrderDao orderDao = new OrderDaoImplHibernate();
        RoleDao roleDao = new RoleDaoImplHibernate();
        Role admin = new Role("admin");
        Role user = new Role("user");
        Role test = new Role("test");
        roleDao.add(admin);
        roleDao.add(user);
        roleDao.add(test);

        String salt1 = HashUtil.getRandomSalt();
        String pass = HashUtil.getSHA512SecurePassword("1111", salt1);
        User sergey = new User("admin", pass, "s.klunniy@gmail.com", admin, salt1);

        String salt2 = HashUtil.getRandomSalt();
        pass = HashUtil.getSHA512SecurePassword("1111", salt2);
        User dima = new User("user", pass, "s.klunniy@gmail.com", user, salt2);

        String salt3 = HashUtil.getRandomSalt();
        pass = HashUtil.getSHA512SecurePassword("1111", salt3);
        User german = new User("test", pass, "s.klunniy@gmail.com", test, salt3);

        UserDao userDao = new UserDaoImplHibernate();
        userDao.add(sergey);
        userDao.add(dima);
        userDao.add(german);

        GoodDao goodDao = new GoodDaoImplHibernate();
        Good good1 = new Good("пылесос", "русский", "2500");
        Good good2 = new Good("телевизор", "украинский", "15000");
        Good good3 = new Good("магнитафон", "японский", "1700");
        Good good4 = new Good("чайник", "китайский", "800");
        Good good5 = new Good("монитор", "PHILIPS", "5500");
        goodDao.add(good1);
        goodDao.add(good2);
        goodDao.add(good3);
        goodDao.add(good4);
        goodDao.add(good5);

        List<Good> goods = new ArrayList<>();
        goods.add(good1);
        goods.add(good2);
        Order order = new Order(goods, dima );
        orderDao.add(order);
        System.out.println(orderDao.getAll(Order.class));
    }
}
