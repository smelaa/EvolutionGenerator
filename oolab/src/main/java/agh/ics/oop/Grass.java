package agh.ics.oop;

public class Grass implements IMapElement{
    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public String getImageName() {
        return null;
    }
}
