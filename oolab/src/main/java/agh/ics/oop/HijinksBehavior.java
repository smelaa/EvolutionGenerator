package agh.ics.oop;

import java.util.Random;

public class HijinksBehavior implements IBehaviorModel{
    @Override
    public void geneBehaviour(Animal animal){
        //losujemy liczbę od 0 do 9
        //w zależności od tego albo porusza się normalnie, albo losujemy
        Random generator = new Random();
        if (generator.nextInt(10) < 2){
            animal.activeGeneIx = generator.nextInt(animal.genes.length);
        }
        else{
            if (animal.activeGeneIx + 1 >= animal.genes.length){
                animal.activeGeneIx = 0;
            }
            else{
                animal.activeGeneIx += 1;
            }
        }
    }
}
