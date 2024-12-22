package org.yurov.services;

import org.yurov.dto.MazeDTOClient;
import org.yurov.dto.UtilsDTOClient;
import org.yurov.entities.Point;
import org.yurov.entities.maze.SimpleMaze;
import org.yurov.repositories.MazeRepository;
import org.yurov.utils.GraphUtils;

public class MazeServiceImpl implements MazeService{

    private MazeRepository mazeRepository;

    @Override
    public void create(SimpleMaze maze) {

        mazeRepository.setMaze(maze);
        MazeDTOClient mazeDTOClient = new MazeDTOClient();
        UtilsDTOClient utilsDTOClient = new UtilsDTOClient();
        GraphUtils graphUtils = new GraphUtils();

        mazeDTOClient.setMaze(utilsDTOClient.transportMazeToClient(
                maze.getArrayMaze()[0].length,
                maze.getArrayMaze().length,
                graphUtils.resultInListPointsAlgorithmPrima(maze)
        ));

        mazeRepository.setMazeDTOClient(mazeDTOClient);

    }

    @Override
    public void createRandom() {

    }

    @Override
    public SimpleMaze read() {
        return null;
    }

    @Override
    public void update(Point point) {

    }

    @Override
    public void delete() {

    }
}
