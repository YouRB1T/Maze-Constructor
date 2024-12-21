package org.yurov.utils;

import org.yurov.entities.Point;
import org.yurov.entities.graph.Edge;
import org.yurov.entities.graph.SimpleGraph;
import org.yurov.entities.graph.Vertex;
import org.yurov.entities.tree.SpanningTree;

import java.util.*;

public class GraphUtils<T> {

    /**
     * Simple realisation Prim's algorithm on the graph
     * @param graph (start vertex has minimum priority,
     *              end vertex has maximum priority)
     * @return spanning tree like SimpleGraph
     */
    private SimpleGraph algorithmPrima(SimpleGraph graph) {
        Optional<Vertex> startVertex = graph.getVertices().stream()
                .min(Comparator.comparing(Vertex::getPriority));

        Optional<Vertex> endVertex = graph.getVertices().stream()
                .max(Comparator.comparing(Vertex::getPriority));

        SimpleGraph answer = new SimpleGraph();

        int maxPriority = endVertex.get().getPriority();

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(
                (e1, e2) -> Integer.compare(e1.getWeight(), e2.getWeight())
        );

        if (startVertex.isEmpty() || endVertex.isEmpty()) {
            System.out.println("DON'T FIND START OR END VERTEX");
            return null;
        }

        startVertex.ifPresent(v -> {
            priorityQueue.addAll(v.getEdges());
        });

        Set<Edge> usedEdge = new HashSet<>();
        Set<Vertex> usedSecondVertex = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            Edge bestEdge = priorityQueue.remove();

            if (usedSecondVertex.contains(bestEdge.getVertexSecond())) continue;

            usedEdge.add(bestEdge);

            Vertex<Object> vertex = bestEdge.getVertexSecond();

            for (Edge edge : vertex.getEdges()) {

                if (edge.getVertexFirst() == bestEdge.getVertexSecond()
                        && edge.getVertexSecond() == bestEdge.getVertexFirst()) {
                    usedEdge.add(edge);
                }

                if (edge != bestEdge && !usedEdge.contains(edge) &&
                       !usedSecondVertex.contains(edge.getVertexSecond())) {
                    priorityQueue.add(edge);
                }
            }
            usedSecondVertex.add(bestEdge.getVertexSecond());
            answer.addEdge(bestEdge);
            if (bestEdge.getVertexSecond().getPriority() == maxPriority) break;
        }


        return answer;
    }

    public List<Point> algorithmPrima(Integer[][] array) {
        return algorithmPrimaOnArray(array);
    }

    private List<Point> algorithmPrimaOnArray(Integer[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        boolean[][] used = new boolean[rows][cols];
        List<Point> path = new ArrayList<>();
        PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> {
            double weight1 = calculateMinEdgeWeight(array, p1, used);
            double weight2 = calculateMinEdgeWeight(array, p2, used);
            return Double.compare(weight1, weight2);
        });

        Point start = findMinWeightCell(array);
        pq.add(start);

        Point end = findMaxWeightCell(array);

        while (!pq.isEmpty()) {
            Point current = pq.poll();

            if (used[current.getY()][current.getX()]) {
                continue;
            }

            used[current.getY()][current.getX()] = true;
            path.add(current);

            if (current.equals(end)) {
                break;
            }

            for (Point neighbor : getNeighbors(current, rows, cols)) {
                if (!used[neighbor.getY()][neighbor.getX()]) {
                    pq.add(neighbor);
                }
            }
        }

        return path;
    }

    private Point findMinWeightCell(Integer[][] array) {
        Point minPoint = new Point(0, 0);
        int minWeight = Integer.MAX_VALUE;

        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[0].length; x++) {
                if (array[y][x] < minWeight) {
                    minWeight = array[y][x];
                    minPoint.setX(x);
                    minPoint.setY(y);
                }
            }
        }

        return minPoint;
    }

    private Point findMaxWeightCell(Integer[][] array) {
        Point maxPoint = new Point(0, 0);
        int maxWeight = Integer.MIN_VALUE;

        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[0].length; x++) {
                if (array[y][x] > maxWeight) {
                    maxWeight = array[y][x];
                    maxPoint.setX(x);
                    maxPoint.setY(y);
                }
            }
        }

        return maxPoint;
    }

    private List<Point> getNeighbors(Point point, int rows, int cols) {
        List<Point> neighbors = new ArrayList<>();
        int x = point.getX();
        int y = point.getY();

        if (x > 0) neighbors.add(new Point(x - 1, y));
        if (x < cols - 1) neighbors.add(new Point(x + 1, y));
        if (y > 0) neighbors.add(new Point(x, y - 1));
        if (y < rows - 1) neighbors.add(new Point(x, y + 1));

        return neighbors;
    }

    private double calculateMinEdgeWeight(Integer[][] array, Point point, boolean[][] inMST) {
        double minWeight = Double.MAX_VALUE;

        for (Point neighbor : getNeighbors(point, array.length, array[0].length)) {
            if (!inMST[neighbor.getY()][neighbor.getX()]) {
                double weight = calculateEdgeWeight(array, point, neighbor);
                minWeight = Math.min(minWeight, weight);
            }
        }

        return minWeight;
    }

    private double calculateEdgeWeight(Integer[][] array, Point p1, Point p2) {
        int weight1 = array[p1.getY()][p1.getX()];
        int weight2 = array[p2.getY()][p2.getX()];
        return (weight1 + weight2) / 2.0;
    }

    /**
     * Alias to return SpanningTree
     * @param graph (SimpleGraph)
     * @return SpanningTree
     */
    public SpanningTree resultInTreeAlgorithmPrima(SimpleGraph graph) {
        return new SpanningTree(graph);
    }

    /**
     * Open method
     * @param graph (SimpleGraph)
     * @return SimpleGraph
     */
    public SimpleGraph resultInGraphAlgorithmPrima(SimpleGraph graph) {
        return algorithmPrima(graph);
    }
}
