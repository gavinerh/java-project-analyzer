package graphProblems;

import java.util.*;

public class FindConnectedCompoCount {
    public static void main(String[] args) {
        Map<String, List<String>> adjacencyMap = new HashMap<>();
        adjacencyMap.put("0", List.of("8","1","5"));
        adjacencyMap.put("1", List.of("0"));
        adjacencyMap.put("5", List.of("0", "8"));
        adjacencyMap.put("8", List.of("0", "5"));
        adjacencyMap.put("2", List.of("3", "4"));
        adjacencyMap.put("3", List.of("2", "4"));
        adjacencyMap.put("4", List.of("3", "2"));

        System.out.println(findConnectedCount(adjacencyMap));
    }

    private static int findConnectedCount(Map<String, List<String>> adjacencyMap) {
        Set<String> visited = new HashSet<>();
        int count = 0;
        for (String key : adjacencyMap.keySet()) {
            if(explore(adjacencyMap, key, visited)) {
                count++;
            }
        }
        return count;
    }


    private static boolean explore(Map<String, List<String>> adjacencyMap, String key, Set<String> visited) {
        if(visited.contains(key)) {
            return false; // If the node is already visited, return false
        }
        visited.add(key); // Mark the current node as visited
        List<String> neighbors = adjacencyMap.get(key) == null ? new ArrayList<>() : adjacencyMap.get(key);
        for (String neighbor : neighbors) {
            explore(adjacencyMap, neighbor, visited);
        }
        return true;
    }

}
