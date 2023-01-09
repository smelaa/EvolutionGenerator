package agh.ics.oop;


import java.util.Collections;
import java.util.HashMap;

import static java.lang.Math.max;

public class Statistics {
    private Map map;

    private HashMap<Vector2d, Integer> diedAnimals=new HashMap<Vector2d, Integer>();
    private HashMap<String, Integer> genomes=new HashMap<String, Integer>();
    private String theMostPopularGenome="";

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

    //liczba wszystkich roślin
    public int getAmountOfGrass(){
        return map.getNumberOfGrassOnField();
    }

    //liczba wolnych pól (t pole, gdzie nie ma zwierzątek)
    public int freeSpots(){
        int allSpots = map.getWidth() * map.getHeight();
        int busySpots = map.getAnimalsOnEachSpot().size();
        if(allSpots - busySpots > 0){
            return allSpots - busySpots;
        }
        else{
            return 0;
        }
    }
    public void addGenesToGenomes(String genes){
        if (genomes.containsKey(genes)){
            genomes.put(genes,genomes.remove(genes)+1);
        }
        else genomes.put(genes,1);
    }
    public void removeGenesToGenomes(String genes){
        if (genomes.containsKey(genes)){
            if (genomes.get(genes)==1){
                genomes.remove(genes);
            }
            else genomes.put(genes,genomes.remove(genes)-1);
        }
        //else houston mamy problem
    }
    //najpopularniejszy genotyp
    public String theMostCommonGenotype(){
        if (genomes.size()>0){
            Integer maxVal= Collections.max(genomes.values());
            for (String genes: genomes.keySet()){
                if (genomes.get(genes).equals(maxVal)){
                    theMostPopularGenome=genes;
                    return genes;
                }
            }
        }
        return "";
    }


    //średnia energia żyjących zwierząt
    public int averageEnergyAlive(){
        if (map.getAnimalsOnField().size() == 0){
            return 0;
        }
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
    public int averageAgeDead(){
        if (map.getDiedAnimalsOnMap().size()==0){return 0;}
        int sum = 0;
        for (Animal animal : map.getDiedAnimalsOnMap()){
            sum += animal.diedDate;
        }

        return sum / map.getDiedAnimals();
    }
    public void printToFile(FileMenager fileMenager){
        Object[] statistics = new Object[]{
                getAmountOfAnimals(),
                getAmountOfGrass(),
                freeSpots(),
                theMostCommonGenotype(),
                averageEnergyAlive(),
                averageAgeDead()
        };
        fileMenager.dayStats(statistics);
    }
    public String getTheMostPopularGenome() {
        return theMostPopularGenome;
    }
}
