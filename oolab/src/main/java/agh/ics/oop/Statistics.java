package agh.ics.oop;

import java.util.*;

import static java.lang.Math.max;

public class Statistics {
    private Map map;

    private int amountOfGrass;
    private HashMap<Vector2d, Integer> diedAnimals=new HashMap<Vector2d, Integer>();

    public Statistics(Map map) {
        this.map=map;
        for(int x=0;x<map.getWidth();x++){
            for(int y=0;y<map.getHeight();y++)
            {
                diedAnimals.put(new Vector2d(x,y),0);
            }
        }

    }

    public HashMap<Vector2d, Integer> getDiedAnimals() {
        return diedAnimals;
    }

    public int getAmountOfAnimals(){ //liczba wszystkich zwierząt na mapie
        return map.getAnimalsOnField().size();
    }

    //liczba wszystkich roślin (tutaj chyba trzeba jakoś mądrzej, żeby nie przekazywać engine)
    public int getAmountOfGrass(){
        return map.getNumberOfGrassOnField();
    }

    //liczba wolnych pól
    public int freeSpots(){
        int allSpots = map.getWidth() * map.getHeight();
        int busySpots = map.getAnimalsOnEachSpot().size();
        return allSpots - busySpots;
    }

    //najpopularniejszy genotyp
    public String theMostCommonGenotype(){
        int maxi = 1;
        Direction[] popular = new Direction[]{};

        HashMap<Direction[], Integer> genotypes = new HashMap<Direction[], Integer>();
        ArrayList<Animal> animals = map.getAnimalsOnField();

        for (Animal animal: animals){
            if (genotypes.containsKey(animal.genes)){
                int count = genotypes.get(animal.genes);
                genotypes.put(animal.genes, count + 1);
                if (count + 1 > maxi){
                    maxi = count + 1;
                    popular = animal.genes;
                }
            }
            else{
                genotypes.put(animal.genes, 1);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Direction gene: popular){
            stringBuilder.append(gene.toString());
        }
        return stringBuilder.toString();
    }

    //średnia energia żyjących zwierząt
    public int averageEnergyAlive(){
        int sum = 0;
        for(Animal animal : map.getAnimalsOnField()){
            sum += animal.energy;
        }

        return sum / map.getAnimalsOnField().size();
    }

    public int maxEnergyAlive(){
        int maxEnergy = 0;
        for(Animal animal : map.getAnimalsOnField()){
            maxEnergy=max(animal.energy,maxEnergy);
        }

        return maxEnergy;
    }

    //średnia długość życia nieżyjących zwierząt
    public int averageEnergyDead(){
        if (map.getDiedAnimalsOnMap().size()==0){return 0;}
        int sum = 0;
        for (Animal animal : map.getDiedAnimalsOnMap()){
            sum += animal.energy;
        }
        return sum / map.getDiedAnimalsOnMap().size();
    }


}
