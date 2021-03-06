package servlet;

import model.User;
import userManager.FriendManager;
import userManager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/sendRequest")
public class SendRequestServlet extends HttpServlet {
    private FriendManager friendManager=new FriendManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        HttpSession session=req.getSession();
        User user = (User) session.getAttribute("user");
        friendManager.addFriend(user.getId(),id);
        resp.sendRedirect("/allUserServlet");
    }
}
