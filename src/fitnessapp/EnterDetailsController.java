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
public class EnterDetailsController implements Initializable {
    
    @FXML
    private Text exerciseName;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button saveAndAddNewButton; 
    
    @FXML
    private Button saveAndMenuButton;
        
    
    public void backButtonAction(ActionEvent event) throws IOException{
        Parent pickExerciseParent = FXMLLoader.load(getClass().getResource("PickExercise.fxml"));
        Scene pickExerciseScene = new Scene(pickExerciseParent);
        
        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setTitle(((Button)event.getSource()).getText());
        window.setScene(pickExerciseScene);
        window.show();
    }
    
    public void saveAndAddNewAction(ActionEvent event) throws IOException{
        
    }
    
    public void saveAndMenuAction(ActionEvent event) throws IOException{

    }
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //This method is public so it can be accessed from the previous screen.
    //This method will set the name of the workout to reflect the button pressed
    public void setExerciseTitle(String name) {
        exerciseName.setText(name);
    }
    
}
