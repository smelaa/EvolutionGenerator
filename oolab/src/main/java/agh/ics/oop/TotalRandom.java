package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TotalRandom implements IMutationModel{
    @Override
    public void mutate(Animal child) {
        Random generator = new Random();
        int mutationsNumber=generator.nextInt(child.genes.length);
        List<Integer> toMutate= new ArrayList<>();
        for (int i = 0; i < child.genes.length; i++){toMutate.add(i);}
        for (int i = 0; i < mutationsNumber; i++){
            Direction geneToInsert = Direction.numberToDirection(generator.nextInt(8));
            int ix= generator.nextInt(toMutate.size());
            child.genes[toMutate.remove(ix)] = geneToInsert;
        }
    }

}
