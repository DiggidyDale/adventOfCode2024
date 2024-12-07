package uk.co.diggidy.advent.of.code.day7;

import uk.co.diggidy.advent.of.code.day7.solver.Solver;

public class Main {
    public static void main(String[] args) {
        final Solver solver = new Solver();
        long result = solver.solve("/input.txt");
        System.out.println("Result: " + result);
    }
}