package ajaxResponse;

import model.Message;
import userManager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet(name = "addMessage", urlPatterns = "/addMessage")
public class AddMessageServlet extends HttpServlet {
    private MessageManager messageManager = new MessageManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int friendId = Integer.parseInt(req.getParameter("friendId"));
        int from_id = Integer.parseInt(req.getParameter("from_id"));
        String mes = req.getParameter("mes");
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        Message message = Message.builder().from_id(from_id).to_id(friendId).message(mes).date(date).time(time).build();
        messageManager.addMessage(message);

    }
}
