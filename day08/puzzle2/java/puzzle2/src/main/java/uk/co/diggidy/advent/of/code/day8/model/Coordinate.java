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
        List<String> antinodes = new ArrayList<>();
        int dx = target.x() - this.x();
        int dy = target.y() - this.y();
        if (dx == 0) {
            for (int j = 0; j < limitY; j++) {
                antinodes.add(String.format("%d,%d", this.x, j));
            }
        } else {
            double m = (double) dy / dx;
            double c = this.y - m * this.x;


            for (int i = 0; i < limitX; i++) {
                double j = (m * i) + c;
                if(j >= 0 && j < limitY && Math.abs(j- Math.round(j)) < 1e-9) {
                    antinodes.add(String.format("%d,%d", i, (int)j));
                }
            }
        }
        return antinodes;
    }

    private boolean isLessThan(BigDecimal a, int b) {
        return a.compareTo(BigDecimal.valueOf(b).setScale(3, BigDecimal.ROUND_HALF_UP)) < 0;
    }

    private boolean isGreaterThanOrEqualToZero(BigDecimal d) {
        return d.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean isRemainderSubstantial(BigDecimal value) {
        return value.remainder(BigDecimal.ONE).compareTo(BigDecimal.valueOf(0.001)) < 0 || value.remainder(BigDecimal.ONE).compareTo(BigDecimal.valueOf(0.999)) > 0;
    }

    private boolean hasDecimal(BigDecimal number) {
        return number.compareTo(number.setScale(2, BigDecimal.ROUND_HALF_UP)) != 0;
    }
}
