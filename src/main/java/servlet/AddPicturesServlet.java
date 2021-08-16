package servlet;

import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import userManager.PhotoManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addPictures")
public class AddPicturesServlet extends HttpServlet {
    private PhotoManager photoManager=new PhotoManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
        String imageName="";
        int index=0;
        String userDirectory = new File("").getAbsolutePath();

        File file=new File(userDirectory+"\\"+"img");
        file.mkdir();
        try {
            List<FileItem> multifiles=upload.parseRequest(req);
            for (FileItem item : multifiles) {
                index=item.getName().lastIndexOf(".");
                if(item.getName().substring(index).equals(".jpg") || item.getName().substring(index).equals(".png")) {
                    imageName = System.currentTimeMillis() + "_" + item.getName();
                    item.write(new File(file.getPath()+"\\"+ imageName));
                    break;
                }
            }
            HttpSession session=req.getSession();
            User user=(User) session.getAttribute("user");
            photoManager.addPictures(user.getId(),imageName);
            resp.sendRedirect("/myPicturesServlet");


        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
