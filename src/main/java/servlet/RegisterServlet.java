package servlet;

import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import userManager.UserManager;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

@WebServlet(urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserManager userManager=new UserManager();
    public  static String isConfirm="";
    public  static String isContain="";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        isConfirm="";
        isContain="";
        String str="";
        String name=req.getParameter("name");
        String surname=req.getParameter("surname");
        int age= Integer.parseInt(req.getParameter("age"));
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String confPass=req.getParameter("confPass");
 /*       String picURL="user.png";*/

        if(userManager.containUser(email)){
            isContain="Email already exist";
            str="/confirmReg";
        }
        if(!password.equals(confPass)){
            isConfirm="Password is not correct";
            str="/confirmReg";
        }

        if(password.equals(confPass) && !userManager.containUser(email)){
            User user=User.builder().name(name).surname(surname).age(age).email(email).password(password).build();
            userManager.addUser(user);
            str="/loginPage";
        }
        resp.sendRedirect(str);
    }
}
