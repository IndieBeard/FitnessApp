/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import java.text.SimpleDateFormat;
import java.util.Comparator;


/**
 *
 * @author andyr
 */
public class DateComparator implements Comparator<Workout> {
    
    SimpleDateFormat format = new SimpleDateFormat("MM/DD/YY");

    @Override
    public int compare(Workout w1, Workout w2) {
        System.out.println("The date on workout 1 is: " + w1.getDate());
        System.out.println("The date on workout 2 is: " + w2.getDate());
        
        return w1.compareTo(w2);
    }
    
}
