package org.yurov.services;

import org.yurov.entities.Point;
import org.yurov.entities.maze.SimpleMaze;

public interface MazeService {

    public void create(SimpleMaze maze);

    public void createRandom();

    public SimpleMaze read();

    public void update(Point point);

    public void delete();

}
