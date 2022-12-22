package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;

public class GlobMap implements IMapType{


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


        // jeśli zwierzątko będzie na samym krańcu mapy, a będzie miało iść na skos, to jednocześnie wyjdzie na równik
        // i na biegun, więc nie wiem, co wtedy mamy robić - napisałam wersję z odwrotnym kierunkiem
        // wtedy można usunąć te warunki i po prostu sprawdzac tylko ten z y
        if (posAfterMovement.y < 0 || posAfterMovement.y >= mapHeight){
            animal.orientation = animal.orientation.opposite(animal.orientation);
            posAfterMovement  = animal.position;
        }
        //czy równik
        else if (posAfterMovement.x >= mapWidth || posAfterMovement.x < 0){
            if (posAfterMovement.x >= mapWidth){
                posAfterMovement.x = 0;
            }
            else{
                posAfterMovement.x = mapWidth - 1;
            }
        }

        animal.changePosition(posAfterMovement);
    }

}
