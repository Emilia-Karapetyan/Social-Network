package userManager;

import dbConnection.DBConnection;
import model.Comment;
import model.User;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {
    private Connection connection;
    public CommentManager(){
        connection= DBConnection.getInstance().getConnection();
    }

    public void addComment(Comment comment){
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement("insert into commentpost (comment,postId,userId) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,comment.getComment());
            statement.setInt(2,comment.getPost_id());
            statement.setInt(3,comment.getUser_id());
            statement.executeUpdate();

            ResultSet resultSet=statement.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                comment.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getComment(){
        PreparedStatement statement=null;
        List<Comment> list=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT * from  commentpost");
            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                Comment comment=new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPost_id(resultSet.getInt("postId"));
                comment.setUser_id(resultSet.getInt("userId"));
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Comment> getComment(int id){
        PreparedStatement statement=null;
        List<Comment> list=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT * from  commentpost where postId=?");
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                Comment comment=new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPost_id(resultSet.getInt("postId"));
                comment.setUser_id(resultSet.getInt("userId"));
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public User getUserByPost(int id){
        PreparedStatement statement=null;
        User user=new User();

        try {
            statement=connection.prepareStatement("SELECT DISTINCT user.* FROM commentpost INNER JOIN USER ON user.id=commentpost.userId where postId=?");
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPicURL(resultSet.getString("picURL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
/*    public List<Integer> getUserId(){
        PreparedStatement statement=null;
        List<Integer> list=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT commentpost.userId AS  userId FROM commentpost");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }*/
}
