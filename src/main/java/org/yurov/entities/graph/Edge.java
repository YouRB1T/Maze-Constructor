package org.yurov.entities.graph;

import lombok.*;

@Getter
@Setter
@ToString
public class Edge<T> {
    private final Vertex<T> vertexFirst;
    private final Vertex<T> vertexSecond;
    private int weight;

    public Edge(Vertex<T> vertexFirst, Vertex<T> vertexSecond, int weight) {
        this.vertexFirst = vertexFirst;
        this.vertexSecond = vertexSecond;
        this.weight = weight;
    }

    public Vertex<T> getVertexFirst() {
        return vertexFirst;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex<T> getVertexSecond() {
        return vertexSecond;
    }
}
