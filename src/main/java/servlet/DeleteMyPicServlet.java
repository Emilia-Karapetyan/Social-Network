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
@WebServlet(urlPatterns = "/deleteMyPic")
public class DeleteMyPicServlet extends HttpServlet {
    private PhotoManager photoManager=new PhotoManager();
    private UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delPic=req.getParameter("delPic");
        HttpSession session=req.getSession();
        User user= (User)session.getAttribute("user");

        String userDirectory = new File("").getAbsolutePath();

        File file=new File(userDirectory+"\\"+"img"+"\\"+delPic);
        if(file.delete()){
            photoManager.deletePic(delPic);
            if(delPic.equals(userManager.getPicturesName(user.getId()))){
                userManager.swapPhoto(user.getId(),"");
            }
            resp.sendRedirect("/myPicturesServlet");
        }else {
            photoManager.deletePic(delPic);
            resp.sendRedirect("/myPicturesServlet");
        }
    }
}
