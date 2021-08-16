package userManager;

import dbConnection.DBConnection;
import model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private Connection connection;
    public MessageManager(){
        connection= DBConnection.getInstance().getConnection();
    }
    public void addMessage(Message message){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into message (fromId,toId,message,date,time) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,message.getFrom_id());
            preparedStatement.setInt(2,message.getTo_id());
            preparedStatement.setString(3,message.getMessage());
            preparedStatement.setDate(4,message.getDate());
            preparedStatement.setTime(5,message.getTime());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                message.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Message> printMessage(int from_id, int to_id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM message WHERE message.fromId=? AND message.toId=? OR message.toId=? AND message.fromId=? ORDER BY TIME");
            preparedStatement.setInt(1,from_id);
            preparedStatement.setInt(2,to_id);
            preparedStatement.setInt(3,from_id);
            preparedStatement.setInt(4,to_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Message> messages = new ArrayList<>();
            while (resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setFrom_id(resultSet.getInt(2));
                message.setTo_id(resultSet.getInt(3));
                message.setMessage(resultSet.getString(4));
                message.setDate(resultSet.getDate(5));
                message.setTime(resultSet.getTime(6));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
