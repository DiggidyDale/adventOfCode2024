package uk.co.diggidy.advent.of.code.day2;

import uk.co.diggidy.advent.of.code.day2.solver.Solver;

public class Main {
    public static void main(String[] args) {
        final Solver solver = new Solver();
        final int solution = solver.solve("/input.txt");
        System.out.println("Number of safe reports: " + solution);

    }
}