package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class HellMap implements IMapType {


    @Override
    public void moveOnMap(Animal animal, SimulationVar var, Map map){

        var.getBehaviorModel().geneBehaviour(animal);

        //jeśli zwierzę może się poruszyć
        if (animal.energy - 1 >= 0){
            changeAnimalPosition(animal, var); //zmiana pozycji
            animalDinnerAndBreeding(animal, var, map);//obiad i dzieci
            animal.age += 1;
            var.getGardener().seedGrass(var, map);


        }
        else{
            animal.diedDate = animal.age;
            //dodajemy zwierzaka do listy zmarłych zwierząt
        }



    }


    protected void changeAnimalPosition(Animal animal, SimulationVar var){
        Direction[] genes = animal.genes;
        int currGeneIdx = animal.activeGeneIx;
        int mapHeight = var.getMapHeight();
        int mapWidth = var.getMapHeight();

        //o ten wektor się poruszamy
        Vector2d vectorToMove = genes[currGeneIdx].toUnitVector();
        Vector2d posAfterMovement = animal.position.add(vectorToMove);


        if (posAfterMovement.y < 0 || posAfterMovement.y >= mapHeight || posAfterMovement.x >= mapWidth || posAfterMovement.x < 0) {
            if (animal.energy - var.getMinEnergyForCopulation() >= 0) {
                animal.energy -= var.getMinEnergyForCopulation();
                Random generator = new Random();
                animal.changePosition(new Vector2d(generator.nextInt(mapWidth), generator.nextInt(mapHeight)));
            }

        }
    }
}

