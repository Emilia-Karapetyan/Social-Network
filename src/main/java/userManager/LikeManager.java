package userManager;

import dbConnection.DBConnection;

import java.sql.*;

public class LikeManager  {
    private Connection connection;

    public LikeManager(){
        connection= DBConnection.getInstance().getConnection();
    }
    public void like(int post_id,int user_id){
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement("Call addLike(?,?)");
            statement.setInt(1,user_id);
            statement.setInt(2,post_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int countLike(int id){
        PreparedStatement statement=null;
        int count=0;
        try {
            statement=connection.prepareStatement("SELECT COUNT(*) AS `count` FROM `like` WHERE postId=?");
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                count=resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
