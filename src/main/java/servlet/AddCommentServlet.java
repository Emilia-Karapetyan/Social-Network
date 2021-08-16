package servlet;

import model.Comment;
import model.User;
import userManager.CommentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "addComment" , urlPatterns = "/addComment")
public class AddCommentServlet extends HttpServlet {
    private CommentManager commentManager=new CommentManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
        String comment=req.getParameter("comment");
        int id=Integer.parseInt(req.getParameter("id"));

        Comment comment1=Comment.builder().comment(comment).post_id(id).user_id(user.getId()).build();
        commentManager.addComment(comment1);
        resp.sendRedirect("/postServlet");
    }
}
