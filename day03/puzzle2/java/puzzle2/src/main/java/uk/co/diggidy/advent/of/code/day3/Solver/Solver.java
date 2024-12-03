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
        String corruptedMemory = getCorruptedMemory(fileName);
        final List<List<Integer>> cleanedMemory = getCleanedMemory(corruptedMemory);
        return getResult(cleanedMemory);
    }

    private int getResult(final List<List<Integer>> cleanedMemory) {
        return cleanedMemory.stream()
                .mapToInt(x -> x.getFirst()* x.getLast())
                .sum();
    }

    private String getPartialCleanedMemory(String memory) {
        final String invalidOperations = "don't\\(\\)(.*?)do\\(\\)";
        final String stage1 = memory.replaceAll(invalidOperations, " ");
        final String removeFinalDont = "don't\\(\\)(.*)";
        return stage1.replaceAll(removeFinalDont, " ");
    }

    private List<List<Integer>> getCleanedMemory(final String corruptedMemory) {
        final String partialCleanedMemory = getPartialCleanedMemory(corruptedMemory);
        final String corruptionRegex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        final String integerRegex = "\\d{1,3}";
        final Pattern corruptionPattern = Pattern.compile(corruptionRegex);
        final Pattern integerPattern = Pattern.compile(integerRegex);
        return corruptionPattern.matcher(partialCleanedMemory).results()
                .map(MatchResult::group)
                .map(integerPattern::matcher)
                .map(m -> m.results().map(MatchResult::group).map(Integer::parseInt).collect(Collectors.toList()))
                .toList();
    }

    private String getCorruptedMemory(final String fileName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines()
                    .collect(Collectors.joining());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
