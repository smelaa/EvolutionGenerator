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

    //najpopularniejszy genotyp
    public String theMostCommonGenotype(){
        int maxi = 0;
        String popular = "";

        HashMap<String, Integer> genotypes = new HashMap<String, Integer>();
        ArrayList<Animal> animals = map.getAnimalsOnField();

//to jest straszne, trzeba poprawić - nie zawsze działa, nie rozumiem dlaczego czasami jest a czasami nie
        for (Animal animal: animals){
            String gene = "";
            for (Direction genes : animal.genes){
                gene += genes.toString();
            }

            if (genotypes.containsKey(gene)){
                int count = genotypes.remove(gene);
                genotypes.put(gene, count + 1);
                if (count + 1 > maxi){
                    maxi = count + 1;
                    popular = gene;
                }
            }
            else{
                genotypes.put(gene, 1);
            }
        }

        return popular;

//        StringBuilder stringBuilder = new StringBuilder();
//        for (Direction gene: popular){
//            stringBuilder.append(gene.toString());
//        }
//
//        return stringBuilder.toString();
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


}
