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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FinalPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbscore;

    private int getCurrentUserId() {
        int userId = 0;
        LoginUI lg = new LoginUI();
        try (Connection con = DatabaseConnection.getConnection()) {

            String query = "SELECT Id FROM user WHERE Username = ? ";
            PreparedStatement pStatement = con.prepareStatement(query);
            pStatement.setString(1, lg.getUsername());

            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;

    }

    @FXML
    void backToHome(ActionEvent event) throws IOException {
        Stage loginStage = (Stage) lbscore.getScene().getWindow();
        loginStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("./UI/MainUI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home page");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @FXML
    void initialize() throws SQLException {
        assert lbscore != null : "fx:id=\"lbscore\" was not injected: check your FXML file 'Question1UI.fxml'.";
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT score FROM user WHERE Id = ? ";
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setInt(1, getCurrentUserId());
        ResultSet resultSet = pStatement.executeQuery();
        if (resultSet.next()) {
            int score = resultSet.getInt("score");
            lbscore.setText(String.valueOf(score));
        }

    }

}
