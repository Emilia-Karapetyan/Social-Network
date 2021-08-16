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
@WebServlet(urlPatterns = "/guestServlet")
public class GuestServlet extends HttpServlet {
    public static User temp=null;
    private UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str="";
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
        int id= Integer.parseInt(req.getParameter("id"));
        User guest = userManager.getUserById(id);
        if(guest.getId()==user.getId()){
            str="/userPage";
        }else{
            str="/guest";
            temp=guest;
        }
        resp.sendRedirect(str);
    }
}
