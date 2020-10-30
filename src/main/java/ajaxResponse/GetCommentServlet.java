package ajaxResponse;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import model.Comment;
import userManager.CommentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(name = "getComment",urlPatterns = "/getComment")
public class GetCommentServlet extends HttpServlet {
        private CommentManager commentManager=new CommentManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("y"));
        String lastMsg=req.getParameter("msg");
            resp.setContentType("text/plain");
            resp.getWriter().write(lastMsg);
       // resp.setContentType("application/json");


    }
}
