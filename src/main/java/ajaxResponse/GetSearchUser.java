package ajaxResponse;

import com.google.protobuf.ByteString;
import model.User;
import userManager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "searchUser",urlPatterns = "/searchUser")
public class GetSearchUser extends HttpServlet {
    private UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list=userManager.getAllUser();
        String str=req.getParameter("str");
        boolean f=false;
        String []arr=new String[2];
        if(str.contains(" ")){
            arr=str.split(" ");
            f=true;
        }

        if(!f) {
            for (User user : list) {
                if (user.getName().toLowerCase().contains(str.toLowerCase()) || user.getSurname().toLowerCase().contains(str.toLowerCase())) {
                    String s = user.getName() + " " + user.getSurname() + "/" + user.getId() + ",";
                    resp.setContentType("text/plain");
                    resp.getWriter().write(s);
                }
            }
        }else{

            for (User user : list) {
                if (user.getName().toLowerCase().contains(arr[0].toLowerCase()) || user.getSurname().toLowerCase().contains(arr[1].toLowerCase())) {
                    String s = user.getName() + " " + user.getSurname() + "/" + user.getId() + ",";
                    resp.setContentType("text/plain");
                    resp.getWriter().write(s);
                }
            }
        }
    }
}
