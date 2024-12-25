package org.yurov.repositories;

import org.yurov.dto.MazeDTOClient;
import org.yurov.storage.MazeStorage;
import org.yurov.utils.GraphUtils;
import org.yurov.utils.UtilsDTOClient;
import org.yurov.entities.maze.SimpleMaze;

import java.util.List;

public class MazeRepository {
    private List<MazeStorage> mazeStorages;

    public MazeRepository(List<MazeStorage> mazeStorages) {
        this.mazeStorages = mazeStorages;
    }

    public List<MazeStorage> getMazeStorages() {
        return mazeStorages;
    }

    public void setMazeStorages(List<MazeStorage> mazeStorages) {
        this.mazeStorages = mazeStorages;
    }

    public void addMazeToStorage(SimpleMaze maze) {
        mazeStorages.add(new MazeStorage(maze));
    }

    public MazeStorage getMazeFromIndex(int index) {
        return mazeStorages.get(index);
    }

    public void deleteMaze(int index) {
        mazeStorages.remove(index);
    }
}
