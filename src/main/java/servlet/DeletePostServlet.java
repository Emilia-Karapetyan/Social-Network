package servlet;

import model.User;
import userManager.PostManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
@WebServlet(urlPatterns = "/deletePost")
public class DeletePostServlet extends HttpServlet {
    private PostManager postManager=new PostManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        String pic=postManager.getPicByPostId(id);
        String userDirectory = new File("").getAbsolutePath();

        File file=new File(userDirectory+"\\"+"postImage"+"\\"+pic);
        if(file.delete()){
            postManager.deletePostById(id);
            resp.sendRedirect("/postServlet");

        }else {
            resp.sendRedirect("/postServlet");
        }

    }
}
