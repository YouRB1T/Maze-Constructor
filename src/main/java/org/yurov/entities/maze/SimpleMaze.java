package org.yurov.entities.maze;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yurov.entities.graph.SimpleGraph;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMaze {
    private SimpleGraph graph;
}
