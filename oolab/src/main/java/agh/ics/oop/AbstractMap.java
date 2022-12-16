package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class AbstractMap {
    protected HashMap<Vector2d,Grass> grassOnField = new HashMap<>();
    protected HashMap<Vector2d,Animal> animalsOnField = new HashMap<>();
    protected int height;
    protected int width;
    protected HashSet<Vector2d> noGrassSpots = new HashSet<>();

    public AbstractMap() {
        //Big Bang
    }

    protected abstract void moveOnMap(Animal animal);

    protected ArrayList<Animal> animalsOnSpot(Vector2d spot){
        return null;
    }
    protected boolean isGrassThere(Vector2d spot){
        //implementujemy
        return false;
    }

    protected void placeAnimal(Vector2d spot, Animal animal){}
}
