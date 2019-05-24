package initializer;

//import dao.HibernateStorage;

import dao.*;
import dao.impl.GoodDaoImplHibImpl;
import dao.impl.RoleDaoHibImpl;
import dao.impl.UserDaoImplHibImpl;
import model.GoodHib;
import model.Role;
import model.User;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        RoleDao roleDao = new RoleDaoHibImpl();
        Role admin = new Role("admin");
        Role user = new Role("user");
        Role test = new Role("test");
        roleDao.add(admin);
        roleDao.add(user);
        roleDao.add(test);

        String salt = HashUtil.getRandomSalt();
        String pass = HashUtil.getSHA512SecurePassword("1111", salt);
        User admin1 = new User("admin", pass, "s.klunniy@gmail.com", admin, salt);

        salt = HashUtil.getRandomSalt();
        pass = HashUtil.getSHA512SecurePassword("1111", salt);
        User user1 = new User("user", pass, "s.klunniy@gmail.com", user, salt);

        salt = HashUtil.getRandomSalt();
        pass = HashUtil.getSHA512SecurePassword("1111 ", salt);
        User test1 = new User("test", pass, "s.klunniy@gmail.com", test, salt);

        UserDao userDao = new UserDaoImplHibImpl();
        userDao.add(admin1);
        userDao.add(user1);
        userDao.add(test1);

        GoodDao goodDao = new GoodDaoImplHibImpl();
        GoodHib goodHib = new GoodHib("пылесос", "русский", "200");
        GoodHib goodHib2 = new GoodHib("телевизор", "украинский", "500");
        GoodHib goodHib3 = new GoodHib("магнитафон", "японский", "700");
        GoodHib goodHib4 = new GoodHib("чайник", "китайский", "99");
        goodDao.add(goodHib);
        goodDao.add(goodHib2);
        goodDao.add(goodHib3);
        goodDao.add(goodHib4);
    }
}
