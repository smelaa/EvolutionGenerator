package agh.ics.oop;

import java.io.IOException;
import java.util.List;

public class SimulationVar {
    private final IMapType mapType;
    private final IHolyGardener gardener;
    private final IMutationModel mutationModel;
    private final IBehaviorModel behaviorModel;
    private final boolean printToFile;
    private final FileMenager fileMenager=new FileMenager();
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
    //bardzo brzydkie, aż nam wstyd:(
    public SimulationVar(List<String> var) throws IllegalArgumentException{
        if (var.size()!=15 && var.size()!=16){throw new IllegalArgumentException("wrong number of arguments");}
        switch(var.get(14)){
            case "false"->{
                if (var.size()==16){throw new IllegalArgumentException("wrong number of arguments");}
                printToFile=false;
            }
            case "true"-> {
                if (var.size()!=16){throw new IllegalArgumentException("wrong number of arguments");}
                printToFile=true;
                try{
                    fileMenager.setFileName(var.get(15));
                }
                catch(IOException ex){throw new IllegalArgumentException("wrong argument");}
            }
            default -> throw new IllegalArgumentException("wrong argument");
        }
        switch(var.get(0)){
            case "GlobMap"->this.mapType=new GlobMap();
            case "HellMap"->this.mapType=new HellMap();
            default -> throw new IllegalArgumentException("wrong argument");
        }
        switch(var.get(1)){
            case "ToxicCorpses"->this.gardener=new ToxicCorpses();
            case "ForestedEquators"->this.gardener=new ForestedEquators();
            default -> throw new IllegalArgumentException("wrong argument");
        }
        switch(var.get(2)){
            case "LittleAdjustment"->this.mutationModel=new LittleAdjustment();
            case "TotalRandom"->this.mutationModel=new TotalRandom();
            default -> throw new IllegalArgumentException("wrong argument");
        }
        switch(var.get(3)){
            case "HijinksBehavior"->this.behaviorModel=new HijinksBehavior();
            case "PredestinationBehavior"->this.behaviorModel=new PredestinationBehavior();
            default -> throw new IllegalArgumentException("wrong argument");
        }
        Integer[] ints=new Integer[10];
        for (int i=4;i<14;i++){
            try {
                if (Integer.parseInt(var.get(i))<=0){throw new IllegalArgumentException("wrong argument");}
                ints[i-4]= Integer.parseInt(var.get(i));
            }
            catch (NumberFormatException e) {throw new IllegalArgumentException("wrong argument");}
        }
        this.mapHeight=ints[0];
        this.mapWidth=ints[1];
        this.grassEnergyProfit=ints[2];
        this.minEnergyForCopulation=ints[3];
        this.animalStartEnergy=ints[4];
        this.dailyEnergyCost=ints[5];
        this.animalsAtStart=ints[6];
        this.grassSpawnedEveryday=ints[7];
        this.refreshTime=ints[8];
        this.genomeLength=ints[9];
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
    public boolean isPrintToFile() {
        return printToFile;
    }
    public FileMenager getFileMenager() {
        return fileMenager;
    }

}
