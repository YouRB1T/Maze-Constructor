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

    public  void transportMazeToClient(int width, int height, List<Point[]> answer) {
        simpleMazeToClientMaze(width, height, answer);
    }

    private void simpleMazeToClientMaze(int width, int height, List<Point[]> answer) {

        if (width == 0 || height == 0) {
            throw new RuntimeException("Нулевая размерность лабиринта=.");
        }

        if (answer == null) {
            throw new RuntimeException("Нет остовного дерева для транспортировки лабиринта ");
        }

        maze = new Integer[width * 2 - 1][height * 2 - 1];

        answer.forEach(points -> {
            transformPoint(points[0], maze, 0, 0);
            transformPoint(points[1], maze, 0, 0);

            int difX = points[0].getX() * 2 - points[1].getX() * 2;
            int difY = points[0].getY() * 2 - points[1].getY() * 2;

            transformPoint(points[0], maze, difX, difY);
        });
    }

    private void transformPoint(Point point, Integer[][] array, int difX, int difY) {
        if (difX == 0 && difY == 0) {
            array[point.getX() * 2][point.getY() * 2] = point.getWeight();
        } else if (difX == 0) {
            int el = difY > 0 ? -1 : 1;
            array[point.getX() * 2][point.getY() * 2 + el] = 1;
        } else if (difY == 0) {
            int el = difX > 0 ? -1 : 1;
            array[point.getX() * 2 + el][point.getY() * 2] = 1;
        }
    }
}
