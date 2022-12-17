package agh.ics.oop;

public enum Direction {
    NORTH,
    EASTNORTH,
    EAST,
    EASTSOUTH,
    SOUTH,
    WESTSOUTH,
    WEST,
    WESTNORTH;

    public Vector2d toUnitVector(){
        return switch(this){
            case NORTH -> new Vector2d(0,1);
            case EASTNORTH -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case EASTSOUTH -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case WESTSOUTH -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case WESTNORTH -> new Vector2d(-1,1);
            default -> null;
        };
    }

    public int toNumber(){
        return switch(this){
            case NORTH -> 0;
            case EASTNORTH -> 1;
            case EAST -> 2;
            case EASTSOUTH -> 3;
            case SOUTH -> 4;
            case WESTSOUTH -> 5;
            case WEST -> 6;
            case WESTNORTH -> 7;
        };
    }
    public static Direction numberToDirection(int n){
        return switch(n%8){
            case 0 -> NORTH;
            case 1 -> EASTNORTH;
            case 2 -> EAST;
            case 3 -> EASTSOUTH;
            case 4 -> SOUTH;
            case 5 -> WESTSOUTH;
            case 6 -> WEST;
            case 7 -> WESTNORTH;
            default -> NORTH;
        };
    }

    public Direction add(int n){
        int sumInt=this.toNumber()+n;
        return numberToDirection(sumInt);
    }

    public Direction add(Direction d){
        int sumInt=this.toNumber()+d.toNumber();
        return numberToDirection(sumInt);
    }
}
