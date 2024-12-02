package uk.co.diggidy.advent.of.code.day2.solver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {

    public int solve(String fileName) {
        final List<List<Integer>> reports = getReports(fileName);
        return analyseReports(reports);
    }

    private int analyseReports(final List<List<Integer>> reports) {
        return reports.stream()
                .mapToInt(report -> analyseReport(report) ? 1 : 0)
                .sum();
    }

    private boolean analyseReport(final List<Integer> report) {
        if (analyseSingleReport(report)) {
            return true;
        }
        for (int i = 0; i < report.size(); i++) {
            List<Integer> copy = new ArrayList<>(report);
            copy.remove(i);
            if (analyseSingleReport(copy)) {
                return true;
            }
        }
        return false;
    }

    private boolean analyseSingleReport(final List<Integer> report) {
        boolean ascending = report.get(0) < report.get(1);
        for (int i = 0; i < report.size(); i++) {
            final Integer prev = i == 0 ? null : report.get(i - 1);
            final Integer current = report.get(i);
            if (prev != null) {
                if ((ascending && prev >= current) || (!ascending && prev <= current)) {
                    return false;
                }
                if (Math.abs(prev - current) > 3) {
                    return false;
                }
            }
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

