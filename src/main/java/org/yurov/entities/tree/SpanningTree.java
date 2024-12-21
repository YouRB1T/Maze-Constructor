package org.yurov.entities.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yurov.entities.graph.Edge;
import org.yurov.entities.graph.SimpleGraph;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SpanningTree {
    private SimpleGraph tree;

    public SpanningTree(SimpleGraph tree) {
        this.tree = tree;
    }
}
