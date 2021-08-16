package servlet;

import com.mysql.cj.Session;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import userManager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/swapPhoto")
public class SwapPhotoServlet extends HttpServlet {
    private UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
        HttpSession session=req.getSession();
        User user=(User) session.getAttribute("user");
        String imageName="";
        int index=0;
        String userDirectory = new File("").getAbsolutePath();

        File file=new File(userDirectory+"\\"+"img");
        file.mkdir();
        String fileName = userManager.getPicturesName(user.getId());
        try {
            List<FileItem> multifiles=upload.parseRequest(req);
            for (FileItem item : multifiles) {
                    index = item.getName().lastIndexOf(".");
                    if (item.getName().substring(index).equals(".jpg") || item.getName().substring(index).equals(".png")) {
                        imageName = System.currentTimeMillis() + "_" + item.getName();
                        item.write(new File( file.getPath()+"\\"+ imageName));
                        break;
                    }
            }


            userManager.swapPhoto(user.getId(),imageName);
            resp.sendRedirect("/userPage");


        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
