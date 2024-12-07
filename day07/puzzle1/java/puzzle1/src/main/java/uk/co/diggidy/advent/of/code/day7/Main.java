package uk.co.diggidy.advent.of.code.day7;

import uk.co.diggidy.advent.of.code.day7.solver.Solver;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final Solver solver = new Solver();
        long result = solver.solve("/input.txt");
        System.out.println("Result: " + result);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + "ms" );
    }
}
//
//Result: 12839601725877
//Time taken: 1262ms