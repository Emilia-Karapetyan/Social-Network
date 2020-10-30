package userManager;

import dbConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotoManager {
    private Connection connection;
    public PhotoManager(){
        connection= DBConnection.getInstance().getConnection();
    }
    public void addPictures(int id,String url){
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement("Insert into usersphoto (userId,picURL) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,url);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> getAllPictures (int id) {
        PreparedStatement preparedStatement=null;
        List<String> list=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement("SELECT DISTINCT  picURL FROM usersphoto WHERE userId=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void deletePic(String url){
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement("Delete from usersphoto where picURL=?");
            preparedStatement.setString(1,url);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
