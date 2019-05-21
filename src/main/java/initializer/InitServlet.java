package initializer;

//import dao.HibernateStorage;
import dao.GoodDaoHibImpl;
import dao.RoleDaoHibImpl;
import dao.UserDaoHibImpl;
import model.GoodHib;
import model.Role;
import model.User;
import utils.HashUtil;

import javax.management.relation.RoleInfo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        RoleDaoHibImpl roleDao = new RoleDaoHibImpl();
        Role admin = new Role("admin");
        Role user = new Role("user");
        Role test = new Role("test");
        roleDao.addRole(admin);
        roleDao.addRole(user);
        roleDao.addRole(test);

        String salt = "salt";

        String pass= HashUtil.getSHA512SecurePassword("1111", salt);

        User admin1 = new User("admin", pass, "s.klunniy@gmail.com", admin, salt);
        User user1 = new User("user", pass, "s.klunniy@gmail.com", user, salt);
        User test1 = new User("test", pass , "s.klunniy@gmail.com", test, salt);

        UserDaoHibImpl.add(admin1);
        UserDaoHibImpl.add(user1);
        UserDaoHibImpl.add(test1);

        GoodHib goodHib = new GoodHib("пылесос", "русский", "200");
        GoodHib goodHib2 = new GoodHib("телевизор", "украинский", "500");
        GoodHib goodHi3 = new GoodHib("магнитафон", "японский", "700");
        GoodDaoHibImpl.addGood(goodHib);
        GoodDaoHibImpl.addGood(goodHib2);
        GoodDaoHibImpl.addGood(goodHi3);
        GoodDaoHibImpl.addGood(goodHib2);
    }
}
