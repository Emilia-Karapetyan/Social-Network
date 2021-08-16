package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String DRIVER_PATH="com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/socialbase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "root";

    private static DBConnection instance=new DBConnection();

    private DBConnection(){
        try {
            Class.forName(DRIVER_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance()
    {
        return instance;
    }
    private Connection connection;
    public Connection getConnection(){
        try {
            if(connection==null || connection.isClosed()){
                connection= DriverManager.getConnection(URL,USER,PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
