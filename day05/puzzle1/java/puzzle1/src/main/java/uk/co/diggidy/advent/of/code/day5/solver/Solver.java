package uk.co.diggidy.advent.of.code.day5.solver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solver {

    public int solve(String fileName) {
        final List<List<String>> orderingsAndUpdates = getOrderingsAndUpdates(fileName);
        final Map<Integer, List<Integer>> orderings = createOrderings(orderingsAndUpdates);
        final List<List<Integer>> updates = createUpdates(orderingsAndUpdates);
        return proccessupdates(orderings, updates);


    }

    private int proccessupdates(final Map<Integer, List<Integer>> orderings, final List<List<Integer>> updates) {
        return updates.stream()
                .filter(x -> validUpdate(x, orderings))
                .mapToInt(x -> x.get(Math.floorDiv(x.size(), 2)))
                .sum();
    }

    private boolean validUpdate(List<Integer> integers, Map<Integer, List<Integer>> orderings) {
        for(int i =0; i<integers.size(); i++) {
            final List<Integer> ordering = orderings.getOrDefault(integers.get(i), Collections.EMPTY_LIST);
            if(integers.subList(i, integers.size()).stream().anyMatch(ordering::contains)) {
                return false;
            }
        }
        return true;
    }

    private List<List<Integer>> createUpdates(List<List<String>> orderingsAndUpdates) {
        return orderingsAndUpdates.getLast().stream()
                .map(x -> Arrays.stream(x.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .toList();
    }

    private Map<Integer, List<Integer>> createOrderings(final List<List<String>> orderingsAndUpdates) {
        return orderingsAndUpdates.getFirst().stream()
                .collect(Collectors.groupingBy(
                        x -> Integer.parseInt(x.split("\\|")[1]),
                        Collectors.mapping(x -> Integer.parseInt(x.split("\\|")[0]),
                                Collectors.toList())));
    }

    private List<List<String>> getOrderingsAndUpdates(final String fileName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            boolean isUpdates = false;
            String line;
            final List<String> ordering = new ArrayList<>();
            final List<String> updates = new ArrayList<>();
            while((line = reader.readLine()) != null){
                if(line.equals("")){
                    isUpdates = true;
                }else {
                    if (isUpdates) {
                        updates.add(line);
                    } else {
                        ordering.add(line);
                    }
                }
            }
            return List.of(ordering, updates);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
