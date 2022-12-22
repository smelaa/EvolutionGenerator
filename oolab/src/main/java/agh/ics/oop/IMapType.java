package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;

public interface IMapType {
    void moveOnMap(Animal animal, SimulationVar var, Map map);

    default void animalDinnerAndBreeding(Animal animal, SimulationVar var, Map map) {

        ArrayList<Animal> possibleMatch = new ArrayList<>();
        possibleMatch.add(animal);
        //allAnimals = listaZeZwierzaczkami
        //for (animall : allAnimals){
//            if (animal.position == animall.position){
//                possibleMatch.add(animall);
//            }
        //}

        if (possibleMatch.size() != 1) {
            possibleMatch.sort(Comparator.<Animal>comparingInt(el -> -el.energy).thenComparingInt(el -> -el.age).thenComparingInt(el -> -el.children));
        }
        Animal winner = possibleMatch.get(0);

        if (map.isGrassThere(winner.position)) {
            winner.energy += var.getGrassEnergyProfit();
        }

        winner.energy += var.getGrassEnergyProfit();
        Animal secondWinner = possibleMatch.get(1);
        if (winner.energy >= var.getMinEnergyForCopulation() && secondWinner.energy >= var.getMinEnergyForCopulation()) {
            Animal baby = new Animal(var, winner, secondWinner);
            map.placeAnimal(winner.position, baby);
        }

    }
}
