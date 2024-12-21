package org.yurov.utils;

import org.yurov.entities.EdgeForPoint;
import org.yurov.entities.Point;
import org.yurov.entities.graph.Edge;
import org.yurov.entities.graph.SimpleGraph;
import org.yurov.entities.graph.Vertex;
import org.yurov.entities.tree.SpanningTree;

import java.util.*;

public class GraphUtils {

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

    public List<Point[]> algorithmPrima(Integer[][] array) {
        return algorithmPrimaOnArray(array);
    }

    private List<Point[]> algorithmPrimaOnArray(Integer[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        // Поиск стартовой
        Point start = null;
        int minWeight = Integer.MAX_VALUE;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (array[i][j] < minWeight) {
                    minWeight = array[i][j];
                    start = new Point(i, j);
                }
            }
        }

        if (start == null) {
            throw new IllegalStateException("Стартовая точка не найдена.");
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        PriorityQueue<EdgeForPoint> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(EdgeForPoint::getWeight)
        );

        boolean[][] visited = new boolean[rows][cols];

        List<Point[]> mstEdges = new ArrayList<>();

        visited[start.getY()][start.getY()] = true;
        addEdges(start, array, visited, priorityQueue, directions);

        // Построение MST
        while (!priorityQueue.isEmpty()) {
            EdgeForPoint edge = priorityQueue.poll();

            Point to = edge.getDestination();

            if (visited[to.getX()][to.getY()]) {
                continue;
            }

            // Добавляем ребро в MST
            mstEdges.add(new Point[]{edge.getSource(), to});
            visited[to.getX()][to.getY()] = true;

            // Добавляем новые рёбра из текущей вершины
            addEdges(to, array, visited, priorityQueue, directions);
        }

        return mstEdges;
    }

    private void addEdges(Point from, Integer[][] array, boolean[][] visited, PriorityQueue<EdgeForPoint> queue, int[][] directions) {
        int rows = array.length;
        int cols = array[0].length;

        for (int[] dir : directions) {
            int newX = from.getX() + dir[0];
            int newY = from.getY() + dir[1];

            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                queue.add(new EdgeForPoint(from, new Point(newX, newY), Math.abs(array[newX][newY] - array[from.getX()][from.getY()])));
            }
        }
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
