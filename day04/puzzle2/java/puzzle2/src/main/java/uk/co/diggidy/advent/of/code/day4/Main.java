package uk.co.diggidy.advent.of.code.day4;

import uk.co.diggidy.advent.of.code.day4.solver.Solver;

public class Main {
    public static void main(String[] args) {
        final Solver solver = new Solver();
        final int solution = solver.solve("/input.txt");
        System.out.println("Number of xmas in wordsearch is : "+solution);
    }
}