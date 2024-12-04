package uk.co.diggidy.advent.of.code.day4.solver;

import uk.co.diggidy.advent.of.code.day4.model.Matrix;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Solver {

    public int solve(String fileName) {
        final Matrix wordSearch = new Matrix(getWordSearch(fileName));
        return findXmas(wordSearch);
    }

    private int findXmas(Matrix wordSearch) {
        int count =0;
        for (int i = 0; i < wordSearch.matrix().size(); i++) {
            final List<String> row = wordSearch.matrix().get(i);
            for (int j = 0; j < row.size(); j++) {
                String letter = row.get(j);
                if(letter.equalsIgnoreCase("x")){
                    count += checkNorth(i, j, count, wordSearch);
                    count += checkNorthEast(i,j, count, wordSearch);
                    count += checkEast(i, j, count, wordSearch);
                    count += checkSouthEast(i,j, count, wordSearch);
                    count += checkSouth(i, j, count, wordSearch);
                    count += checkSouthWest(i, j, count, wordSearch);
                    count += checkWest(i, j, count, wordSearch);
                    count += checkNorthWest(i, j, count, wordSearch);
                }
            }
        }
        return count;
    }

    private int checkNorthWest(final int i, final int j, int count, final Matrix wordSearch) {
        if(inNorthBoundary(i) && inWestBoundary(j)){
            if(String.join("",
                            wordSearch.get(i, j),
                            wordSearch.get(i-1, j-1),
                            wordSearch.get(i-2, j-2),
                            wordSearch.get(i-3, j-3))
                    .equalsIgnoreCase("xmas")){
                return 1;
            }
        }
        return 0;
    }

    private int checkSouthWest(final int i, final int j, int count, final Matrix wordSearch) {
        if(inSouthBoundary(i, wordSearch) && inWestBoundary(j)){
            if(String.join("",
                            wordSearch.get(i, j),
                            wordSearch.get(i+1, j-1),
                            wordSearch.get(i+2, j-2),
                            wordSearch.get(i+3, j-3))
                    .equalsIgnoreCase("xmas")){
                return 1;
            }
        }
        return 0;
    }

    private int checkSouthEast(final int i, final int j, int count, final Matrix wordSearch) {
        if(inSouthBoundary(i, wordSearch) && inEastBoundary(j, wordSearch)){
            if(String.join("",
                            wordSearch.get(i, j),
                            wordSearch.get(i+1, j+1),
                            wordSearch.get(i+2, j+2),
                            wordSearch.get(i+3, j+3))
                    .equalsIgnoreCase("xmas")){
                return 1;
            }
        }
        return 0;
    }

    private int checkNorthEast(final int i, final int j, int count, final Matrix wordSearch) {
        if(inNorthBoundary(i) && inEastBoundary(j, wordSearch)){
            if(String.join("",
                    wordSearch.get(i, j),
                    wordSearch.get(i-1, j+1),
                    wordSearch.get(i-2, j+2),
                    wordSearch.get(i-3, j+3))
                    .equalsIgnoreCase("xmas")){
               return 1;
            }
        }
        return 0;
    }

    private int checkSouth(final int i, final int j, int count, final Matrix wordSearch) {
        if(inSouthBoundary(i, wordSearch)){
            if(String.join("",
                    wordSearch.get(i,j),
                    wordSearch.get(i+1,j),
                    wordSearch.get(i+2,j),
                    wordSearch.get(i+3,j))
                    .equalsIgnoreCase("xmas")){
                return 1;
            }
        }
        return 0;
    }

    private int checkNorth(final int i, final int j, int count, final Matrix wordSearch) {
        if(inNorthBoundary(i)){
            if(String.join("",
                            wordSearch.get(i, j),
                            wordSearch.get(i-1, j),
                            wordSearch.get(i-2, j),
                            wordSearch.get(i-3, j))
                    .equalsIgnoreCase("xmas")){
                return 1;
            }
        }
        return 0;
    }

    private int checkEast(int i, int j, int count, Matrix wordSearch) {
        final List<String> row = wordSearch.matrix().get(i);
        if(inEastBoundary(j, wordSearch)){
          if(String.join("",row.subList(j, j+4)).equalsIgnoreCase("xmas")){
              return 1;
          }
        }
        return 0;
    }

    private int checkWest(int i, int j, int count, Matrix wordSearch) {
        final List<String> row = wordSearch.matrix().get(i);
        if(inWestBoundary(j)){
            if(String.join("",row.subList(j-3, j+1).reversed()).equalsIgnoreCase("xmas")){
               return 1;
            }
        }
        return 0;
    }

    private boolean inSouthBoundary(final int i, final Matrix wordSearch) {
        return i + 3 < wordSearch.matrix().size();
    }

    private boolean inNorthBoundary(final int i) {
        return i - 3 >= 0;
    }

    private boolean inEastBoundary(final int j, final Matrix wordSearch) {
        return j + 3 < wordSearch.matrix().getFirst().size();
    }

    private boolean inWestBoundary(final int j) {
        return j - 3 >= 0;
    }


    private List<List<String>> getWordSearch(final String fileName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines()
                    .map(line -> Arrays.stream(line.trim().split("")).toList())
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
