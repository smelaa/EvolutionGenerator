package agh.ics.oop;

import agh.ics.oop.gui.MainViewController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;
import static java.lang.Math.round;

public class Animal {
    protected Direction orientation;
    protected Vector2d position;
    protected int energy;
    public Direction[] genes;
    protected int activeGeneIx;
    protected int grassEaten=0;
    protected int diedDate=0;
    protected int age=0;
    protected int children=0;
    private Map map;

    //konstruktor dla Adama i Ewy
    public Animal(SimulationVar var,Map map) {
        this.map=map;
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
        map=mommy.getMap();
        orientation=Direction.numberToDirection(generator.nextInt(8));
        position=mommy.getPosition();
        genes= new Direction[var.getGenomeLength()];
        activeGeneIx=generator.nextInt(var.getGenomeLength());
        int energy1 = mommy.energy / 2;
        int energy2 = daddy.energy / 2;
        energy = energy1 + energy2;
        mommy.energy -= energy1;
        daddy.energy -= energy2;
        mommy.children+=1;
        daddy.children+=1;
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

    public Circle getImage(Double size, MainViewController follower) {
        Color color;
        try{
            color=new Color((double) (map.getMaxEnergy() - energy) /map.getMaxEnergy(),0, (double) energy /map.getMaxEnergy(),1);
        }
        catch(Exception ex){color=Color.BLACK; ex.printStackTrace(); ex.printStackTrace();}
        Circle circle = new Circle(size/2, color);
        if (genesToString().equals(map.getTheMostPopularGenome())){
            circle.setStroke(Color.valueOf("#fff705"));
        }
        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                followMe(follower);
            }
        });
        return circle;
    }
    public Map getMap() {
        return map;
    }

    public int getAge(){
        return age;
    }
    private void followMe(MainViewController follower){follower.follow(this);}

    public String genesToString(){
        String genome = "";
        for (Direction gene : genes){
            genome += gene.toString();
        }
        return genome;
    }

    public Direction getActiveJeans(){
        return genes[activeGeneIx];
    }
    public int getGrassEaten() {
        return grassEaten;
    }
    public int getChildren() {
        return children;
    }

    public String diedDateToString() {
        if (diedDate==0){return "I'm still standing";}
        else{return String.valueOf(diedDate);}
    }

    public String ageToString(){
        if (diedDate==0){return String.valueOf(age);}
        else{return "Another one bites a dust";}
    }

    public boolean isAlive(){
        return diedDate==0;
    }

}
