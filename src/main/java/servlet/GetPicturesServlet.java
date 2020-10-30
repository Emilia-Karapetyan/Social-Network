package servlet;


import model.User;
import userManager.UserManager;

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


@WebServlet(urlPatterns = "/getPictures")
public class GetPicturesServlet extends HttpServlet {
    private UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
        String userDirectory = new File("").getAbsolutePath();
        File file=new File(userDirectory+"\\"+"img");
        file.mkdir();
        String fileName = userManager.getPicturesName(user.getId());
        if(fileName==null){
            fileName="user.png";
        }
        if(fileName.isEmpty()){
            fileName="user.png";
        }

        OutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(file.getPath()+"\\"+ fileName);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}
