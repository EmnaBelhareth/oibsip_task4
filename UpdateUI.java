import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateUI {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnUpdate;

    @FXML
    private PasswordField tfNpassword;

    @FXML
    private TextField tfNusername;

    @FXML
    void Update(ActionEvent event) throws SQLException, IOException {
        LoginUI lg = new LoginUI();
        Connection con = DatabaseConnection.getConnection();
        String query = "UPDATE user SET Username = ? , Pwd = ?";
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, tfNusername.getText());
        pStatement.setString(2, tfNpassword.getText());
        int row = pStatement.executeUpdate();
        if (row > 0)
            lg.showAlert(AlertType.INFORMATION, "Update Successful", "Your profile was updated successfully");
        Stage mainStage = (Stage) btnUpdate.getScene().getWindow();
        mainStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("./UI/LoginUI.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Online Examination");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @FXML
    void previous(MouseEvent event) throws IOException {
        Stage mainStage = (Stage) btnUpdate.getScene().getWindow();
        mainStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("./UI/MainUI.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home Page");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @FXML
    void initialize() {
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'UpdateUI.fxml'.";
        assert tfNpassword != null : "fx:id=\"tfNpassword\" was not injected: check your FXML file 'UpdateUI.fxml'.";
        assert tfNusername != null : "fx:id=\"tfNusername\" was not injected: check your FXML file 'UpdateUI.fxml'.";

    }

}
