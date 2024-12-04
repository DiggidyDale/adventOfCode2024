package uk.co.diggidy.advent.of.code.day4.model;

import java.util.List;

public record Matrix(List<List<String>> matrix) {

    public String get(int row, int column) {
        return matrix.get(row).get(column).toLowerCase();
    }
}
