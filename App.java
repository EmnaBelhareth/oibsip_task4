import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * App
 */
public class App extends Application {

    @Override  
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("./UI/LoginUI.fxml"));
     // Parent root = FXMLLoader.load(getClass().getResource("./UI/testUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Online Examination");
        stage.setResizable(false);
        stage.show();
         try {
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("success connection...");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        launch(args);

    }  
}
    
