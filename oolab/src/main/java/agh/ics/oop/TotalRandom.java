package agh.ics.oop;
import java.util.Random;

public class TotalRandom implements IMutationModel{
    @Override
    public void mutate(Animal child, Animal mommy, Animal daddy) {

        for (int i = 0; i < child.genes.length; i++){
            Random generator = new Random();

            int newGene = generator.nextInt(8);

            Direction geneToInsert = switch(newGene){
                case 0 -> Direction.NORTH;
                case 1 -> Direction.EASTNORTH;
                case 2 -> Direction.EAST;
                case 3 -> Direction.EASTSOUTH;
                case 4 -> Direction.SOUTH;
                case 5 -> Direction.WESTSOUTH;
                case 6 -> Direction.WEST;
                case 7 -> Direction.WESTNORTH;
                default -> null;
            };

            child.genes[i] = geneToInsert;

        }
    }

}
