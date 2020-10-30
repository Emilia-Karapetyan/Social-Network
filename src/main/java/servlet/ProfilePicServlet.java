package servlet;

import model.User;
import userManager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/profilePic")
public class ProfilePicServlet extends HttpServlet {
    private UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        userManager.swapPhoto(user.getId(),name);
        resp.sendRedirect("/userPage");

    }
}
