package userManager;

import dbConnection.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendManager {
    private Connection connection;

    public FriendManager() {
        connection = DBConnection.getInstance().getConnection();
    }

    public void addFriend(int from_id, int to_id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("Insert into friend (fromId,toId) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, from_id);
            preparedStatement.setInt(2, to_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void accept(int id, int id1)  {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE friend set bool=1 where fromId=? and toId=?");
        statement.setInt(1, id);
        statement.setInt(2, id1);
        statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> myFriend(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT user.* FROM friend" +
                "                 INNER JOIN USER ON user.id=friend.fromId WHERE toId=? AND BOOL=1");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getInt("age"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setPicURL(resultSet.getString("picURL"));
            userList.add(user);
        }

        return userList;
    }

    public List<User> myFriend1(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT user.* FROM friend" +
                "                 INNER JOIN USER ON user.id=friend.toId WHERE fromId=? AND BOOL=1");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getInt("age"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setPicURL(resultSet.getString("picURL"));
            userList.add(user);
        }

        return userList;
    }

    public void ignorRequest(int from_id, int to_id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM friend WHERE toId=? AND fromId=?");
            statement.setInt(1, from_id);
            statement.setInt(2, to_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> requestFriend(int id) {
        PreparedStatement statement = null;
        List<User> userList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT  user.* from friend" +
                    " inner join user on user.id=friend.toId where fromId=? and bool=0");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPicURL(resultSet.getString("picURL"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> MyrequestFriend(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT  user.* from friend" +
                " inner join user on user.id=friend.fromId where toId=? and bool=0");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getInt("age"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setPicURL(resultSet.getString("picURL"));
            userList.add(user);
        }
        return userList;
    }

    public void deleteFriendById(int from_id, int to_id) {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE from friend where fromId=? and toId=?");
            statement.setInt(1, from_id);
            statement.setInt(2, to_id);
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement("DELETE from friend where toId=? and fromId=?");
            statement2.setInt(1, from_id);
            statement2.setInt(2, to_id);
            statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
