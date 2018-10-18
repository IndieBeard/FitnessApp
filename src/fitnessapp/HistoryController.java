/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;

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
            //update the list of workouts completed on the grid

            addNewLine(objectFromJson());
        } catch (IOException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Will read "Workout.json" and "convert" it into an arraylist of Workout objects, stored in workoutList
    private ArrayList<Workout> objectFromJson() throws FileNotFoundException, IOException {

        ArrayList<Workout> workoutList = new ArrayList<>();
        Gson gson = new Gson();

        System.out.println("Reading JSON from a file");
        System.out.println("----------------------------");

        BufferedReader br = new BufferedReader(
                new FileReader("Workouts.json"));
        String line;

        while ((line = br.readLine()) != null) {
            //convert the json string back to object
            //Workout workout = new GsonBuilder().create().fromJson(line, Workout.class);
            //workoutList.add(workout);

            JsonElement json = gson.fromJson(line, JsonElement.class);
            String jsonInString = gson.toJson(json);
            System.out.println(jsonInString);
            Type workoutType = new TypeToken<Workout>() {
            }.getType();
            Workout workout = gson.fromJson(jsonInString, workoutType);
            workoutList.add(workout);
        }

        for (Workout workout : workoutList) {
            System.out.println(workout.getDate());
        }

        return workoutList;
    }

    //This will add a new line to the grid pane and update it with workout date and set
    private void addNewLine(ArrayList<Workout> workoutList) {
        int lineCounter = 0;
        for (Workout workout : workoutList) {
            infoPanel.add(new Label(workout.getDate()), 0, lineCounter);
            infoPanel.add(new Label(workout.getSets().size() + " x " + workout.getExercise() + "\n"), 1, lineCounter);
            lineCounter++;
        }
    }

}
