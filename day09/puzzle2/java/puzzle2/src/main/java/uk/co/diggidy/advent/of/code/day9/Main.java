package uk.co.diggidy.advent.of.code.day9;

import uk.co.diggidy.advent.of.code.day9.solver.Solver;

public class Main {
    public static void main(String[] args) {
        final Solver solver = new Solver();
        long result = solver.solve("/input1.txt");
        System.out.println("Checksum: "+ result);
    }
}