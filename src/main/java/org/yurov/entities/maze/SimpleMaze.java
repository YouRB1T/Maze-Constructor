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

    public SimpleMaze() {
    }

    public SimpleMaze(Integer[][] arrayMaze) {
        this.arrayMaze = arrayMaze;
    }

    public Integer[][] getArrayMaze() {
        return arrayMaze;
    }

    public SimpleGraph getGraph() {
        return graph;
    }
}
