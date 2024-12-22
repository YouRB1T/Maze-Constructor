package org.yurov.utils;

import org.yurov.entities.graph.SimpleGraph;
import org.yurov.entities.maze.SimpleMaze;

public class GraphBuilder {

    public SimpleGraph buildFromArray(Integer[][] array) {
        return null;
    }

    public SimpleGraph buildFromString(String stringGraph) {
        return null;
    }

    public SimpleMaze buildRandomMaze(int width, int height, int minVal, int maxVal) {
        return new SimpleMaze(ArrayUtils.generateRandom2DArray(height, width, minVal, maxVal));
    }
}
