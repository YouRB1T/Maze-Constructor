package org.yurov.repositories;

import org.yurov.dto.MazeDTOClient;
import org.yurov.entities.maze.SimpleMaze;

public class MazeRepository {
    private SimpleMaze maze;
    private MazeDTOClient mazeDTOClient;

    public MazeRepository() {
        maze = new SimpleMaze();
        mazeDTOClient = new MazeDTOClient();
    }

    public MazeRepository(SimpleMaze maze, MazeDTOClient mazeDTOClient) {
        this.maze = maze;
        this.mazeDTOClient = mazeDTOClient;
    }

    public SimpleMaze getMaze() {
        return maze;
    }

    public void setMaze(SimpleMaze maze) {
        this.maze = maze;
    }

    public MazeDTOClient getMazeDTOClient() {
        return mazeDTOClient;
    }

    public void setMazeDTOClient(MazeDTOClient mazeDTOClient) {
        this.mazeDTOClient = mazeDTOClient;
    }
}
