package org.yurov.utils;

import org.yurov.entities.EdgeForPoint;
import org.yurov.entities.Point;
import org.yurov.entities.graph.Edge;
import org.yurov.entities.graph.SimpleGraph;
import org.yurov.entities.graph.Vertex;
import org.yurov.entities.maze.SimpleMaze;
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

    public List<Point[]> resultInListPointsAlgorithmPrima(SimpleMaze maze) {
        return algorithmPrimaOnArray(maze.getArrayMaze());
    }

    private List<Point[]> algorithmPrimaOnArray(Integer[][] array) {

        if (array == null) {
            throw new IllegalStateException("Нужен массив для генерации остовного дерева");
        }

        int rows = array.length;
        int cols = array[0].length;

        Point start = findMinWeightPoint(array);

        if (start == null) {
            throw new IllegalStateException("Стартовая точка не найдена.");
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        PriorityQueue<EdgeForPoint> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(EdgeForPoint::getWeight)
        );

        boolean[][] visited = new boolean[rows][cols];

        List<Point[]> mstEdges = new ArrayList<>();

        visited[start.getY()][start.getX()] = true;
        addEdges(start, array, visited, priorityQueue, directions);

        while (!priorityQueue.isEmpty()) {
            EdgeForPoint edge = priorityQueue.poll();

            Point to = edge.getDestination();

            if (visited[to.getX()][to.getY()]) {
                continue;
            }

            mstEdges.add(new Point[]{edge.getSource(), to});
            visited[to.getX()][to.getY()] = true;

            addEdges(to, array, visited, priorityQueue, directions);
        }

        return mstEdges;
    }

    private void addEdges(Point from, Integer[][] array, boolean[][] visited, PriorityQueue<EdgeForPoint> queue,
                          int[][] directions) {
        int rows = array.length;
        int cols = array[0].length;

        for (int[] dir : directions) {
            int newX = from.getX() + dir[0];
            int newY = from.getY() + dir[1];

            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                Point to = new Point(newX, newY, array[newX][newY]);
                queue.add(new EdgeForPoint(from, to, EdgeForPointUtils.calculateWeight(array, from, to)));
            }
        }
    }

    private Point findMinWeightPoint(Integer[][] array) {
        Point start = new Point();

        int minWeight = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] < minWeight) {
                    minWeight = array[i][j];
                    start = new Point(i, j, minWeight);
                }
            }
        }

        return start;
    }

    /**
     * Alias to return SpanningTree
     * @param maze (SimpleMaze)
     * @return SpanningTree
     */
    public SpanningTree resultInTreeAlgorithmPrima(SimpleMaze maze) {
        return new SpanningTree(maze.getGraph());
    }

    /**
     * Open method
     * @param maze (SimpleMaze)
     * @return SimpleGraph
     */
    public SimpleGraph resultInGraphAlgorithmPrima(SimpleMaze maze) {
        return algorithmPrima(maze.getGraph());
    }
}
