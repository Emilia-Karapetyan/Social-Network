package method;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import model.User;
import userManager.CommentManager;
import userManager.UserManager;

import java.util.List;

@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class Controller {
    private static CommentManager commentManager=new CommentManager();
    private static UserManager userManager=new UserManager();
    private static User user=null;
    public static  User getCommentWriter(int id){
        List<User> users=userManager.getAllUser();
        for (User u : users) {
            if(u.getId()==id){
                user=u;
                break;
            }
        }
        return user;
    }
}
