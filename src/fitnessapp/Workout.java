/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import java.util.ArrayList;

/**
 *
 * @author andyr
 */
public class Workout {
    
    private final String exercise;
    private final String date;
    private final ArrayList<Set> sets;
    
    public Workout(String _exercise, String _date, ArrayList _sets){
        exercise = _exercise;
        date = _date;
        sets = _sets;
    }
    
    public String getExercise(){
        return exercise;
    }
    
    public String getDate(){
        return date;
    }
    
    public ArrayList<Set> getSets(){
        return sets;
    }
    
}
