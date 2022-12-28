package agh.ics.oop;

import java.util.*;

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

    public int getAmountOfAnimals(Map map){ //liczba wszystkich zwierząt na mapie
        return map.getDiedAnimals() + map.getAnimalsOnField().size();
    }

    //liczba wszystkich roślin (tutaj chyba trzeba jakoś mądrzej, żeby nie przekazywać engine)
    public int getAmountOfGrass(SimulationVar var, SimulationEngine engine){
        return var.getGrassSpawnedEveryday() * engine.getDays();
    }

    //liczba wolnych pól
    public int freeSpots(Map map, SimulationVar var){
        int allSpots = var.getMapHeight() * var.getMapHeight();
        int busySpots = map.getAnimalsOnEachSpot().size();
        return allSpots - busySpots;
    }

    //najpopularniejszy genotyp
    public Direction[] theMostCommonGenotype(Map map){
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

        return popular;


    }

    //średnia energia żyjących zwierząt
    public int averageEnergyAlive(Map map){
        int sum = 0;
        for(Animal animal : map.getAnimalsOnField()){
            sum += animal.energy;
        }

        return sum / map.getAnimalsOnField().size();
    }

    //średnia długość życia nieżyjących zwierząt
    public int averageEnergyDead(Map map){
        int sum = 0;
        for (Animal animal : map.getDiedAnimalsOnMap()){
            sum += animal.energy;
        }
        return sum / map.getDiedAnimalsOnMap().size();
    }


}
