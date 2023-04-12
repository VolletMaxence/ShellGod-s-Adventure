package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

    private Map<T, List<T>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T src, T dest) {
        adjVertices.putIfAbsent(src, new ArrayList<>());
        adjVertices.putIfAbsent(dest, new ArrayList<>());

        adjVertices.get(src).add(dest);
        adjVertices.get(dest).add(src);
    }

    public void removeVertex(T vertex) {
        adjVertices.values().forEach(e -> e.remove(vertex));
        adjVertices.remove(vertex);
    }

    public void removeEdge(T src, T dest) {
        List<T> srcList = adjVertices.get(src);
        List<T> destList = adjVertices.get(dest);
        if (srcList != null) {
            srcList.remove(dest);
        }
        if (destList != null) {
            destList.remove(src);
        }
    }

    public List<T> getAdjVertices(T vertex) {
        return adjVertices.get(vertex);
    }
}
