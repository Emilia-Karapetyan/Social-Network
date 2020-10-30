package servlet;

import model.Post;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import userManager.PostManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addPostServlet")
public class AddPostServlet extends HttpServlet {
    private PostManager postManager=new PostManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        String text = "";
        String imageName = "";
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        String directory = new File("").getAbsolutePath();
        File file = new File(directory + "\\" + "postImage");
        file.mkdir();
        try {
            List<FileItem> multifiles = servletFileUpload.parseRequest(req);

            for (FileItem fileItem : multifiles) {
                if (fileItem.isFormField()) {
                    if (fileItem.getFieldName().equals("text")) {
                        text = fileItem.getString();
                    }
                }
                 else {
                    imageName = System.currentTimeMillis() + "_" + fileItem.getName();
                    fileItem.write(new File(file.getPath() + "\\" + imageName));
                }
            }
            Post post=Post.builder().description(text).picURL(imageName).user_id(user.getId()).build();
            postManager.addPost(post);
            resp.sendRedirect("/postServlet");

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
