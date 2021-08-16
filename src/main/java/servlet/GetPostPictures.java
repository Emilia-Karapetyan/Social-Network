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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(urlPatterns = "/getPostPic")
public class GetPostPictures extends HttpServlet {
    private PostManager postManager=new PostManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pic= req.getParameter("pic");

        String userDirectory = new File("").getAbsolutePath();
        File file = new File(userDirectory + "\\" + "postImage");
        file.mkdir();


        OutputStream out = resp.getOutputStream();

        FileInputStream in = new FileInputStream(file.getPath() + "\\" + pic);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}
