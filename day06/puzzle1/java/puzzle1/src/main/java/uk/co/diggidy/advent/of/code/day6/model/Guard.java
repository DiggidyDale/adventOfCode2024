package uk.co.diggidy.advent.of.code.day6.model;

import org.graalvm.collections.Pair;

public class Guard {
    private int xCoord;
    private int yCoord;
    private Direction direction;

    public Guard(int x, int y, Direction direction) {
        this.xCoord = x;
        this.yCoord = y;
        this.direction = direction;
    }

    public Pair<Integer, Integer> getNextPosition(){
        return switch (direction){
            case NORTH -> Pair.create(this.xCoord, this.yCoord -1);
            case SOUTH -> Pair.create(this.xCoord, this.yCoord +1);
            case EAST ->  Pair.create(this.xCoord +1, this.yCoord);
            case WEST ->  Pair.create(this.xCoord -1, this.yCoord);
        };
    }

    public Pair<Integer, Integer> getCurrentPosition(){
        return Pair.create(this.xCoord, this.yCoord);
    }

    public void updatePosition(int x, int y){
        this.xCoord = x;
        this.yCoord = y;
    }

    public void updateDirection(){
        this.direction = switch (direction){
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

    public Direction getDirection() {
        return direction;
    }
}
