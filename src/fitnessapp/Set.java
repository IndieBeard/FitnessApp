/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

/**
 *
 * @author andyr
 */
public class Set {
    
    private final int reps;
    private final double weight;
    
    public Set(int _reps, double _weight){
        reps = _reps;
        weight = _weight;
    }
    
    public int getReps(){
        return reps;
    }
    
    public double getWeight(){
        return weight;
    }
    
}
