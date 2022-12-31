package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class HellMap implements IMapType {


    @Override
    public void moveOnMap(Animal animal, SimulationVar var, Map map){

        var.getBehaviorModel().geneBehaviour(animal);

        //jeśli zwierzę może się poruszyć
        if (animal.energy - var.getDailyEnergyCost() >= 0){
            animal.energy -= var.getDailyEnergyCost();
            changeAnimalPosition(animal, var, map); //zmiana pozycji

        }
        else{
            animal.diedDate = animal.getAge();
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


        if (posAfterMovement.y < 0 || posAfterMovement.y >= mapHeight || posAfterMovement.x >= mapWidth || posAfterMovement.x < 0) {
            if (animal.energy - var.getMinEnergyForCopulation() >= 0) {
                animal.energy -= var.getMinEnergyForCopulation();
                Random generator = new Random();
                posAfterMovement = new Vector2d(generator.nextInt(mapWidth), generator.nextInt(mapHeight));
            }
            else{
                animal.diedDate = animal.age;
                map.setDiedAnimals(map.getDiedAnimals() + 1);
                map.addToDiedList(animal);
                return;
            }

        }
        map.setAnimalsOnField(animal.getPosition(), posAfterMovement);
        animal.changePosition(posAfterMovement);
    }
}

