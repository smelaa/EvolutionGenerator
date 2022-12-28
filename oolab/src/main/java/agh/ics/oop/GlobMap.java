package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;

public class GlobMap implements IMapType{


    @Override
    public void moveOnMap(Animal animal, SimulationVar var, Map map){

        var.getBehaviorModel().geneBehaviour(animal);

        //jeśli zwierzę może się poruszyć
        if (animal.energy - 1 >= 0){
            changeAnimalPosition(animal, var, map); //zmiana pozycji
        }
        else{
            animal.diedDate = animal.age;
            map.setDiedAnimals(map.getDiedAnimals() + 1);
            map.addToDiedList(animal);
        }



    }


    protected void changeAnimalPosition(Animal animal, SimulationVar var, Map map){
        Direction[] genes = animal.genes;
        int currGeneIdx = animal.activeGeneIx;
        int mapHeight = var.getMapHeight();
        int mapWidth = var.getMapHeight();

        //o ten wektor się poruszamy
        Vector2d vectorToMove = genes[currGeneIdx].toUnitVector();
        Vector2d posAfterMovement = animal.position.add(vectorToMove);


        if (posAfterMovement.y < 0 || posAfterMovement.y >= mapHeight){
            animal.orientation = animal.orientation.opposite(animal.orientation);
            posAfterMovement  = animal.position;
        }
        //czy równik
        else if (posAfterMovement.x >= mapWidth || posAfterMovement.x < 0){
            if (posAfterMovement.x >= mapWidth){
                Vector2d vector = new Vector2d(0, posAfterMovement.y);
                posAfterMovement = vector;

            }
            else{
                Vector2d vector = new Vector2d(mapWidth - 1, posAfterMovement.y);
                posAfterMovement = vector;
            }
        }

        map.setAnimalsOnField(animal.getPosition(), posAfterMovement);
        animal.changePosition(posAfterMovement);

    }

}
