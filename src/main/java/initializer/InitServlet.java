package initializer;

//import dao.HibernateStorage;
import dao.GoodDaoHibImpl;
import dao.RoleDaoHibImpl;
import dao.UserDaoHibImpl;
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
        RoleDaoHibImpl roleDao = new RoleDaoHibImpl();
        Role admin = new Role("admin");
        Role user = new Role("user");
        Role test = new Role("test");
        roleDao.addRole(admin);
        roleDao.addRole(user);
        roleDao.addRole(test);

        String salt = HashUtil.getRandomSalt();
        String pass= HashUtil.getSHA512SecurePassword("1111", salt);
        User admin1 = new User("admin", pass, "s.klunniy@gmail.com", admin, salt);

        salt = HashUtil.getRandomSalt();
        pass= HashUtil.getSHA512SecurePassword("1111", salt);
        User user1 = new User("user", pass, "s.klunniy@gmail.com", user, salt);

        salt = HashUtil.getRandomSalt();
        pass= HashUtil.getSHA512SecurePassword("1111", salt);
        User test1 = new User("test", pass , "s.klunniy@gmail.com", test, salt);

        UserDaoHibImpl.addUser(admin1);
        UserDaoHibImpl.addUser(user1);
        UserDaoHibImpl.addUser(test1);

        GoodHib goodHib = new GoodHib("пылесос", "русский", "200");
        GoodHib goodHib2 = new GoodHib("телевизор", "украинский", "500");
        GoodHib goodHib3 = new GoodHib("магнитафон", "японский", "700");
        GoodHib goodHib4 = new GoodHib("чайник", "китайский", "99");
        GoodDaoHibImpl.addGood(goodHib);
        GoodDaoHibImpl.addGood(goodHib2);
        GoodDaoHibImpl.addGood(goodHib3);
        GoodDaoHibImpl.addGood(goodHib4);
    }
}
