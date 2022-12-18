package agh.ics.oop;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class Statistics {
    private int amountOfGrass;
    private HashMap<Vector2d, Integer> diedAnimals;

    public Statistics(SimulationVar var) {
        for(int x=0;x<var.getMapWidth();x++){
            for(int y=0;y<var.getMapHeight();y++)
            {
                diedAnimals.put(new Vector2d(x,y),0);
            }
        }

    }

    public HashMap<Vector2d, Integer> getDiedAnimals() {
        return diedAnimals;
    }

    public int getAmountOfAnimals(){
        //oblicz ile było wszystkich zwierzaków w danej symulacji
        return 0;
    }
}
