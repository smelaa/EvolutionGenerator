package agh.ics.oop;

import java.util.*;

import static java.lang.Math.round;

public class ToxicCorpses implements IHolyGardener{
    @Override
    public void seedGrass(SimulationVar var,  Map map) {
        //stworzymy tablicę vector2d posortowaną względem tego ile zwierzaków umarło na danym polu
        //wylosujemy liczbę od 0 do 9
        //w zaleznosci od wylosowanej liczby wylosujemy vector z 20% pól na których umarło najmniej zwierzakow lub 80% pozostałych pól
        //zasadzimy trawke
        ArrayList<Vector2d> positions= new ArrayList<>(map.getStats().getDiedAnimals().keySet());
        for (Vector2d position: positions){
            if(map.isGrassThere(position)){positions.remove(position);}
        }
        positions.sort(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return map.getStats().getDiedAnimals().get(o1)-map.getStats().getDiedAnimals().get(o2);
            }
        });
        Random generator = new Random();
        for (int i=0;i<var.getGrassSpawnedEveryday();i++){
            if (positions.size()==0){break;}
            Vector2d newGrassPosition;
            if(generator.nextInt(10)>=2){
                int ix=generator.nextInt((int) round(positions.size()*0.2));
                newGrassPosition=positions.remove(ix);
            }
            else{
                int ix=generator.nextInt(positions.size()-(int) round(positions.size()*0.2))+(int) round(positions.size()*0.2);
                newGrassPosition=positions.remove(ix);
            }
            Grass newGrass= new Grass(newGrassPosition);
            map.addGrass(newGrass);
        }

    }
}
