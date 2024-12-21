package org.yurov.entities.maze;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yurov.entities.graph.SimpleGraph;

@Getter
@Setter
@NoArgsConstructor
public class SimpleMaze {
    private SimpleGraph graph;
    private Integer[][] arrayMaze;
}
