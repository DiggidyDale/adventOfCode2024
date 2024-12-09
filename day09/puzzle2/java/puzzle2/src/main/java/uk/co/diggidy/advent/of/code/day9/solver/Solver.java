package uk.co.diggidy.advent.of.code.day9.solver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {

    public long solve(String fileName) {
        List<String> diskMap = getDiskMap(fileName);
        final List<String> cleanedDiskMap = cleanDiskMap(diskMap);
        return process(cleanedDiskMap);
    }

    private long process(final List<String> cleanedDiskMap) {
        String previousBlockType = "";
        for (int i = cleanedDiskMap.size() - 1; i >= 0; i--) {
            String blockType = cleanedDiskMap.get(i);
            if (!blockType.equals(previousBlockType)) {

                previousBlockType = blockType;
                if (!blockType.equalsIgnoreCase(".")) {
                    int blockInitialPos = i;
                    while (blockInitialPos >= 0) {
                        String blockToCheck = cleanedDiskMap.get(blockInitialPos);
                        if (blockToCheck.equals(blockType)) {
                            blockInitialPos--;
                        } else {
                            break;
                        }
                    }
                    int blockLength = (i - blockInitialPos);
                    for (int j = 0; j < i; j++) {
                        final String initialBlockToBeReplaced = cleanedDiskMap.get(j);
                        if (initialBlockToBeReplaced.equalsIgnoreCase(".")) {
                            final String blockAtEndLoc = cleanedDiskMap.get(j + blockLength-1);
                            if (blockAtEndLoc.equals(".")) {
                                for (int k = j; k < j + blockLength; k++) {
                                    cleanedDiskMap.set(k, blockType);
                                }
                                for (int k = i; k >= blockInitialPos-1; k--) {
                                    cleanedDiskMap.set(k, ".");
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        cleanedDiskMap.forEach(System.out::print);
        long result = 0;
        for (int i = 0; i < cleanedDiskMap.size(); i++) {
            if (!cleanedDiskMap.get(i).equalsIgnoreCase(".")) {
                result += (Long.parseLong(cleanedDiskMap.get(i)) * i);
            }

        }
        return result;
    }

    private List<String> cleanDiskMap(List<String> diskMap) {
        List<String> cleanedDiskMap = new ArrayList<>();
        int blockId = 0;
        for (int i = 0; i < diskMap.size(); i++) {
            int block = Integer.parseInt(diskMap.get(i));
            for (int j = 0; j < block; j++) {
                if (isEven(i)) {
                    cleanedDiskMap.add("" + blockId);
                } else {
                    cleanedDiskMap.add(".");
                }
            }
            if (isEven(i)) {
                blockId++;
            }

        }
        cleanedDiskMap.forEach(System.out::print);
        System.out.println("\n");
        return cleanedDiskMap;
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }


    private List<String> getDiskMap(final String fileName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                return reader.lines()
                        .flatMap(line -> Arrays.stream(line.trim().split("")))
                        .toList();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
