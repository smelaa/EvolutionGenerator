package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;


import static java.lang.Math.round;

public class ForestedEquators implements IHolyGardener{

    @Override
    public void seedGrass(SimulationVar var,  Map map) {
        ArrayList<Vector2d> prefferedSpots=new ArrayList<>();
        ArrayList<Vector2d> restOfSpots=new ArrayList<>();
        //stworzymy tablicę preffered spots
        //stworzymy tablicę rest of spots
        //wylosujemy liczbę od 0 do 9
        //w zaleznosci od wylosowanej liczby wylosujemy vector z jednej z tablic
        //jezeli dana tablica jest pusta to wylosujemy z drugiej
        //zasadzimy trawke
        for (int x=0;x<var.getMapWidth();x++){
            for (int y=0;y<(int) round(0.4*var.getMapHeight());y++){
                Vector2d spot=new Vector2d(x,y);
                if (!map.isGrassThere(spot)) {
                    restOfSpots.add(spot);
                }
            }
            for (int y = (int) round(0.4*var.getMapHeight()); y<(int) round(0.6*var.getMapHeight()); y++){
                Vector2d spot=new Vector2d(x,y);
                if (!map.isGrassThere(spot)) {
                    prefferedSpots.add(spot);
                }
            }
            for (int y = (int) round(0.6*var.getMapHeight()); y<var.getMapHeight(); y++){
                Vector2d spot=new Vector2d(x,y);
                if (!map.isGrassThere(spot))
                    restOfSpots.add(spot);
            }
        }
        Random generator = new Random();
        for (int i=0;i<var.getGrassSpawnedEveryday();i++){
            if (prefferedSpots.size()==0 && restOfSpots.size()==0){break;}
            Vector2d newGrassPosition;
            if((generator.nextInt(10)<2 && restOfSpots.size()>0) ||prefferedSpots.size()==0){
                int ix=generator.nextInt(restOfSpots.size());
                newGrassPosition=restOfSpots.remove(ix);
            }
            else{
                int ix=generator.nextInt(prefferedSpots.size());
                newGrassPosition=prefferedSpots.remove(ix);
            }
            Grass newGrass= new Grass(newGrassPosition);
            map.addGrass(newGrass);
        }
    }
}
