package ajaxResponse;

import model.User;
import userManager.LikeManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addLike",urlPatterns = "/addLike")
public class AddLikeServlet extends HttpServlet {
    private LikeManager likeManager = new LikeManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int id = Integer.parseInt(req.getParameter("p"));
        likeManager.like(id,user.getId());

    }
}
