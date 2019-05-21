package initializer;

//import dao.HibernateStorage;
import dao.RoleDaoHibImpl;
import dao.UserDaoHibImpl;
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

//        HibernateStorage userDao= new HibernateStorage();

        RoleDaoHibImpl roleDao = new RoleDaoHibImpl();
        Role admin = new Role("admin");
        Role user = new Role("user");
        Role test = new Role("test");
        roleDao.addRole(admin);
        roleDao.addRole(user);
        roleDao.addRole(test);

        String salt = "salt";

        String pass= HashUtil.getSHA512SecurePassword("1111", salt);

//        String passUser = HashUtil.getSHA512SecurePassword("1111", salt);
//        String passTest = HashUtil.getSHA512SecurePassword("1111", salt);

        User admin1 = new User("admin", pass, "s.klunniy@gmail.com", admin, salt);
        User user1 = new User("user", pass, "s.klunniy@gmail.com", user, salt);
        User test1 = new User("test", pass , "s.klunniy@gmail.com", test, salt);

//        userDao.add(admin1);
//        userDao.add(user1);
//        userDao.add(test1);
        UserDaoHibImpl.add(admin1);
        UserDaoHibImpl.add(user1);
        UserDaoHibImpl.add(test1);
        System.out.println();
    }
}
