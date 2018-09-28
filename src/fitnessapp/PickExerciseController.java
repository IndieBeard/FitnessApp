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
 * FXML Controller class
 *
 * @author andyr
 */
public class PickExerciseController {
    
    @FXML
     private void exerciseSelectAction(ActionEvent event) throws IOException{
        changeScene(event, "EnterDetails.fxml");
    }
     
    @FXML
    private void backButtonAction(ActionEvent event) throws IOException{
        changeScene(event, "SplashScreen.fxml");
    } 
     
     private void changeScene(ActionEvent event, String sceneName) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(sceneName));
        Scene scene = new Scene(parent);
        
        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setTitle(((Button)event.getSource()).getText());
        window.setScene(scene);
        window.show();
    }
     
   
    
}
