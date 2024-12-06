package uk.co.diggidy.advent.of.code.day6.model;

import org.graalvm.collections.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    Map<String, String> map = new HashMap<>();
    String initialPosition;
    Direction initialDirection;

    public Grid(Map<String, String> map, String initialPosition, Direction direction) {
        this.map = new HashMap<>(map);
        this.initialPosition = String.copyValueOf(initialPosition.toCharArray());
        this.initialDirection = direction;
    }

    public Grid(List<List<String>> input){
        for(int y = 0; y < input.size(); y++){
            for(int x = 0; x < input.get(y).size(); x++){
                String value = input.get(y).get(x);
                final String coords = String.format("%d,%d", x, y);
                if(!value.equals(".") && !value.equals("#")){
                    initialPosition = coords;
                    initialDirection = Direction.getDirectionBySymbol(value)
                            .orElseThrow(() ->
                                    new IllegalArgumentException("Unable to work out the direction"));
                    value = ".";
                }
                map.put(coords, value);
            }
        }
    }

    public boolean isInMap(int x, int y){
        return map.containsKey(String.format("%d,%d", x, y));
    }

    public boolean isObstacle(int x, int y) {
        return map.get(String.format("%d,%d", x, y)).equals("#");
    }

    public void addMarker(int x, int y) {
        map.put(String.format("%d,%d", x, y), "x");
    }

    public Pair<Integer, Integer> getInitialPosition() {
        final String[] split = initialPosition.split(",");
        return Pair.create(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public Direction getInitialDirection() {
        return initialDirection;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public Grid cloneGrid() throws CloneNotSupportedException {
        return new Grid(this.map, this.initialPosition, initialDirection);
    }

    public void addObstacle(String coords) {
        this.map.put(coords, "#");
    }
}