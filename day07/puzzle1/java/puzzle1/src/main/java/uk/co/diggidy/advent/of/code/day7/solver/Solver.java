package uk.co.diggidy.advent.of.code.day7.solver;

import org.graalvm.collections.Pair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Solver {

    private final static char[] POSSIBLE_SYMBOLS = {'+', '*'};

    public long solve(String fileName) {
        List<Pair<Long, List<String>>> corruptCalcs = getCalculations(fileName);
        return process(corruptCalcs);
    }

    private long process(List<Pair<Long, List<String>>> corruptCalcs) {
        long sum = 0l;
        for (Pair<Long, List<String>> pair : corruptCalcs) {
            final List<List<String>> combinations = getCombinations(pair.getRight());
            final String template = String.join("%s", pair.getRight());
            for (List<String> combination : combinations) {
                String exp = String.format(template, combination.toArray());
                double result = calculate(exp, pair.getLeft());
                if (result == pair.getLeft().doubleValue()) {
                    sum += result;
                    break;
                }
            }
        }

        return sum;
    }

    private double calculate(String exp, double expected) {
        String[] tokens = exp.split("(?<=[-+*/])|(?=[-+*/])");
        Double result = Double.valueOf(expected);
        for (int i = tokens.length - 1; i >= 1; i -= 2) {
            String operator = tokens[i-1];
            double nextNumber = Double.parseDouble(tokens[i]);

            // Apply the operator
            switch (operator) {
                case "+":
                    result -= nextNumber;
                    break;
                case "-":
                    result += nextNumber;
                    break;
                case "*":
                    result /= nextNumber;
                    break;
                case "/":
                    result *= nextNumber;
                    break;
            }
            if(result < 0 || result != result.longValue()) {
                return 0;
            }
        }
        if(Double.parseDouble(tokens[0]) != result){
            return 0;
        }
        return expected;
    }

    private List<List<String>> getCombinations(List<String> right) {
        final int numberOfCombinations = Double.valueOf(Math.pow(POSSIBLE_SYMBOLS.length, (right.size() - 1))).intValue();
        final String template = "%1$" + (right.size() - 1) + "s";

        return IntStream.range(0, numberOfCombinations)
                .mapToObj(i -> Integer.toBinaryString(i))
                .map(s -> String.format(template, s).replace(' ', '0'))
                .map(s -> s.replace('0', POSSIBLE_SYMBOLS[0]))
                .map(s -> s.replace('1', POSSIBLE_SYMBOLS[1]))
                .map(s -> s.chars().mapToObj(i -> String.valueOf((char) i)).toList())
                .toList();
    }


    private List<Pair<Long, List<String>>> getCalculations(String file) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            final List<Pair<Long, List<String>>> calculations = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                final String[] split = line.split(":");
                final List<String> numbers = Arrays.stream(split[1].stripLeading().split(" ")).toList();
                calculations.add(Pair.create(Long.parseLong(split[0]), numbers));

            }
            return calculations;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
