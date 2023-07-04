import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection
 */
public class DatabaseConnection {

   
     public static Connection getConnection() throws SQLException {
         try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       
  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/gestion_users", "root", "");
    return connection;
    }
  
   }

