package agh.ics.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    private HashMap<Vector2d,Grass> grassOnField = new HashMap<>();
    private ArrayList<Animal> animalsOnField=new ArrayList<>();
    private HashMap<Vector2d,Integer> numberOfAnimalsOnField = new HashMap<>(); //pamietac zeby przy move zmieniac wartosc w tej hashmapie
    private int height;
    private int width;

    //Big Bang
    public Map(SimulationVar var) {
        height=var.getMapHeight();
        width= var.getMapWidth();
        for (int i=0;i<var.getAnimalsAtStart();i++){
            Animal newAnimal= new Animal(var);
            placeAnimalOnMap(newAnimal);
        }
        var.getGardener().seedGrass(var, this);
    }
    public void placeAnimalOnMap(Animal animal){
        animalsOnField.add(animal);
        Integer animalsOnSpot=numberOfAnimalsOnField.remove(animal.getPosition());
        if (animalsOnSpot == null){animalsOnSpot=0;}
        numberOfAnimalsOnField.put(animal.getPosition(),animalsOnSpot+1);
    }
    public void addGrass(Grass newGrass){
        grassOnField.put(newGrass.getPosition(), newGrass);
    }

    protected ArrayList<Animal> animalsOnSpot(Vector2d spot){

        return null;
    }
    protected int howManyAnimalsOnSpot(Vector2d spot){
        return 0;
    }
    protected boolean isGrassThere(Vector2d spot){
        return grassOnField.containsKey(spot);
    }


}