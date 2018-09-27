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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andyr
 */
public class PickExerciseController implements Initializable {

   // @FXML 
    //private Text workoutName;
    
    @FXML
    private Button button;
    
    /*
    @FXML protected void exerciseSelectAction(ActionEvent event) throws IOException {
        //actiontarget.setText("Sign in button pressed");
        
        Parent enterDetailsParent;
        enterDetailsParent = FXMLLoader.load(getClass().getResource("EnterDetails.fxml"));
        Scene enterDetailsScene = new Scene(enterDetailsParent);
        
        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(enterDetailsScene);
        window.show();
        
       // enterWorkoutDetailsScene(event);
        System.out.println("Button Pressed!");
    } 
*/
    @FXML
     private void exerciseSelectAction(ActionEvent event) throws IOException{
        Parent enterDetailsParent = FXMLLoader.load(getClass().getResource("EnterDetails.fxml"));
        Scene enterDetailsScene = new Scene(enterDetailsParent);
        
        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setTitle(((Button)event.getSource()).getText());
        window.setScene(enterDetailsScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
}
