package uk.co.diggidy.advent.of.code.day8.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record Coordinate(int x, int y, int limitX, int limitY) {
    public String getCoords() {
        return String.format("%d,%d", x, y);
    }

    public List<String> findAntinode(Coordinate target) throws IllegalArgumentException {
        // y = mx + c
        // m = y2 - y1 / x2 - x1
        // c = y - mx
        BigDecimal dmx = BigDecimal.valueOf(this.x() - target.x());
        BigDecimal dmy = BigDecimal.valueOf(this.y() - target.y());
        BigDecimal m = dmy.divide(dmx,4,BigDecimal.ROUND_HALF_UP);
        BigDecimal c = BigDecimal.valueOf(this.y()).subtract(m.multiply(BigDecimal.valueOf(this.x())));

        List<String> antinodes = new ArrayList<>();
        for (int i = 0; i < limitX; i++) {
            BigDecimal j = m.multiply(BigDecimal.valueOf(i)).add(c);
            if (!hasDecimal(j) && j.intValue() >= 0 && j.intValue() < limitY) {
                antinodes.add(String.format("%d,%d", i, j.intValue()));
            }
        }
        return antinodes;
    }

    private boolean hasDecimal(BigDecimal number) {
        return number.compareTo(number.setScale(0, BigDecimal.ROUND_DOWN)) != 0;
    }
}
