//package servlet;
//
//import dao.UserDao;
//import model.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet(value = "/IdentificationServlet")
//public class JoinServlet extends HttpServlet {
//
//    private static final UserDao userDao = new UserDao();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html");       //даем понять браузеру что ему приходит не картинка не видео не джейсон
//        resp.setCharacterEncoding("UTF-8");
//
//        String firstName = req.getParameter("name2");
//        String login = req.getParameter("login2");
//        System.out.println("мы заходим на сайт под именем:  " + firstName + " и паролем:  " + login);
//
//        User user = new User(firstName, login);
//
//        boolean doesUserExist = userDao.getUser(user);
//
//        if (doesUserExist == true) {
//            req.setAttribute("login", login);
//            req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
//            req.getRequestDispatcher("SignIn.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("IncorrectLogPass.jsp").forward(req, resp);
//        }
//    }
//}
