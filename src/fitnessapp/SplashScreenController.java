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
    private Button close;
    
    //When this method is called, it will change the the Workout Screen
    //we want to read from the button to get the scene and then we can get the stage from the scene
    //Is public so it can be called from the main class.
    public void startWorkoutButtonPushed(ActionEvent event) throws IOException{
        Parent pickExerciseParent = FXMLLoader.load(getClass().getResource("PickExercise.fxml"));
        Scene pickExerciseScene = new Scene(pickExerciseParent);
        
        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(pickExerciseScene);
        window.show();
    }
    
    @FXML
    //Will close the app
    private void closeButtonAction(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    
}
