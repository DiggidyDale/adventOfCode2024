package uk.co.diggidy.advent.of.code.day3.Solver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solver {

    public int solve(String fileName) {
        List<String> corruptedMemory = getCorruptedMemory(fileName);
        List<List<Integer>> cleanedMemory = getCleanedMemory(corruptedMemory);
        return getResult(cleanedMemory);

    }

    private int getResult(final List<List<Integer>> cleanedMemory) {
        return cleanedMemory.stream()
                .mapToInt(x -> x.getFirst()* x.getLast())
                .sum();
    }

    private List<List<Integer>> getCleanedMemory(final List<String> corruptedMemory) {
        final String corruptionRegex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        final String integerRegex = "\\d{1,3}";
        final Pattern corruptionPattern = Pattern.compile(corruptionRegex);
        final Pattern integerPattern = Pattern.compile(integerRegex);
        return corruptedMemory.stream()
                .map(corruptionPattern::matcher)
                .flatMap(m -> m.results().map(MatchResult::group))
                .map(integerPattern::matcher)
                .map(m -> m.results().map(MatchResult::group).map(Integer::parseInt).collect(Collectors.toList()))
                .toList();
    }

    private List<String> getCorruptedMemory(final String fileName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines()
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
