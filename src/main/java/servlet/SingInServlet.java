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
@WebServlet(urlPatterns = "/singIn")
public class SingInServlet extends HttpServlet {
    private UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String str="";
        User user=userManager.getUserByEmailandPassword(email,password);
        if(user!=null){
            HttpSession session=req.getSession();
            session.setAttribute("user",user);
            str="/userPage";
        }else{
            str="/loginPage";
        }
    resp.sendRedirect(str);
    }
}
