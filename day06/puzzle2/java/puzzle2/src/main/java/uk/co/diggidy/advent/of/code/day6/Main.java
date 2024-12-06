package uk.co.diggidy.advent.of.code.day6;

import uk.co.diggidy.advent.of.code.day6.solver.Solver;

public class Main {
    public static void main(String[] args) {
        final Solver solver = new Solver();
        final long solution = solver.solve("/input.txt");
        System.out.println("Number of locations: " + solution);
    }
}