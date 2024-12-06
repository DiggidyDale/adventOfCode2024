package uk.co.diggidy.advent.of.code.day6.solver;

import org.graalvm.collections.Pair;
import uk.co.diggidy.advent.of.code.day6.model.Grid;
import uk.co.diggidy.advent.of.code.day6.model.Guard;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solver {

    public long solve(String fileName) {
        final Grid grid = getGrid(fileName);
        return process(grid);

    }

    private long process(final Grid grid) {
        final Guard guard = new Guard(
                grid.getInitialPosition().getLeft(),
                grid.getInitialPosition().getRight(),
                grid.getInitialDirection());
        try{
            return getAllLocations(grid, guard);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private int getAllLocations(final Grid grid, final Guard guard) throws CloneNotSupportedException {
        final Pair<Integer, List<String>> traversedGrid = traverseGrid(grid.cloneGrid(),
                new Guard(grid.getInitialPosition().getLeft(), grid.getInitialPosition().getRight(), grid.getInitialDirection()),
                true);
        return traversedGrid.getRight().stream()
                .filter(key -> !key.equalsIgnoreCase(String.format("%d,%d", grid.getInitialPosition().getLeft(), grid.getInitialPosition().getRight())))
                .mapToInt(key -> this.traverseGridForNewObstacle(grid, guard, key))
                .sum();
    }

    private long findMarkers(final Grid grid) {
        return grid.getMap().values().stream()
                .filter(e -> e.equalsIgnoreCase("x"))
                .count();
    }

    private int traverseGridForNewObstacle(final Grid grid, final Guard guard, final String newObstacle) {
        try {
            final Grid clonedGrid = grid.cloneGrid();
            clonedGrid.addObstacle(newObstacle);
            return traverseGrid(
                    clonedGrid,
                    new Guard(grid.getInitialPosition().getLeft(),
                            grid.getInitialPosition().getRight(),
                            grid.getInitialDirection()),
                    false).getLeft();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private Pair<Integer, List<String>> traverseGrid(final Grid grid, final Guard guard, boolean markMap) {
        boolean onTheMap = true;
        int count = 0;
        final Set<String>  states = new HashSet<>();
        while(onTheMap) {
            final Pair<Integer, Integer> currentPosition = guard.getCurrentPosition();
            if(markMap){
                grid.addMarker(currentPosition.getLeft(), currentPosition.getRight());
            }
            final Pair<Integer, Integer> nextPosition = guard.getNextPosition();
            if(!grid.isInMap(nextPosition.getLeft(), nextPosition.getRight())){
                onTheMap = false;
            } else if(grid.isObstacle(nextPosition.getLeft(), nextPosition.getRight())) {
                guard.updateDirection();
            } else {
                guard.updatePosition(nextPosition.getLeft(), nextPosition.getRight());
                if(!markMap){
                    if(states.contains(guard.getState())){
                        count++;
                        break;
                    }
                    states.add(guard.getState());
                }
            }
        }
        List<String> coords = Collections.EMPTY_LIST;
        if(markMap){
            coords = grid.getMap().entrySet().stream()
                    .filter(e -> e.getValue().equalsIgnoreCase("x"))
                    .map(Map.Entry::getKey)
                    .toList();

        }
        return Pair.create(count, coords);
    }


    private Grid getGrid(final String fileName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            List<List<String>> map = reader.lines()
                    .map(line -> Arrays.stream(line.trim().split("")).toList())
                    .toList();

            return new Grid(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
