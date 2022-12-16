package agh.ics.oop;

public class Animal implements IMapElement{
    protected Direction orientation;
    protected Vector2d position;
    protected int energy;
    protected Direction[] genes;
    protected int activeGeneIx;
    protected int grassEaten;
    protected int diedDate;
    protected int age;
    protected int children;

    //konstruktor dla Adama i Ewy
    public Animal(IMutationModel mutationModel) {
        //tu trzeba wszystko losować
    }
    //konstruktor dla dzieciów
    public Animal(IMutationModel mutationModel, Animal mommy, Animal daddy){
        //wpisać konstruktor
        mutationModel.mutate(this, mommy, daddy);
    }

    @Override
    public String getImageName() {
        return null;
    }

    public void changePosition(Vector2d newPosition){
        this.position=newPosition;
    }
}
