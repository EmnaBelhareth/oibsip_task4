

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainUI {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnProfil;

    @FXML
    private Button btnStart;

  
    void nextPage(String ressource , String title) throws IOException
    {
        Stage mainStage = (Stage) btnLogout.getScene().getWindow();
        mainStage.close();
        Parent root = FXMLLoader.load(getClass().getResource(ressource));
        Stage primaryStage = new Stage();
        Scene scene = new Scene (root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
      

        nextPage("./UI/LoginUI.fxml","Online Examination");

        

    }

    @FXML
    void profil(ActionEvent event) throws IOException {
        
        nextPage("./UI/UpdateUI.fxml","Edit Profile");
    }

    @FXML
    void start(ActionEvent event) throws IOException {
        
         nextPage("./UI/StartPageUI.fxml","Start Page");

    }

    @FXML
    void initialize() {
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert btnProfil != null : "fx:id=\"btnProfil\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'MainUI.fxml'.";

    }

}
