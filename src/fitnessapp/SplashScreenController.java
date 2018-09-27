/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author andyr
 */
public class SplashScreenController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //When this method is called, it will change the the Workout Screen
    //we want to read from the button to get the scene and then we can get the stage from the scene
    public void startWorkoutButtonPushed(ActionEvent event) throws IOException{
        //Parent pickExerciseParent = FXMLLoader.load(getClass().getResource("PickExercise.fxml"));
        Parent pickExerciseParent = FXMLLoader.load(getClass().getResource("PickExercise.fxml"));
        Scene pickExerciseScene = new Scene(pickExerciseParent);
        
        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(pickExerciseScene);
        window.show();
    }
    
}
