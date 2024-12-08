package uk.co.diggidy.advent.of.code.day8.solver;

import uk.co.diggidy.advent.of.code.day8.model.Node;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solver {

    public int solve(String fileName) {
        List<List<String>> grid = getGrid(fileName);
        Map<String, List<Node>> nodeMap = getNodeMap(grid);
        return process(nodeMap, grid.size(), grid.getFirst().size());
    }

    private int process(Map<String, List<Node>> nodeMap, int rowLimit, int colLimit) {
        Collection<List<Node>> entries = nodeMap.values();
        Set<String> antinodes = new HashSet<>();
        for (List<Node> entry : entries) {
            for (int i = entry.size() - 1; i >= 0; i--) {
                Node current = entry.get(i);
                for (int j = 0; j < entry.size() -1; j++) {
                    if(i != j){

                    Node next = entry.get(j);
                    current.getCoord().findAntinode(next.getCoord()).stream()
                            .filter(s -> !s.isBlank())
                            .forEach(antinodes::add);
                    }
                }
            }
        }
        return antinodes.size();
    }

    private Map<String, List<Node>> getNodeMap(List<List<String>> grid) {
        Map<String, List<Node>> nodeMap = new HashMap<>();
        final int limitY = grid.size();
        final int limitX = grid.get(0).size();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                String node = grid.get(i).get(j);
                if (!node.equalsIgnoreCase(".")) {
                    if (nodeMap.containsKey(node)) {
                        nodeMap.get(node).add(new Node(node, j, i, limitX, limitY));
                    } else {
                        List<Node> nodes = new ArrayList<>();
                        nodes.add(new Node(node, j, i, limitX, limitY));
                        nodeMap.put(node, nodes);
                    }
                }
            }
        }
        return nodeMap;
    }

    private List<List<String>> getGrid(String file) {
        try (InputStream inputStream = getClass().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines()
                    .map(line -> Arrays.stream(line.split("")).map(String::trim).toList())
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
