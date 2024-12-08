package uk.co.diggidy.advent.of.code.day8.model;

import org.graalvm.collections.Pair;

import java.util.List;

public record Coordinate(int x, int y, int limitX, int limitY) {
    public String getCoords() {
        return String.format("%d,%d", x, y);
    }

    public List<String> findAntinode(Coordinate target) throws IllegalArgumentException {
        int dmx = this.x() - target.x();
        int dmy = this.y() - target.y();
        int desiredX1 = this.x() + dmx;
        int desiredY1 = this.y() + dmy;
        int desiredX2 = target.x() - dmx;
        int desiredY2 = target.y() - dmy;
        String desired1 = "", desired2 = "";
        if (desiredX1 >= 0 && desiredX1 <= limitX && desiredY1 >= 0 && desiredY1 <= limitY) {
            desired1 = String.format("%d,%d", desiredX1, desiredY1);
        }
        if (desiredX2 >= 0 && desiredX2 <= limitX && desiredY2 >= 0 && desiredY2 <= limitY) {
            desired2 = String.format("%d,%d", desiredX2, desiredY2);
        }
        return List.of(desired1, desired2);
    }
}
