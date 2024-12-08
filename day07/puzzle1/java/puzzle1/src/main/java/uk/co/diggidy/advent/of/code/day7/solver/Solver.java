package uk.co.diggidy.advent.of.code.day7.solver;

import org.graalvm.collections.Pair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Solver {

    private static final char[] POSSIBLE_SYMBOLS = {'+', '*'};

    public long solve(String fileName) {
        List<Pair<Long, List<String>>> corruptCalcs = getCalculations(fileName);
        return process(corruptCalcs);
    }

    private long process(List<Pair<Long, List<String>>> corruptCalcs) {
        long sum = 0L;
        for (Pair<Long, List<String>> pair : corruptCalcs) {
            final List<List<String>> combinations = getCombinations(pair.getRight());
            final String template = String.join("%s", pair.getRight());
            for (List<String> combination : combinations) {
                final String exp = String.format(template, combination.toArray());
                final double result = calculate(exp, pair.getLeft());
                if (result == pair.getLeft()) {
                    sum += result;
                    break;
                }
            }
        }
        return sum;
    }

    private double calculate(String exp, double expected) {
        String[] tokens = exp.split("(?<=[-+*/])|(?=[-+*/])");
        double result = expected;
        for (int i = tokens.length - 1; i >= 1; i -= 2) {
            String operator = tokens[i - 1];
            double nextNumber = Double.parseDouble(tokens[i]);

            switch (operator) {
                case "+" -> result -= nextNumber;
                case "-" -> result += nextNumber;
                case "*" -> result /= nextNumber;
                case "/" -> result *= nextNumber;
            }
            if (result < 0 || result != (long) result) {
                return 0;
            }
        }
        return Double.parseDouble(tokens[0]) == result ? expected : 0;
    }

    private List<List<String>> getCombinations(List<String> right) {
        int numberOfCombinations = (int) Math.pow(POSSIBLE_SYMBOLS.length, right.size() - 1);
        String template = "%1$" + (right.size() - 1) + "s";

        return IntStream.range(0, numberOfCombinations)
                .mapToObj(i -> String.format(template, Integer.toBinaryString(i)).replace(' ', '0'))
                .map(s -> s.replace('0', POSSIBLE_SYMBOLS[0]).replace('1', POSSIBLE_SYMBOLS[1]))
                .map(s -> s.chars().mapToObj(c -> String.valueOf((char) c)).toList())
                .toList();
    }

    private List<Pair<Long, List<String>>> getCalculations(String file) {
        try (InputStream inputStream = getClass().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines()
                    .map(x -> Pair.create(Long.parseLong(x.split(":")[0]), Arrays.stream(x.split(":")[1].stripLeading().split(" ")).toList()))
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
