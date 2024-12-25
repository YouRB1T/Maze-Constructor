package org.yurov.services;

import org.yurov.entities.maze.SimpleMaze;

public interface MazeService {

    public void create(Integer[][] maze);

    public void createRandom(Integer[] array);

    public Integer[][] read(int i);

    public void update(int[] point, int index);

    public void delete(int index);

}
