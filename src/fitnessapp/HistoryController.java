/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author andyr
 */
public class HistoryController implements Initializable {

    @FXML
    private GridPane infoPanel;

    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private Button backButton;
    
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Constraints code taken from https://stackoverflow.com/questions/45543669/gridpane-within-a-scrollpane
        // create new constraints for columns and set their percentage
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.NEVER);
        columnConstraints.setPercentWidth(100.00);

        // create new constraints for row and set their percentage
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.NEVER);

        infoPanel = new GridPane();
        infoPanel.getRowConstraints().add(rowConstraints);
        infoPanel.getColumnConstraints().add(columnConstraints);
        scrollPane.setContent(infoPanel);

        try {
            // When page is opened, read the file Workouts.json,
            //convert the json into an arraylist of Workouts
            //Sort that list by date
            //update the list of workouts completed on the grid sorting by date
            addNewLine(byDate(objectFromJson()));
            
        } catch (IOException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        changeScene(event, "SplashScreen.fxml");
    }

    //Will read "Workout.json" and "convert" it into an arraylist of Workout objects, stored in workoutList
    //Code inspired from inclass examples
    private ArrayList<Workout> objectFromJson() throws FileNotFoundException, IOException {

        ArrayList<Workout> workoutList = new ArrayList<>();
        Gson gson = new Gson();

        BufferedReader br = new BufferedReader(
                new FileReader("Workouts.json"));
        String line;

        while ((line = br.readLine()) != null) {
            //convert the json string back to object
            JsonElement json = gson.fromJson(line, JsonElement.class);
            String jsonInString = gson.toJson(json);
            Type workoutType = new TypeToken<Workout>() {
            }.getType();
            Workout workout = gson.fromJson(jsonInString, workoutType);
            workoutList.add(workout);
        }

        return workoutList;
    }
    
    //This method will loop through the array list of workouts and sort them into their own arraylists of workouts
    //organized by date.
    //Code inspired by info at https://stackoverflow.com/questions/43426669/java-sort-list-object-by-date-ascending
    private List<Workout> byDate(ArrayList<Workout> workoutList){
 
       Collections.sort(workoutList);
       
        return workoutList;
    }
    
    //This will add a new line to the grid pane and update it with workout date and workout information
    private void addNewLine(List<Workout> workoutList) {
        int lineCounter = 0;
        for (Workout workout : workoutList) {
            infoPanel.add(new Label(workout.getDate()), 0, lineCounter);
            int index = 1;
            Label details = new Label(workout.getExercise() + "\n");
            //Loop through the LinkedHashSet for each workout
            for(Set set : workout.getSets()){
                details.setText(details.getText() + "Set" + (index++) + ". " + set.getReps() + " x " + set.getWeight() + "\n");
            }
            infoPanel.add(details, 1, lineCounter);
            lineCounter++;
        }
    }
    
    private void changeScene(ActionEvent event, String sceneName) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(sceneName));
        Scene scene = new Scene(parent);

        //This line gets the stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setTitle(((Button) event.getSource()).getText());
        window.setScene(scene);
        window.show();
    }

}
