package org.yurov.utils;

import org.yurov.entities.Point;

import java.util.List;

public class UtilsDTOClient {

    public Integer[][] transportMazeToClient(int width, int height, List<Point[]> answer) {
       return simpleMazeToClientMaze(width, height, answer);
    }

    private Integer[][] simpleMazeToClientMaze(int width, int height, List<Point[]> answer) {

        if (width == 0 || height == 0) {
            throw new RuntimeException("Нулевая размерность лабиринта=.");
        }

        if (answer == null) {
            throw new RuntimeException("Нет остовного дерева для транспортировки лабиринта ");
        }

        Integer[][] finalMaze = new Integer[width * 2 - 1][height * 2 - 1];

        answer.forEach(points -> {
            transformPoint(points[0], finalMaze, 0, 0);
            transformPoint(points[1], finalMaze, 0, 0);

            int difX = points[0].getX() * 2 - points[1].getX() * 2;
            int difY = points[0].getY() * 2 - points[1].getY() * 2;

            transformPoint(points[0], finalMaze, difX, difY);
        });

        return finalMaze;
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
