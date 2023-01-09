package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private HashMap<Vector2d,Grass> grassOnField = new HashMap<>();
    private int diedAnimals = 0;
    private ArrayList<Animal> animalsOnField=new ArrayList<>(); //wszystkie zwierzątka na mapie
    private HashMap<Vector2d,Integer> numberOfAnimalsOnField = new HashMap<>(); //pamietac zeby przy move zmieniac wartosc w tej hashmapie
    private ArrayList<Animal> diedAnimalsOnMap = new ArrayList<>();
    private Statistics stats;
    private int height;
    private int width;
    private IMapType map;
    private int maxEnergy=0;

    //Big Bang
    public Map(SimulationVar var) {
        height=var.getMapHeight();
        width= var.getMapWidth();
        map = var.getMapType();
        stats=new Statistics(this);
        updateMaxEnergy();
        for (int i=0;i<var.getAnimalsAtStart();i++){
            Animal newAnimal= new Animal(var,this);
            placeAnimalOnMap(newAnimal);
        }
        var.getGardener().seedGrass(var, this);
    }
    public void placeAnimalOnMap(Animal animal){
        animalsOnField.add(animal);
        stats.addGenesToGenomes(animal.genesToString());
        Integer animalsOnSpot=numberOfAnimalsOnField.remove(animal.getPosition());
        if (animalsOnSpot == null){animalsOnSpot=0;}
        numberOfAnimalsOnField.put(animal.getPosition(),animalsOnSpot+1);
    }
    public void addGrass(Grass newGrass){
        grassOnField.put(newGrass.getPosition(), newGrass);
    }

    protected void setAnimalsOnField(Vector2d oldSpot, Vector2d newSpot){
        int old = numberOfAnimalsOnField.remove(oldSpot);
        if (old - 1 > 0){
            numberOfAnimalsOnField.put(oldSpot, old - 1); //usuwamy ze starego miejsca
        }


        Integer animalsOnSpot=numberOfAnimalsOnField.remove(newSpot);
        if (animalsOnSpot == null){animalsOnSpot=0;}
        numberOfAnimalsOnField.put(newSpot, animalsOnSpot + 1); //dodajemy do nowego miejsca
    }

    protected void removeAnimal(Animal animal){
        animalsOnField.remove(animal); //usunięcie z listy zwierząt
        stats.removeGenesToGenomes(animal.genesToString());
        int tmp = numberOfAnimalsOnField.remove(animal.getPosition());
        if (tmp - 1 != 0){
            numberOfAnimalsOnField.put(animal.getPosition(), tmp - 1);
        }
    }
    protected int howManyAnimalsOnSpot(Vector2d spot){ //zwraca liczbę zwierzątek na danych polu
        return numberOfAnimalsOnField.get(spot);

    }

    protected ArrayList<Animal> getAnimalsOnField(){
        return this.animalsOnField;
    }
    public boolean isGrassThere(Vector2d spot){
        return grassOnField.containsKey(spot);
    }

    public boolean isAnimalThere(Vector2d spot){ return (numberOfAnimalsOnField.containsKey(spot) && numberOfAnimalsOnField.get(spot)>0);}

    //tego też się wstydzimy
    public ArrayList<Animal> getAnimalsOnSpot(Vector2d spot){
        ArrayList<Animal> animalsOnSpot=new ArrayList<Animal>();
        for (Animal animal: animalsOnField){
            if (animal.getPosition().equals(spot)){
                animalsOnSpot.add(animal);
            }
        }
        return animalsOnSpot;
    }
    public Animal getAnimalOnSpot(Vector2d spot){
        for (Animal animal: animalsOnField){
            if (animal.getPosition().equals(spot)){
                return animal;
            }
        }
        return null;
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
        if (grassOnField.get(grass.getPosition())!= null) {
            grassOnField.remove(grass.getPosition());
        }
    }

    public Statistics getStats() {
        return stats;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getNumberOfGrassOnField() {
        return grassOnField.size();
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void updateMaxEnergy() {
        maxEnergy=stats.maxEnergyAlive();
    }

    public HashMap<Vector2d, Grass> getGrassOnField() {
        return grassOnField;
    }

    public String getTheMostPopularGenome(){
        return stats.getTheMostPopularGenome();
    }
}