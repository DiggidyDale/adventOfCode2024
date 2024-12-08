package uk.co.diggidy.advent.of.code.day8;


import uk.co.diggidy.advent.of.code.day8.solver.Solver;

public class Main {
    public static void main(String[] args) {
        final Solver solver = new Solver();
        int result = solver.solve("/input.txt");
        System.out.println("Number of antinodes: "+ result);
    }
}