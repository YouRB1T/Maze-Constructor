package org.yurov.entities.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Vertex<T> {
    private final int priority;
    private T data;
    private List<Edge> edges;

    public Vertex(int priority) {
        this.priority = priority;
        edges = new ArrayList<>();
    }

    public int getPriority() {
        return priority;
    }

    public void sedEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }
}