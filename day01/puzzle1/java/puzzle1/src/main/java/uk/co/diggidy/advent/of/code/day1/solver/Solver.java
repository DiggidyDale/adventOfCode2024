package uk.co.diggidy.advent.of.code.day1.solver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    public Long solve(String file){
        final List<List<Integer>> locationLists = getLocations(file);
        final List<List<Integer>> sortedLocations = getSortedLocations(locationLists);
        return getTotalDistance(sortedLocations);
    }

    private Long getTotalDistance(List<List<Integer>> sortedLocations) {
        final List<Integer> leftList = sortedLocations.get(0);
        final List<Integer> rightList = sortedLocations.get(1);
        if(leftList.size() != rightList.size()){
            throw new IllegalArgumentException("Left and right lists do not match");
        }
        long totalDistance = 0L;
        for(int i = 0; i < leftList.size(); i++){
            int distance = Math.abs(leftList.get(i) - rightList.get(i));
            totalDistance += distance;
        }

        return totalDistance;
    }

    private List<List<Integer>> getSortedLocations(List<List<Integer>> locationLists) {
        return List.of(locationLists.get(0).stream().sorted().toList(), locationLists.get(1).stream().sorted().toList());
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
