package org.yurov.entities.maze;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yurov.entities.graph.SimpleGraph;

@Getter
@Setter
public class SimpleMaze {
    private SimpleGraph graph;
    private Integer[][] arrayMaze;
    private int height;
    private int width;

    public SimpleMaze() {
    }

    public SimpleMaze(Integer[][] arrayMaze) {
        this.arrayMaze = arrayMaze;
        height = arrayMaze[0].length;
        width = arrayMaze.length;
    }

    public Integer[][] getArrayMaze() {
        return arrayMaze;
    }

    public SimpleGraph getGraph() {
        return graph;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
