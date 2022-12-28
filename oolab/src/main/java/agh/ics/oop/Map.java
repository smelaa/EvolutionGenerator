package agh.ics.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    private HashMap<Vector2d,Grass> grassOnField = new HashMap<>();
    private int diedAnimals = 0;
    private ArrayList<Animal> animalsOnField=new ArrayList<>(); //wszystkie zwierzątka na mapie
    private HashMap<Vector2d,Integer> numberOfAnimalsOnField = new HashMap<>(); //pamietac zeby przy move zmieniac wartosc w tej hashmapie
    private ArrayList<Animal> diedAnimalsOnMap = new ArrayList<>();
    private int height;
    private int width;
    private IMapType map;
    private int howManyGrasses = 0;

    //Big Bang
    public Map(SimulationVar var) {
        height=var.getMapHeight();
        width= var.getMapWidth();
        map = var.getMapType();
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

    protected void setAnimalsOnField(Vector2d oldSpot, Vector2d newSpot){
        numberOfAnimalsOnField.put(oldSpot, numberOfAnimalsOnField.get(oldSpot) - 1); //usuwamy ze starego miejsca
        numberOfAnimalsOnField.put(newSpot, numberOfAnimalsOnField.get(newSpot) + 1); //dodajemy do nowego miejsca
    }

    protected void removeAnimal(Animal animal){
        animalsOnField.remove(animal); //usunięcie z listy zwierząt
        numberOfAnimalsOnField.put(animal.getPosition(), numberOfAnimalsOnField.get(animal.getPosition()) - 1); //dekrementowanie liczby zwierząt na tym polu
    }
    protected int howManyAnimalsOnSpot(Vector2d spot){ //zwraca liczbę zwierzątek na danych polu
        return numberOfAnimalsOnField.get(spot);

    }

    protected ArrayList<Animal> getAnimalsOnField(){
        return this.animalsOnField;
    }
    protected boolean isGrassThere(Vector2d spot){
        return grassOnField.containsKey(spot);
    }

    protected int getDiedAnimals(){
        return diedAnimals;
    }

    protected void setDiedAnimals(int n){
        diedAnimals = n;
    }

    protected HashMap<Vector2d, Integer> getAnimalsOnEachSpot(){
        return numberOfAnimalsOnField;
    }

    protected ArrayList<Animal> getDiedAnimalsOnMap(){
        return diedAnimalsOnMap;
    }

    protected void addToDiedList(Animal animal){
        diedAnimalsOnMap.add(animal);
    }

    protected void removeGrass(Grass grass){
        grassOnField.remove(grassOnField.get(grass).getPosition(), grass);
    }




}