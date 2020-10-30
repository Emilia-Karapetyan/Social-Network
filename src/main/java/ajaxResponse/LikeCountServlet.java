package ajaxResponse;

import userManager.LikeManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "countServlet", urlPatterns = "/countServlet")
public class LikeCountServlet extends HttpServlet {
    private LikeManager likeManager=new LikeManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int c=Integer.parseInt(req.getParameter("count"));
        int g=likeManager.countLike(c);
        String greetings = String.valueOf(g);
        resp.setContentType("text/plain");
        resp.getWriter().write(greetings);
    }
}
