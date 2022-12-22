package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;

public class GlobMap implements IMapType{


    @Override
    public void moveOnMap(Animal animal, SimulationVar var, Map map){
        Direction[] genes = animal.genes;
        int currGeneIdx = animal.activeGeneIx;
        int mapHeight = var.getMapHeight();
        int mapWidth = var.getMapHeight();



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


    protected void animalDinnerAndBreeding(Animal animal, SimulationVar var, Map map){
        //jeśli najpierw obsługujemy jedno zwierzątko, a dopiero potem wszystkie inne (a tak mi się wydaje),
        //to nie wiem, jak to zrobić tylko w tej klasie :( Ale to do ustalenia

        if (map.isGrassThere(animal.position)){
            ArrayList<Animal> possibleMatch = new ArrayList<>();
            possibleMatch.add(animal);
            //allAnimals = listaZeZwierzaczkami
            //for (animall : allAnimals){
//            if (animal.position == animall.position){
//                possibleMatch.add(animall);
//            }
            //}

            //-, bo sortuje rosnąco, a my chcemy malejąco
            if (possibleMatch.size() != 1){
                possibleMatch.sort(Comparator.<Animal>comparingInt(el -> -el.energy).thenComparingInt(el->-el.age).thenComparingInt(el->-el.children));
            }
            Animal winner = possibleMatch.get(0);
            winner.energy += var.getGrassEnergyProfit();
            Animal secondWinner = possibleMatch.get(1);
            if (winner.energy >= var.getMinEnergyForCopulation() && secondWinner.energy >= var.getMinEnergyForCopulation()){
                Animal baby = new Animal(var, winner, secondWinner);
                map.placeAnimal(winner.position, baby);
            }

        }





    }

}
