package uk.co.diggidy.advent.of.code.day2.solver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {

    public int solve(String fileName){
        final List<List<Integer>> reports = getReports(fileName);
        return analyseReports(reports);
    }

    private int analyseReports(final List<List<Integer>> reports) {
        return reports.stream()
                .mapToInt(report -> analyseReport(report) ? 1 : 0)
                .sum();
    }

    private boolean analyseReport(final List<Integer> report) {
        Integer prev = null;
        boolean ascending = report.get(0) < report.get(1);
        for(int current : report) {
            if (prev != null) {
                if ((ascending && prev >= current) || (!ascending && prev <= current)) {
                    return false;
                }
                if (Math.abs(prev - current) > 3) {
                    return false;
                }
            }
            prev = current;
        }
        return true;
    }

    private List<List<Integer>> getReports(final String fileName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines()
                    .map(line -> Arrays.stream(line.trim().split("\s+"))
                            .mapToInt(Integer::parseInt).boxed()
                            .toList())
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
