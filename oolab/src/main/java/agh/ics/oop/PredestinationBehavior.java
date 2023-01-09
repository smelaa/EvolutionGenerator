package agh.ics.oop;

public class PredestinationBehavior implements IBehaviorModel{
    @Override
    public void geneBehaviour(Animal animal){
        if (animal.activeGeneIx + 1 >= animal.genes.length){
            animal.activeGeneIx = 0;
        }
        else{
            animal.activeGeneIx += 1;
        }
    }
}
