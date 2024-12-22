package org.yurov.dto;

import org.yurov.entities.Point;
import org.yurov.entities.maze.SimpleMaze;

import java.util.List;

public class MazeDTOClient {

    /**
     * 'n' - wolcable cell
     * '0' - wall
     */
    private final int[] CELLS = new int[] {0};

    private Integer[][] maze;

    public MazeDTOClient() {
    }

    public MazeDTOClient(Integer[][] maze) {
        this.maze = maze;
    }

    public Integer[][] getMaze() {
        return maze;
    }

    public void setMaze(Integer[][] maze) {
        this.maze = maze;
    }
}
