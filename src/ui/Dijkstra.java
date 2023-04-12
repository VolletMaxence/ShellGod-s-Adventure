package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra<T> {

    private final Graph<T> graph;

    public Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    public ArrayList<T> getPath(T source, T destination) {
        Map<T, T> previousVertices = new HashMap<>();
        Map<T, Double> distances = new HashMap<>();
        Set<T> visited = new HashSet<>();
        PriorityQueue<T> queue = new PriorityQueue<>((v1, v2) -> (int) (distances.getOrDefault(v1, Double.MAX_VALUE) - distances.getOrDefault(v2, Double.MAX_VALUE)));
        distances.put(source, 0.0);
        queue.offer(source);

        while (!queue.isEmpty()) {
            T currentNode = queue.poll();
            if (currentNode.equals(destination)) {
                break;
            }
            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);

            List<T> neighbors = graph.getAdjVertices(currentNode);
            for (T neighbor : neighbors) {
                double tentativeDistance = distances.getOrDefault(currentNode, Double.MAX_VALUE) + getDistance(currentNode, neighbor);
                if (tentativeDistance < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, tentativeDistance);
                    previousVertices.put(neighbor, currentNode);
                    queue.offer(neighbor);
                }
            }
        }

        return buildPath(previousVertices, destination);
    }

    private double getDistance(T currentNode, T neighbor) {
        // Ici on peut ajouter de la logique pour calculer la distance entre deux vertices
        // Pour l'instant, on retourne juste 1 si les vertices sont connectés et 0 sinon
        if (graph.getAdjVertices(currentNode).contains(neighbor)) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    private ArrayList<T> buildPath(Map<T, T> previousVertices, T destination) {
        ArrayList<T> path = new ArrayList<>();
        path.add(destination);
        T vertex = destination;
        while (previousVertices.containsKey(vertex)) {
            vertex = previousVertices.get(vertex);
            path.add(vertex);
        }
        return reverse(path);
    }

    private ArrayList<T> reverse(ArrayList<T> list) {
        ArrayList<T> reversed = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }
        return reversed;
    }

}
