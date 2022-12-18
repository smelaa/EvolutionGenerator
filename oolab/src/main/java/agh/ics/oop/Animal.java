package agh.ics.oop;

import java.util.Random;

import static java.lang.Math.round;

public class Animal implements IMapElement{
    protected Direction orientation;
    protected Vector2d position;
    protected int energy;
    public Direction[] genes;
    protected int activeGeneIx;
    protected int grassEaten=0;
    protected int diedDate=0;
    protected int age=0;
    protected int children=0;

    //konstruktor dla Adama i Ewy
    public Animal(SimulationVar var) {
        Random generator = new Random();
        orientation=Direction.numberToDirection(generator.nextInt(8));
        position=new Vector2d(generator.nextInt(var.getMapWidth()), generator.nextInt(var.getMapHeight()));
        energy=var.getAnimalStartEnergy();
        genes= new Direction[var.getGenomeLength()];
        for (int i=0;i<var.getGenomeLength();i++){
            genes[i]=Direction.numberToDirection(generator.nextInt(8));
        }
        activeGeneIx=generator.nextInt(var.getGenomeLength());
    }
    //konstruktor dla dzieciów
    public Animal(SimulationVar var, Animal mommy, Animal daddy){
        Random generator = new Random();
        orientation=Direction.numberToDirection(generator.nextInt(8));
        position=mommy.getPosition();
        genes= new Direction[var.getGenomeLength()];
        activeGeneIx=generator.nextInt(var.getGenomeLength());
        int fromMommy=mommy.genesToSucceed(daddy);
        if (generator.nextBoolean()){
            try {
                createGenome(mommy, daddy, fromMommy);
            }
            catch (IllegalArgumentException exception){
                exception.printStackTrace();
            }
        }
        else{
            try {
                createGenome(daddy,mommy,var.getGenomeLength()-fromMommy);
            }
            catch (IllegalArgumentException exception){
                exception.printStackTrace();
            }
        }
        var.getMutationModel().mutate(this);
    }

    private void createGenome(Animal parent1, Animal parent2, int intersection) throws IllegalArgumentException{
        if (intersection<=genes.length) {
            for (int i = 0; i < intersection; i++) {
                genes[i] = parent1.genes[i];
            }
            for (int i = intersection; i < genes.length; i++) {
                genes[i] = parent2.genes[i];
            }
        }
        else{throw new IllegalArgumentException("wrong genome intersection point");}
    }
    @Override
    public String getImageName() {
        return null;
    }

    public void changePosition(Vector2d newPosition){
        this.position=newPosition;
    }
    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isStronger(Animal other){
        if (energy>other.getEnergy()){return true;}
        return false;
    }

    public int genesToSucceed(Animal other){
        return round(energy/(other.getEnergy()+energy)*genes.length);
    }
}
