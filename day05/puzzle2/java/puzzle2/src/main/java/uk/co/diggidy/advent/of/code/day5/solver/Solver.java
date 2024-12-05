package uk.co.diggidy.advent.of.code.day5.solver;

import uk.co.diggidy.advent.of.code.day5.model.Update;

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
        final List<List<Update>> updates = createUpdates(orderingsAndUpdates.getLast(), orderings);
        return proccessupdates(updates);


    }

    private int proccessupdates(final List<List<Update>> updates) {
        return updates.stream()
                .filter(x -> !validUpdate(x))
                .map(x -> x.stream().sorted().collect(Collectors.toList()))
                .mapToInt(x -> x.get(Math.floorDiv(x.size(), 2)).value())
                .sum();
    }

    private boolean validUpdate(List<Update> updates) {
        for(int i =0; i<updates.size(); i++) {
            final List<Integer> ordering = updates.get(i).ordering();
            if(updates.subList(i, updates.size()).stream().mapToInt(Update::value).anyMatch(ordering::contains)) {
                return false;
            }
        }
        return true;
    }

    private List<List<Update>> createUpdates(List<String> updates, Map<Integer, List<Integer>> orderings) {
        return updates.stream()
                .map(x -> Arrays.stream(x.split(","))
                        .map(Integer::parseInt)
                        .map(y -> new Update(y, orderings.getOrDefault(y, Collections.EMPTY_LIST)))
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
