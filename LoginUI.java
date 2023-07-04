import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginUI {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    private static String logUsername;


    public String getUsername()
    {
        return logUsername;
    }
    @FXML
    void Login(ActionEvent event) throws IOException, SQLException {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        if (authenticate(username, password)) {
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome, " + username);
            Stage loginStage = (Stage) tfUsername.getScene().getWindow();
            loginStage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("./UI/MainUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home page");
            primaryStage.setResizable(false);
            primaryStage.show();
        }

        else

            showAlert(AlertType.ERROR, "Login Failed", "Invalid email or password");

    }

    private Boolean authenticate(String username, String password) throws SQLException {
       // return username.equals("emna") && password.equals("12345678");
       Connection con = DatabaseConnection.getConnection();
       String query = "SELECT * FROM user WHERE Username = ? AND Pwd = ?";
       PreparedStatement pStatement = con.prepareStatement(query);
       pStatement.setString(1, username);
       pStatement.setString(2, password);
       ResultSet resultSet = pStatement.executeQuery();
       if(resultSet.next())
       {
        logUsername = username;
       return true;
       }
       else return false;

       
    }

    void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert tfPassword != null : "fx:id=\"tfPassword\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert tfUsername != null : "fx:id=\"tfUsername\" was not injected: check your FXML file 'MainFrame.fxml'.";

    }

}
