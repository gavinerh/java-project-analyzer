package graphProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertingFromEdgeListToAdjacencyMap {
    public static void main(String[] args) {
        List<List<String>> edges = List.of(
                List.of("i", "j"),
                List.of("k", "i"),
                List.of("m","k"),
                List.of("k", "l"),
                List.of("o", "n"));
        Map<String,List<String>> map = convert(edges);
        map.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    public static Map<String,List<String>> convert(List<List<String>> edges) {
        Map<String, List<String>> adjacencyMap = new HashMap<>();
        for(List<String> edge : edges) {
            for(String node : edge) {
                if (!adjacencyMap.containsKey(node)) {
                    adjacencyMap.put(node, new ArrayList<>());
                }
            }
            // connect the nodes
            connectNodes(edge, adjacencyMap);
        }
        return adjacencyMap;
    }

    private static void connectNodes(List<String> edge, Map<String, List<String>> adjacencyMap) {
        String node1 = edge.get(0);
        String node2 = edge.get(1);
        adjacencyMap.get(node1).add(node2);
        adjacencyMap.get(node2).add(node1);
    }
}
