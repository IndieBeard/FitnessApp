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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andyr
 */
public class EnterDetailsController {
    
    @FXML
    private Text exerciseName;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button saveAndAddNewButton; 
    
    @FXML
    private Button saveAndMenuButton;
    
    //References to each data field on the EnterDetails Screen.
    @FXML
    private TextField set1Rep;
    @FXML
    private TextField set1Weight;
    @FXML
    private TextField set2Rep;
    @FXML
    private TextField set2Weight;
    @FXML
    private TextField set3Rep;
    @FXML
    private TextField set3Weight;
    @FXML
    private TextField set4Rep;
    @FXML
    private TextField set4Weight;
    
    private Set set1;
    private Set set2;
    private Set set3;
    private Set set4;
        
    //This method IS used, but not in this script. When the back button is pressed in the application it is called.
    @FXML
    private void backButtonAction(ActionEvent event) throws IOException{
        changeScene(event, "PickExercise.fxml");
    }
    
    @FXML
    //This method will save the information that was entered into the input boxes, save it in memory and print to console. Then return to the Pick Exercise screen
    private void saveAndAddNewAction(ActionEvent event) throws IOException{
        save();
        changeScene(event, "PickExercise.fxml");
    }
    
    @FXML
    private void saveAndMenuAction(ActionEvent event) throws IOException{
        save();
        changeScene(event, "SplashScreen.fxml");
    }
    
    //NOTE: This inefficient method will be reworked in the next deliverable
    private void save(){
        //if there is information in both input boxes for set 1
        if(!set1Rep.getText().trim().equals("") && !set1Weight.getText().trim().equals("")){
            int numRep = Integer.valueOf(set1Rep.getText());
            double numWeight = Double.parseDouble(set1Weight.getText());
            
            set1 = new Set(numRep, numWeight);
            System.out.println("The number of Reps for Set 1 are: " + set1.getReps());
            System.out.println("The amount of Weight for Set 1 are: " + set1.getWeight());
        }
        
        if(!set2Rep.getText().trim().equals("") && !set2Weight.getText().trim().equals("")){
            int numRep = Integer.valueOf(set2Rep.getText());
            double numWeight = Double.parseDouble(set2Weight.getText());
            
            set2 = new Set(numRep, numWeight);
            System.out.println("The number of Reps for Set 2 are: " + set2.getReps());
            System.out.println("The amount of Weight for Set 2 are: " + set2.getWeight());
        }

        if(!set3Rep.getText().trim().equals("") && !set3Weight.getText().trim().equals("")){
            int numRep = Integer.valueOf(set3Rep.getText());
            double numWeight = Double.parseDouble(set3Weight.getText());
           
            set3 = new Set(numRep, numWeight);
            System.out.println("The number of Reps for Set 3 are: " + set3.getReps());
            System.out.println("The amount of Weight for Set 3 are: " + set3.getWeight());
        }

        if(!set4Rep.getText().trim().equals("") && !set4Weight.getText().trim().equals("")){
            int numRep = Integer.valueOf(set4Rep.getText());
            double numWeight = Double.parseDouble(set4Weight.getText());
            
            set4 = new Set(numRep, numWeight);
            System.out.println("The number of Reps for Set 4 are: " + set4.getReps());
            System.out.println("The amount of Weight for Set 4 are: " + set4.getWeight());
        }
    }
    
    //Will change the scene to whatever is passed into it
    private void changeScene(ActionEvent event, String sceneName) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(sceneName));
        Scene scene = new Scene(parent);
        
        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setTitle(((Button)event.getSource()).getText());
        window.setScene(scene);
        window.show();
    }
    
    //This method is public so it can be accessed from the previous screen.
    //This method will set the name of the workout to reflect the button pressed
    public void setExerciseTitle(String name) {
        exerciseName.setText(name);
    }
    
}
