package uk.co.diggidy.advent.of.code.day7.solver;

import org.graalvm.collections.Pair;
import org.graalvm.polyglot.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {

    private final static char[] POSSIBLE_SYMBOLS = {'+', '*','|'};

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
                    double result = calculate(exp);
                        if (result == pair.getLeft().doubleValue()) {
                            sum += result;
                            break;
                        }
                }
            }

        return sum;
    }

    private double calculate(String exp) {
        String[] tokens = exp.split("(?<=[-+*/|])|(?=[-+*/|])");
        Double result = Double.parseDouble(tokens[0]);
        for(int i =1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double nextNumber = Double.parseDouble(tokens[i + 1]);

            // Apply the operator
            switch (operator) {
                case "+":
                    result += nextNumber;
                    break;
                case "-":
                    result -= nextNumber;
                    break;
                case "*":
                    result *= nextNumber;
                    break;
                case "/":
                    result /= nextNumber;
                    break;
                case "|":
                    result = Double.valueOf(String.valueOf(result.longValue())+nextNumber);
                    break;
            }
        }

        return result;
    }

    private List<List<String>> getCombinations(List<String> right) {
        final int numberOfCombinations = Double.valueOf(Math.pow(POSSIBLE_SYMBOLS.length, (right.size() - 1))).intValue();
        final String template = "%1$" + (right.size() - 1) + "s";
//        List<String> combinations = new ArrayList<>();
//        for (int i = 0; i < numberOfCombinations; i++) {
//            String binaryString = Integer.toBinaryString(i);
//            String padded = String.format(template, binaryString).replace(' ', '0');
//            String combination = padded.replace('0', POSSIBLE_SYMBOLS[0]).replace('1', POSSIBLE_SYMBOLS[1]);
//            combinatio;
//
//        }
//        return combinations;
        return IntStream.range(0, numberOfCombinations)
                .mapToObj(i -> Integer.toString(i, 3))
                .map(s -> String.format(template, s).replace(' ', '0'))
                .map(s -> s.replace('0', POSSIBLE_SYMBOLS[0]))
                .map(s -> s.replace('1', POSSIBLE_SYMBOLS[1]))
                .map(s -> s.replace('2', POSSIBLE_SYMBOLS[2]))
                .map(s -> s.chars().mapToObj(i -> String.valueOf((char)i)).toList())
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
