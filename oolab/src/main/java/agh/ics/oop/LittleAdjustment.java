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
        for (int i = 0; i < child.genes.length; i++){toMutate.add(i);}
        for (int i = 0; i < mutationsNumber; i++){
            int ix= generator.nextInt(toMutate.size());
            if (generator.nextBoolean()){
                child.genes[toMutate.remove(ix)]=child.genes[toMutate.remove(ix)].add(1);
            }
            else{
                child.genes[toMutate.remove(ix)]=child.genes[toMutate.remove(ix)].add(-1);
            }
        }

    }

}

