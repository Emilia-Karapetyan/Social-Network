package ajaxResponse;

import model.User;
import userManager.CommentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "getUserName",urlPatterns = "/getUserName" )
public class GetUserName extends HttpServlet {
    private CommentManager commentManager=new CommentManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("xxx");
        int post_id=Integer.parseInt(req.getParameter("post_id"));
        User user= (User) commentManager.getComment(post_id);
        resp.setContentType("text/plain");
        resp.getWriter().write(user.getName()+" "+user.getSurname());
    }
}
