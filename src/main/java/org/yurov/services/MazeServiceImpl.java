package org.yurov.services;

import org.springframework.stereotype.Service;
import org.yurov.dto.MazeDTOClient;
import org.yurov.utils.UtilsDTOClient;
import org.yurov.entities.maze.SimpleMaze;
import org.yurov.repositories.MazeRepository;
import org.yurov.utils.ArrayUtils;
import org.yurov.utils.GraphUtils;

@Service
public class MazeServiceImpl implements MazeService{

    private MazeRepository mazeRepository;

    @Override
    public void create(Integer[][] maze) {
        mazeRepository.addMazeToStorage(maze);
    }

    /**
     * @param array (height, width, minValue, maxValue)
     */
    @Override
    public void createRandom(Integer[] array) {
        if (array.length != 4) {
            throw new RuntimeException("To generate random data array should have 4 numbers");
        }

        if (array[0] % 2 == 0 && array[1] % 2 == 0) {
            throw new RuntimeException("Height and width should be not even");
        }

        Integer[][] arrayGenerated = ArrayUtils.generateRandom2DArray(
                array[0],
                array[1],
                array[2],
                array[3]
        );

        mazeRepository.addMazeToStorage(arrayGenerated);

    }

    @Override
    public Integer[][] read(int index) {
        return mazeRepository.getMazeFromIndex(index).getMazeDTOClient().getMaze();
    }

    /**
     * @param point (x, y, weight)
     */
    @Override
    public void update(int[] point, int index) {
        Integer[][] maze = mazeRepository.getMazeFromIndex(index)
                .getMaze().getArrayMaze();
        maze[point[0]][point[1]] = point[2];

        mazeRepository.getMazeFromIndex(index).setMaze(new SimpleMaze(maze));
    }

    @Override
    public void delete(int index) {
        mazeRepository.deleteMaze(index);
    }
}
