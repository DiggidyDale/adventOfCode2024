package uk.co.diggidy.advent.of.code.day1;

import uk.co.diggidy.advent.of.code.day1.solver.Solver;

public class Main {

    public static void main(String[] args) {
        final Solver solver = new Solver();
        final Long result = solver.solve("/input.txt");

        System.out.println("Similarity value: "+ result);
    }
}