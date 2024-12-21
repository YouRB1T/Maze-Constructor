package org.yurov.entities.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SimpleGraph {
    private List<Edge> edges;
    private List<Vertex> vertices;

    public SimpleGraph() {
        this.edges = new ArrayList<>(); // Инициализация списка рёбер
        this.vertices = new ArrayList<>(); // Инициализация списка вершин
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }
}
