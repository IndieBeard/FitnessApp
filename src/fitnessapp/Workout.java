/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andyr
 */
public class Workout implements Comparable<Workout> {
    
    private final String exercise;
    private final String date;
    private final ArrayList<Set> sets;
    
    
    
    public Workout(String _exercise, String _date, ArrayList<Set> _sets){
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

    @Override
    public int compareTo(Workout workout2) {
        
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
        
        try {
            
            Date date1 = format.parse(this.getDate());
            Date date2 = format.parse(workout2.getDate());
            
            return date1.compareTo(date2);
        } catch (ParseException ex) {
            Logger.getLogger(Workout.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
        
    }
}
