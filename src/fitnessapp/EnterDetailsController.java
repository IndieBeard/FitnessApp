/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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

    @FXML
    private Button addNewRowButton;

    //References to each data field on the EnterDetails Screen.
    @FXML
    private GridPane gridPane;

    @FXML
    private TextField exercise;
    @FXML
    private TextField date;

    private final LinkedHashSet<Set> setList = new LinkedHashSet<>();
    private int exerciseSetNumberCounter = 2; //Starts at 2 because 1 will always be first, 
    private int newRowButtonCounter = 3; //This is the starting row of the add button
    private int navigationButtonCounter = 4; //Starting row of the bottom navigation buttons
    private int numberOfRowsToLoop = 1;
    private final int numberOfColsToLoop = 2;

    //This method IS used, but not in this script. When the back button is pressed in the application it is called.
    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        changeScene(event, "SplashScreen.fxml");
    }

    @FXML
    //This method will save the information that was entered into the input boxes
    private void saveAndAddNewAction(ActionEvent event) throws IOException {
        //If the data passes the check, then save the data and refresh the scene
        if (checkDataEntered()) {
            save();
            changeScene(event, "EnterDetails.fxml");
        }
    }

    @FXML
    private void saveAndMenuAction(ActionEvent event) throws IOException {
        if (checkDataEntered()) {
            save();
            changeScene(event, "SplashScreen.fxml");
        }
    }

    @FXML
    private void addNewRowAction(ActionEvent event) throws IOException {
        addNewRow();
    }

    private void addNewRow() {

        // create controls:
        Text setNumberText = new Text();
        setNumberText.setText(Integer.toString(exerciseSetNumberCounter));
        exerciseSetNumberCounter++;

        TextField repInputField = new TextField();
        TextField weightInputField = new TextField();

        // add controls to grid:
        gridPane.add(setNumberText, 0, numberOfRowsToLoop + 2);
        gridPane.add(repInputField, 1, numberOfRowsToLoop + 2);
        gridPane.add(weightInputField, 2, numberOfRowsToLoop + 2);
        numberOfRowsToLoop++;

        newRowButtonCounter++;
        navigationButtonCounter++;

        //Move the other things down on the grid rows
        GridPane.setRowIndex(addNewRowButton, newRowButtonCounter);
        GridPane.setRowIndex(backButton, navigationButtonCounter);
        GridPane.setRowIndex(saveAndAddNewButton, navigationButtonCounter);
        GridPane.setRowIndex(saveAndMenuButton, navigationButtonCounter);

    }

    //This method will check the data entered into the text box to make sure
    //There is an exercise name entered.
    //There is a date entered in the correct format
    //The correct data type is entered for reps and weight
    //If there is an error, then generate the appropriate error message.
    //Code inspired by https://stackoverflow.com/questions/33968333/how-to-check-if-a-string-is-date and extrapolating on that
    private boolean checkDataEntered() {


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/YY");
        
        try {
            dateFormat.parse(date.getText());
        } catch (ParseException pe) {
            Alert alert = new Alert(AlertType.ERROR, "Date must be entered in MM/DD/YY format!", ButtonType.CLOSE);
            alert.showAndWait();
            return false;
        }
        

        //This code is a similar loop that is used in Save()
        //However this will make sure that each box is filled out correctly.
        for (int row = 2; row < numberOfRowsToLoop + 2; row++) {
            for (int col = 1; col <= numberOfColsToLoop; col++) {
                TextField textField = getNodeByRowColumnIndex(row, col, gridPane);
                String stringFromInputBox = textField.getText();

                //If it is in the first column (Number of Reps Input), check if it's a int
                if (col == 1 && !"".equals(stringFromInputBox)) {
                    try {
                        Integer.parseInt(stringFromInputBox);
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(AlertType.ERROR, "You have a non-Int in reps!", ButtonType.CLOSE);
                        alert.showAndWait();
                        return false;
                    }

                    //If it's not, (2nd column) (Weight Input), check if its a double
                } else {
                    if (!"".equals(stringFromInputBox)) {
                        try {
                            Double.parseDouble(stringFromInputBox);
                        } catch (NumberFormatException e) {
                            Alert alert = new Alert(AlertType.ERROR, "You have a non-Double in Weight!", ButtonType.CLOSE);
                            alert.showAndWait();
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
        
    }

    private void save() throws IOException {

        checkDataEntered();

        //Loop through each of the lines and save what is entered.
        //We loop number of rows to loop + 2, because it will start at the correct node
        //Everytime we press the plus button, we'll add one to our Number of Rows 
        //Looping through Gridpane, we always start at Row 2, Col 1
        //Then Row 2, Col 2, Row 3 col 3, and so on
        for (int row = 2; row < numberOfRowsToLoop + 2; row++) {
            int numRep = 0;
            Double numWeight = null;
            Set set;
            for (int col = 1; col <= numberOfColsToLoop; col++) {
                TextField textField = getNodeByRowColumnIndex(row, col, gridPane);
                String stringFromInputBox = textField.getText();

                if (col == 1) {
                    if (!"".equals(stringFromInputBox)) {
                        numRep = Integer.valueOf(stringFromInputBox);
                    } else {
                        numRep = 0;
                    }
                } else {
                    if (!"".equals(stringFromInputBox)) {
                        numWeight = Double.parseDouble(stringFromInputBox);
                    } else {
                        numWeight = 0.0;
                    }
                }

            }
            set = new Set(numRep, numWeight);
            setList.add(set);
        }

        Workout workoutToSave = new Workout(exercise.getText(), date.getText(), setList);
        objToJSON(workoutToSave);
    }

    //Code inspired by https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
    //This will return the Textfield that is located in whatever row, column, index we pass in.
    public TextField getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        TextField result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                    result = (TextField) node;
                    break;
                }
            }
        }

        return result;
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

}
