package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    private HashMap<Vector2d,Grass> grassOnField = new HashMap<>();
    private HashMap<Vector2d,Animal> animalsOnField = new HashMap<>(); //tu musi być jednak inna struktura danych gdyż mapa ma tylko jedną wartość dla danego klucza, a u nas może być kilka zwierzaków na jednym polu
    private int height;
    private int width;
    //public HashSet<Vector2d> noGrassSpots = new HashSet<>(); chyba jednak tego nie potrzeba

    //Big Bang
    public Map(SimulationVar var) {
        height=var.getMapHeight();
        width= var.getMapWidth();
        for (int i=0;i<var.getAnimalsAtStart();i++){
            Animal newAnimal= new Animal(var);
            animalsOnField.put(newAnimal.getPosition(), newAnimal);
        }
        var.getGardener().seedGrass(var, this);
    }
    public void addGrass(Grass newGrass){
        grassOnField.put(newGrass.getPosition(), newGrass);
    }

    protected void move(Animal animal){}

    protected ArrayList<Animal> animalsOnSpot(Vector2d spot){

    }
    protected boolean isGrassThere(Vector2d spot){
        return grassOnField.containsKey(spot);
    }

    protected void placeAnimal(Vector2d spot, Animal animal){}


}