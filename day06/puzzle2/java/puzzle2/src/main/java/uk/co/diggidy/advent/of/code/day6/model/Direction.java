package uk.co.diggidy.advent.of.code.day6.model;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    NORTH("^"), EAST(">"), SOUTH("v"), WEST("<");
    private final String symbol;
    Direction(String symbol) {
        this.symbol = symbol;
    }
    public static Optional<Direction> getDirectionBySymbol(String symbol) {
        return Arrays.stream(Direction.values())
                .filter(d -> symbol.equalsIgnoreCase(d.symbol))
                .findAny();
    }
}
