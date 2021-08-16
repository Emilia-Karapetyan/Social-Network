package servlet;

import model.User;
import userManager.PhotoManager;
import userManager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
@WebServlet(urlPatterns = "/deletePicturesServlet")
public class DeletePicturesServlet extends HttpServlet {
    private UserManager userManager=new UserManager();
    private PhotoManager photoManager=new PhotoManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        User user= (User)session.getAttribute("user");
        String picName=userManager.getPicturesName(user.getId());
        String userDirectory = new File("").getAbsolutePath();

        File file=new File(userDirectory+"\\"+"img"+"\\"+picName);
        if(file.delete()){
            userManager.swapPhoto(user.getId(),"");
            photoManager.deletePic(picName);
            resp.sendRedirect("/userPage");
        }else {
            photoManager.deletePic(picName);
            resp.sendRedirect("/userPage");
        }

    }
}
