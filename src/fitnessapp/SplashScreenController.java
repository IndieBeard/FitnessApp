/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author andyr
 */
public class SplashScreenController {
    
    @FXML
    private Button startWorkoutButton;
    
    @FXML
    private Button historyButton;
    
    @FXML
    private Button exitButton;
    
    //When this method is called, it will change the the Workout Screen
    //we want to read from the button to get the scene and then we can get the stage from the scene
    @FXML
    private void startWorkoutButtonAction(ActionEvent event) throws IOException{
        Parent pickExerciseParent = FXMLLoader.load(getClass().getResource("EnterDetails.fxml"));
        Scene pickExerciseScene = new Scene(pickExerciseParent);
        
        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(pickExerciseScene);
        window.show();
    }
    
   @FXML
   private void historyButtonAction(ActionEvent event) throws IOException{
        Parent historyParent = FXMLLoader.load(getClass().getResource("History.fxml"));
        Scene historyScene = new Scene(historyParent);

        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(historyScene);
        window.show();
   }
    
    @FXML
    //Will close the app
    private void closeButtonAction(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
}
