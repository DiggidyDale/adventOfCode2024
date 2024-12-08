package uk.co.diggidy.advent.of.code.day8.model;

public class Node {
    private boolean antinode;
    private String antenna;
    private Coordinate coord;

    public Node(boolean antinode, String antenna, Coordinate coord) {
        this.antenna = antenna;
        this.antinode = antinode;
    }

    public Node(String antenna, int x, int y, int limitX, int limitY) {
        this.antenna = antenna;
        this.antinode = false;
        this.coord = new Coordinate(x, y, limitX, limitY);
    }

    public Coordinate getCoord() {
        return coord;
    }

    public boolean isAntinode() {
        return antinode;
    }

    public void addAntinode() {
        this.antinode = true;
    }

    public String getAntenna() {
        return antenna;
    }

    public void setAntenna(String antenna) {
        this.antenna = antenna;
    }
}
