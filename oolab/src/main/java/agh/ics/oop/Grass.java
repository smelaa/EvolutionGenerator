package agh.ics.oop;

import java.util.HashSet;
import java.util.Random;


public class Grass implements IMapElement{
    private Vector2d position;
    public Grass(Vector2d position){
        this.position=position;
    }

    @Override
    public String getImageName() {
        return null;
    }

    public Vector2d getPosition() {
        return position;
    }
}
