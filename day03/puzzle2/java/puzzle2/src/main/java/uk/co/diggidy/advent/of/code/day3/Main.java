package uk.co.diggidy.advent.of.code.day3;

import uk.co.diggidy.advent.of.code.day3.Solver.Solver;

public class Main {
    public static void main(String[] args) {
        final Solver solver = new Solver();
        final int solution = solver.solve("/input.txt");
        System.out.println("Result: " + solution);

    }
}