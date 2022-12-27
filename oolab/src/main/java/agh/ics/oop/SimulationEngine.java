package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

public class SimulationEngine implements ISimulationEngine{
    SimulationVar variables;
    private Map map;
    private int howManyDays = 0;
    private Statistics stats;



    public SimulationEngine(Map map, SimulationVar variables){
        this.map = map;
        this.variables = variables;
    }

    public void dayRitual(IMapType mapType, Map map){
        howManyDays += 1;
        //korowód
        int diedToday = 0;
        for (Animal animal: map.getAnimalsOnField()){
            if (animal.diedDate != 0){
                diedToday += 1;
                map.removeAnimal(animal);
            }
        }
        map.setDiedAnimals(map.getDiedAnimals() + diedToday); //łączna liczba zmarłych zwierzątek


        //poruszamy wszystkie zwierzątka
        for (Animal animal:map.getAnimalsOnField()){
            mapType.moveOnMap(animal, variables, map);
        }

        ArrayList<Animal> animals = map.getAnimalsOnField();
        animals.sort(Comparator.<Animal>comparingInt(el -> el.position.x).thenComparingInt(el -> el.position.y));
        Vector2d lastVector = new Vector2d(10^9, 10^9);

        //jedzonko i rozmnażanie
        for (Animal animal: map.getAnimalsOnField()){
            if (animal.getPosition() != lastVector){ //jeśli wektor zwierzątka nie jest identyczny do ostatniego -> aby nie sprawdzac kilka razy tej samej pozycji zwierząt
                lastVector = animal.getPosition();
                if (map.howManyAnimalsOnSpot(animal.getPosition())!= 1){ //jeśli jest wiecej niż 1 zwierze na danym polu

                    int howManyOnThisSpot = map.howManyAnimalsOnSpot(animal.getPosition());
                    ArrayList<Animal> possibleMatch = new ArrayList<>();
                    for(int i = 0; i < howManyOnThisSpot; i++){
                        possibleMatch.add(animals.get(i));
                    }
                    possibleMatch.sort(Comparator.<Animal>comparingInt(el -> -el.energy).thenComparingInt(el -> -el.age).thenComparingInt(el -> -el.children));

                    Animal winner = possibleMatch.get(0); //wygrał trawkę

                    if (map.isGrassThere(winner.position)) {
                        winner.energy += variables.getGrassEnergyProfit();
                        winner.grassEaten += 1;
                        map.removeGrass(new Grass(winner.position));
                    }

                    Animal secondWinner = possibleMatch.get(1); //ma szansę na dzieciątko z winnerem
                    if (winner.energy >= variables.getMinEnergyForCopulation() && secondWinner.energy >= variables.getMinEnergyForCopulation()) {
                        Animal baby = new Animal(variables, winner, secondWinner); //nowe bobo
                        map.placeAnimalOnMap(baby); //umieszczamy na mapie

                    }
                }
            }

        }

        //zasianie roślin
        variables.getGardener().seedGrass(variables, map);


    }

    protected int getDays(){
        return howManyDays;
    }

    @Override
    public void run() {
        Object[] statistics = new Object[]{
                stats.getAmountOfAnimals(map),
                stats.getAmountOfGrass(variables, this),
                stats.freeSpots(map, variables),
                stats.theMostCommonGenotype(map),
                stats.averageEnergyAlive(map),
                stats.averageEnergyDead(map)

        };

    }
}
