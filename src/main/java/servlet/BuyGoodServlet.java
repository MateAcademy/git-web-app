package servlet;

import dao.CodeDao;
import model.Code;
import model.User;
import service.MailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/buy")
public class BuyGoodServlet extends HttpServlet {

    private static  MailService mailService = new MailService();
    private static final CodeDao codeDao = new CodeDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        Long goodId = Long.valueOf(request.getParameter("good_id"));
        String codeValue = request.getParameter("code");
        User user = (User) request.getSession().getAttribute("user");
        Code code = new Code(codeValue, user.getId(), goodId);

        if (codeDao.checkCode(code)) {
            response.getWriter().print("Оплата прошла");
        } else {
            response.getWriter().print("Оплата отклонена");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       Long goodId = (Long)request.getAttribute("id");
       Long goodId = Long.parseLong(request.getParameter("id"));
       User user = (User) request.getSession().getAttribute("user");
       String randomeCode = mailService.sendEmailWithCode(user.getEmail());
       Code code = new Code(randomeCode, user.getId(), goodId);
       codeDao.addCode(code);
        request.setAttribute("good_id", goodId);
        request.getRequestDispatcher("buyConfirmation.jsp").forward(request, response);
    }
}
