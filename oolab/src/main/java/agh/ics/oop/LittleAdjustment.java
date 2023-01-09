package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class LittleAdjustment implements IMutationModel{
    @Override
    public void mutate(Animal child) {
        Random generator = new Random();
        int mutationsNumber=generator.nextInt(child.genes.length);
        List<Integer> toMutate= new ArrayList<>();
        for (int i = 0; i < child.genes.length; i++){toMutate.add(child.genes[i].toNumber());}
        for (int i = 0; i < mutationsNumber; i++){
            int ix= generator.nextInt(toMutate.size());
            if (generator.nextBoolean()){
                if (child.genes[ix].toNumber() == 7){
                    child.genes[ix] = Direction.NORTH;
                }
                else{
                    child.genes[ix]=Direction.numberToDirection(toMutate.get(ix) + 1);
                }
            }
            else{
                if(child.genes[ix].toNumber() == 0){
                    child.genes[ix] = Direction.NORTHWEST;
                }
                child.genes[ix]=Direction.numberToDirection(toMutate.get(ix) - 1);
            }
        }

    }

}

