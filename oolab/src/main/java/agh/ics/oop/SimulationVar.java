package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationVar {
    private final IMapType mapType;
    private final IHolyGardener gardener;
    private final IMutationModel mutationModel;
    private final IBehaviorModel behaviorModel;
    private Statistics stats;
    private final int mapHeight;
    private final int mapWidth;
    private final int grassEnergyProfit;
    private final int minEnergyForCopulation;
    private final int animalStartEnergy;
    private final int dailyEnergyCost;
    private final int animalsAtStart;
    private final int grassSpawnedEveryday;
    private final int refreshTime;
    private final int genomeLength;
    //trzeba tu zrobić jakiś konstruktor, który byłby w stanie wczytać te rzeczy z pliku
    public SimulationVar(List<String> var) {

        this.genomeLength = 0;
        this.stats=null;
        this.mapType = null;
        this.gardener = null;
        this.mutationModel = null;
        this.behaviorModel = null;
        this.mapHeight = 0;
        this.mapWidth = 0;
        this.grassEnergyProfit = 0;
        this.minEnergyForCopulation = 0;
        this.animalStartEnergy = 0;
        this.dailyEnergyCost = 0;
        this.animalsAtStart = 0;
        this.grassSpawnedEveryday = 0;
        this.refreshTime = 0;
    }


    public IMapType getMapType() {
        return mapType;
    }

    public IHolyGardener getGardener() {
        return gardener;
    }

    public IMutationModel getMutationModel() {
        return mutationModel;
    }

    public IBehaviorModel getBehaviorModel() {
        return behaviorModel;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getGrassEnergyProfit() {
        return grassEnergyProfit;
    }

    public int getMinEnergyForCopulation() {
        return minEnergyForCopulation;
    }

    public int getAnimalStartEnergy() {
        return animalStartEnergy;
    }

    public int getDailyEnergyCost() {
        return dailyEnergyCost;
    }

    public int getAnimalsAtStart() {
        return animalsAtStart;
    }

    public int getGrassSpawnedEveryday() {
        return grassSpawnedEveryday;
    }

    public int getRefreshTime() {
        return refreshTime;
    }

    public int getGenomeLength() {
        return genomeLength;
    }

    public Statistics getStats() {
        return stats;
    }
}
