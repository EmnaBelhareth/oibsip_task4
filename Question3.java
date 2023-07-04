import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class Question3 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton answer1;

    @FXML
    private RadioButton answer2;

    @FXML
    private RadioButton answer3;

    @FXML
    private RadioButton answer4;
    @FXML
    private Button btnNext;

    @FXML
    private Label lbTime;
    static int seconds = 10;
    private Timer timer;
    private boolean buttonClicked = false;
     void startCountDown() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (seconds <= 0 || buttonClicked) {
                    timer.cancel();
                    Platform.runLater(() -> {
                        try {
                            autoSubmit();
                        } catch (SQLException | IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    Platform.runLater(() -> lbTime.setText(String.valueOf(seconds)));
                    seconds--;
                }
            }
        }, 0, 1000);
    }
   

    

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

    void nextPage() throws IOException {
        Stage q1Stage = (Stage) answer1.getScene().getWindow();
        q1Stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("./UI/Question4UI.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question 4");
        primaryStage.setResizable(false);
        primaryStage.show();
       

    }
 void autoSubmit() throws SQLException, IOException {
        Connection con = DatabaseConnection.getConnection();
        String query = "UPDATE user SET score = score + ? WHERE Id = ?";
        PreparedStatement pStatement = con.prepareStatement(query);
        if (answer4.isSelected()) {
            pStatement.setInt(1, 25);
            pStatement.setInt(2, getCurrentUserId());

        } else {
            pStatement.setInt(1, 0);
            pStatement.setInt(2, getCurrentUserId());

        }
        int row = pStatement.executeUpdate();
        if (row > 0) {
            System.out.println("Score updated successfully!");
        } else {
            System.out.println("Failed to update the score.");
        }
        nextPage();

    }

    @FXML
    void nextQuestion3(ActionEvent event) throws IOException, SQLException {

        buttonClicked = true;
        
       
    
    }
   
    @FXML
    void initialize() {
        assert answer1 != null : "fx:id=\"answer1\" was not injected: check your FXML file 'Question1UI.fxml'.";
        assert answer2 != null : "fx:id=\"answer2\" was not injected: check your FXML file 'Question1UI.fxml'.";
        assert answer3 != null : "fx:id=\"answer3\" was not injected: check your FXML file 'Question1UI.fxml'.";
        assert answer4 != null : "fx:id=\"answer4\" was not injected: check your FXML file 'Question1UI.fxml'.";
        ToggleGroup toggleGroup = new ToggleGroup();
        answer1.setToggleGroup(toggleGroup);
        answer2.setToggleGroup(toggleGroup);
        answer3.setToggleGroup(toggleGroup);
        answer4.setToggleGroup(toggleGroup);
        startCountDown();

        
    }

}
