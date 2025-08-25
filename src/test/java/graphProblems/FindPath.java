package graphProblems;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindPath {
    public static void main(String[] args) {
        List<List<String>> edges = List.of(
                List.of("i", "j"),
                List.of("k", "i"),
                List.of("m","k"),
                List.of("k", "l"),
                List.of("o", "n"));
        Map<String,List<String>> adjacencyList = ConvertingFromEdgeListToAdjacencyMap.convert(edges);
        Set<String> visited = new HashSet<>();
        System.out.println(hasPath(adjacencyList, "i", "l", visited));
    }

    public static boolean hasPath(Map<String, List<String>> graph, String start, String end, Set<String> visited) {
        if (start.equals(end)) {
            return true;
        }
        List<String> neighbors = graph.get(start);
        for (String neighbor : neighbors) {
            if(visited.contains(neighbor)) {
                continue; // Skip already visited nodes to avoid cycles
            } else {
                visited.add(neighbor); // Mark the neighbor as visited
            }
            if (hasPath(graph, neighbor, end, visited)) {
                return true;
            }
        }
        return false;
    }
}
