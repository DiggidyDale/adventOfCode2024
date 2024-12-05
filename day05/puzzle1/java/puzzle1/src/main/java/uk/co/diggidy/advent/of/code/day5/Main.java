package uk.co.diggidy.advent.of.code.day5;

import uk.co.diggidy.advent.of.code.day5.solver.Solver;

public class Main {
    public static void main(String[] args) {
        final Solver solver = new Solver();
        final int Solution = solver.solve("/input.txt");
        System.out.println("Number of orders:"+Solution);
    }
}