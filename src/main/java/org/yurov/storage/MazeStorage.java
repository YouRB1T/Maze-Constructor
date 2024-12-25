package org.yurov.storage;

import org.yurov.dto.MazeDTOClient;
import org.yurov.entities.maze.SimpleMaze;
import org.yurov.utils.GraphUtils;
import org.yurov.utils.UtilsDTOClient;

public class MazeStorage {
    private SimpleMaze maze;
    private MazeDTOClient mazeDTOClient;
    private UtilsDTOClient utilsDTOClient = new UtilsDTOClient();
    private GraphUtils graphUtils = new GraphUtils();

    public MazeStorage(SimpleMaze maze) {
        this.maze = maze;
        mazeDTOClient = new MazeDTOClient(
                utilsDTOClient.transportMazeToClient(
                        maze.getWidth(),
                        maze.getHeight(),
                        graphUtils.resultInListPointsAlgorithmPrima(maze)
                )
        );
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

    public UtilsDTOClient getUtilsDTOClient() {
        return utilsDTOClient;
    }

    public void setUtilsDTOClient(UtilsDTOClient utilsDTOClient) {
        this.utilsDTOClient = utilsDTOClient;
    }

    public GraphUtils getGraphUtils() {
        return graphUtils;
    }

    public void setGraphUtils(GraphUtils graphUtils) {
        this.graphUtils = graphUtils;
    }
}
