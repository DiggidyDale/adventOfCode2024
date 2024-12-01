package uk.co.diggidy.advent.of.code.day1.solver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solver {

    public Long solve(String file){
        final List<List<Integer>> locationLists = getLocations(file);
        return getSimilarityScore(locationLists);
    }

    private Long getSimilarityScore(List<List<Integer>> locationLists) {
        final List<Integer> leftList = locationLists.get(0);
        final List<Integer> rightList = locationLists.get(1);

        Map<Integer, Integer> result = rightList.stream()
                .collect(Collectors.toMap(value -> value, x -> 1, (v1, v2) -> v1 += 1));

        return leftList.stream()
                .mapToLong(value -> (long) result.getOrDefault(value, 0) * value )
                .sum();

    }

    private List<List<Integer>> getLocations(String file) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            List<Integer> leftList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\s+");
                leftList.add(Integer.parseInt(split[0].trim()));
                rightList.add(Integer.parseInt(split[1].trim()));
            }
            return List.of(leftList, rightList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
