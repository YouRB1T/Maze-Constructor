package org.yurov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yurov.dto.MazeDTOClient;
import org.yurov.entities.Point;
import org.yurov.entities.graph.Edge;
import org.yurov.entities.graph.SimpleGraph;
import org.yurov.entities.graph.Vertex;
import org.yurov.entities.maze.SimpleMaze;
import org.yurov.utils.ArrayUtils;
import org.yurov.utils.GraphUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MainApp {

    /**
    public static void main(String[] args) {
        SimpleGraph simpleGraph = new SimpleGraph();

        Vertex<Integer> A = new Vertex<Integer>(1);
        Vertex<Integer> B = new Vertex<Integer>(2);
        Vertex<Integer> C = new Vertex<Integer>(3);
        Vertex<Integer> D = new Vertex<Integer>(4);
        Vertex<Integer> E = new Vertex<Integer>(5);
        Vertex<Integer> F = new Vertex<Integer>(6);
        Vertex<Integer> G = new Vertex<Integer>(7);

        Edge<Integer> AB = new Edge<Integer>(A, B, 2);
        Edge<Integer> AC = new Edge<Integer>(A, C, 1);
        Edge<Integer> CF = new Edge<Integer>(C, F, 5);
        Edge<Integer> BE = new Edge<Integer>(B, E, 4);
        Edge<Integer> BD = new Edge<Integer>(B, D, 3);
        Edge<Integer> BC = new Edge<Integer>(B, C, 1);
        Edge<Integer> CB = new Edge<Integer>(C, B, 1);
        Edge<Integer> FE = new Edge<Integer>(F, E, 1);
        Edge<Integer> FG = new Edge<Integer>(F, G, 7);
        Edge<Integer> EG = new Edge<Integer>(E, G, 6);
        Edge<Integer> DG = new Edge<Integer>(D, G, 8);

        A.sedEdge(AB);
        A.sedEdge(AC);

        B.sedEdge(BD);
        B.sedEdge(BE);
        B.sedEdge(BC);

        C.sedEdge(CF);
        C.sedEdge(CB);

        D.sedEdge(DG);

        E.sedEdge(EG);

        F.sedEdge(FE);
        F.sedEdge(FG);

        List<Vertex> vertices = new ArrayList<>();
        vertices.add(A);
        vertices.add(B);
        vertices.add(C);
        vertices.add(D);
        vertices.add(E);
        vertices.add(F);
        vertices.add(G);
        List<Edge> edges = new ArrayList<>();
        edges.add(AB);
        edges.add(AC);
        edges.add(CF);
        edges.add(BE);
        edges.add(BD);
        edges.add(FE);
        edges.add(FG);
        edges.add(EG);
        edges.add(DG);
        simpleGraph.setEdges(edges);
        simpleGraph.setVertices(vertices);



        GraphUtils<Integer> graphUtils = new GraphUtils<>();

        List<Edge> ans = graphUtils.resultInGraphAlgorithmPrima(simpleGraph).getEdges();

        ans.forEach(
                e -> {
                    System.out.println(e.getVertexFirst() + " --(" + e.getWeight() + ")-> " + e.getVertexSecond());
                }
        );

    }
     */

/**
    public static void main(String[] args) {
        Integer[][] array = ArrayUtils.generateRandom2DArray(3, 3, 3, 10);

        System.out.println(ArrayUtils.D2DArrayToString(array));

        GraphUtils program = new GraphUtils();

        program.resultInListPointsAlgorithmPrima(new SimpleMaze(array)).forEach(
                points -> {
                    StringBuilder sb = new StringBuilder();

                    sb.append(points[0]).append(" -> ").append(points[1]);

                    System.out.println(sb.toString());
                }
        );


        List<Point[]> answer = program.resultInListPointsAlgorithmPrima(new SimpleMaze(array));
        MazeDTOClient mazeDTOClient = new MazeDTOClient();

        ArrayUtils.fillInIntegerArray(mazeDTOClient.getMaze());

        System.out.println(ArrayUtils.D2DArrayToString(mazeDTOClient.getMaze()));

    }
 */

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}