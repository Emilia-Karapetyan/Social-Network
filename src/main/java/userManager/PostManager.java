package userManager;

import dbConnection.DBConnection;
import model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostManager {
    private Connection connection;
    public PostManager(){
        connection= DBConnection.getInstance().getConnection();
    }

    public void addPost(Post post){
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement("Insert into post(description,picURl,userId) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,post.getDescription());
            statement.setString(2,post.getPicURL());
            statement.setInt(3,post.getUser_id());
            statement.executeUpdate();

            ResultSet resultSet=statement.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                post.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletePostById(int id){
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement("Delete from post where id=?");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Post> getPostByUserId(int id){
        PreparedStatement statement=null;
        List<Post> list=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT * from post where userId=?");
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Post post=new Post();
                post.setId(resultSet.getInt("id"));
                post.setDescription(resultSet.getString("description"));
                post.setPicURL(resultSet.getString("picURL"));
                post.setUser_id(resultSet.getInt("userId"));
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public String getPicByPostId(int id){
        PreparedStatement statement=null;
        String str="";
        try {
            statement=connection.prepareStatement("SELECT picURL from post where id=?");
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                str=resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }

    public List<Post> myFriendPost(int id){
        PreparedStatement statement=null;
        List<Post> list=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT post.* FROM post INNER JOIN friend ON post.userId=friend.toId AND friend.bool=1 AND friend.fromId=?");
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Post post=new Post();
                post.setId(resultSet.getInt("id"));
                post.setDescription(resultSet.getString("description"));
                post.setPicURL(resultSet.getString("picURL"));
                post.setUser_id(resultSet.getInt("userId"));
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Post> myFriendPost1(int id){
        PreparedStatement statement=null;
        List<Post> list=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT post.* FROM post INNER JOIN friend ON post.userId=friend.fromId AND friend.bool=1 AND friend.toId=?");
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Post post=new Post();
                post.setId(resultSet.getInt("id"));
                post.setDescription(resultSet.getString("description"));
                post.setPicURL(resultSet.getString("picURL"));
                post.setUser_id(resultSet.getInt("userId"));
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
