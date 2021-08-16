package ajaxResponse;

import com.google.gson.JsonObject;
import model.Message;
import userManager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/getMessage",urlPatterns = "/getMessage")
public class GetMessage extends HttpServlet {
    private MessageManager messageManager=new MessageManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int from_id= Integer.parseInt(req.getParameter("fromId"));
        int to_id= Integer.parseInt(req.getParameter("toId"));
        List<Message> list=messageManager.printMessage(from_id,to_id);
        String s="";
        for (Message message : list) {

            String str=message.getMessage()+","+message.getDate()+","+message.getFrom_id();
            s+=str+":";
        }
        resp.setContentType("text/plain");
        resp.getWriter().write(s);
    }
}
