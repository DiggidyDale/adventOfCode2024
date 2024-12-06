package uk.co.diggidy.advent.of.code.day6.solver;

import org.graalvm.collections.Pair;
import uk.co.diggidy.advent.of.code.day6.model.Grid;
import uk.co.diggidy.advent.of.code.day6.model.Guard;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Solver {

    public long solve(String fileName) {
        final Grid grid = getGrid(fileName);
        return process(grid);

    }

    private long process(final Grid grid) {
        final Guard guard = new Guard(
                grid.getInitialPosition().getLeft(),
                grid.getInitialPosition().getRight(),
                grid.getDirection());
        traverseGrid(grid, guard);
        return findMarkers(grid);
    }

    private long findMarkers(final Grid grid) {
        return grid.getMap().values().stream()
                .filter(e -> e.equalsIgnoreCase("x"))
                .count();
    }

    private void traverseGrid(final Grid grid, final Guard guard) {
        boolean onTheMap = true;
        while(onTheMap) {
            final Pair<Integer, Integer> currentPosition = guard.getCurrentPosition();
            grid.addMarker(currentPosition.getLeft(), currentPosition.getRight());
            final Pair<Integer, Integer> nextPosition = guard.getNextPosition();
            if(!grid.isInMap(nextPosition.getLeft(), nextPosition.getRight())){
                onTheMap = false;
            } else if(grid.isObstacle(nextPosition.getLeft(), nextPosition.getRight())) {
                guard.updateDirection();
            } else {
                guard.updatePosition(nextPosition.getLeft(), nextPosition.getRight());
            }
        }
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
