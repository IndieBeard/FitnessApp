/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.lang.reflect.Type;

/**
 * FXML Controller class
 *
 * @author andyr
 */
public class EnterDetailsController {

    @FXML
    private Button backButton;

    //Not used but initializes in FXML
    @FXML
    private Button saveAndAddNewButton;

    //Not used but initializes in FXML
    @FXML
    private Button saveAndMenuButton;

    //References to each data field on the EnterDetails Screen.
    @FXML
    private TextField exercise;
    @FXML
    private TextField date;
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
    private final ArrayList<Set> setList = new ArrayList<>();

    //This method IS used, but not in this script. When the back button is pressed in the application it is called.
    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        changeScene(event, "SplashScreen.fxml");
    }

    @FXML
    //This method will save the information that was entered into the input boxes, save it in memory and print to console. Then return to the Pick Exercise screen
    private void saveAndAddNewAction(ActionEvent event) throws IOException {
        save();
        changeScene(event, "EnterDetails.fxml");
    }

    @FXML
    private void saveAndMenuAction(ActionEvent event) throws IOException {
        save();
        changeScene(event, "SplashScreen.fxml");
    }

    //NOTE: This inefficient method will be reworked in the next deliverable
    private void save() throws IOException {
        //if there is information in both input boxes for set 1
        if (!set1Rep.getText().trim().equals("") && !set1Weight.getText().trim().equals("")) {
            int numRep = Integer.valueOf(set1Rep.getText());
            double numWeight = Double.parseDouble(set1Weight.getText());

            set1 = new Set(numRep, numWeight);
            setList.add(set1);

        }

        if (!set2Rep.getText().trim().equals("") && !set2Weight.getText().trim().equals("")) {
            int numRep = Integer.valueOf(set2Rep.getText());
            double numWeight = Double.parseDouble(set2Weight.getText());

            set2 = new Set(numRep, numWeight);
            setList.add(set2);

        }

        if (!set3Rep.getText().trim().equals("") && !set3Weight.getText().trim().equals("")) {
            int numRep = Integer.valueOf(set3Rep.getText());
            double numWeight = Double.parseDouble(set3Weight.getText());

            set3 = new Set(numRep, numWeight);
            setList.add(set3);

        }

        if (!set4Rep.getText().trim().equals("") && !set4Weight.getText().trim().equals("")) {
            int numRep = Integer.valueOf(set4Rep.getText());
            double numWeight = Double.parseDouble(set4Weight.getText());

            set4 = new Set(numRep, numWeight);
            setList.add(set4);

        }

        //For testing to ensure data is recorded correctly.  
        int index = 1;
        for (Set set : setList) {
            //System.out.println("The number of Reps for Set " + (index) + " are: " + set.getReps());
            //System.out.println("The amount of Weight for Set " + (index++) + " are: " + set.getWeight());
        }

        Workout workoutToSave = new Workout(exercise.getText(), date.getText(), setList);
        objToJSON(workoutToSave);
    }

    //This will convert the workout object to JSON and write it to a file called "Workouts.json"
    void objToJSON(Workout workout) throws FileNotFoundException, IOException {

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        jsonArrayBuilder.add(Json.createObjectBuilder()
                .add("date", workout.getDate())
                .add("exercise", workout.getExercise()));
        workout.getSets().forEach((set) -> {
            jsonArrayBuilder.add(Json.createObjectBuilder()
                    .add("reps", set.getReps())
                    .add("Weight", set.getWeight())
            );
        });

        Gson gson = new Gson();
        Type type = new TypeToken<Workout>() {
        }.getType();
        String json = gson.toJson(workout, type);
        System.out.println(json);

        try {
            //write converted json data to a file named "Workouts.json"
            try (FileWriter writer = new FileWriter("Workouts.json", true)) {
                writer.write("\n");
                writer.write(json);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Will change the scene to whatever is passed into it
    private void changeScene(ActionEvent event, String sceneName) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(sceneName));
        Scene scene = new Scene(parent);

        //This line gets the stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setTitle(((Button) event.getSource()).getText());
        window.setScene(scene);
        window.show();
    }

    //This method is public so it can be accessed from the previous screen.
    //This method will set the name of the workout to reflect the button pressed
    //public void setExerciseTitle(String name) {
    //    exerciseName.setText(name);
    //}
}
