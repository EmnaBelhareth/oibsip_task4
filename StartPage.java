import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStart;

      @FXML
    void previous(MouseEvent event) throws IOException {
         Stage startStage = (Stage) btnStart.getScene().getWindow();
            startStage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("./UI/MainUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home Page");
            primaryStage.setResizable(false);
            primaryStage.show();

    }

    @FXML
    void startQuestion(ActionEvent event) throws IOException {
        Stage startStage = (Stage) btnStart.getScene().getWindow();
            startStage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("./UI/Question1UI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Question 1");
            primaryStage.setResizable(false);
            primaryStage.show();

    }

    @FXML
    void initialize() {
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'StartPageUI.fxml'.";

    }

}
