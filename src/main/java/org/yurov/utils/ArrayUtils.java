package org.yurov.utils;

import java.util.Random;

public class ArrayUtils {

    public static Integer[][] generateRandom2DArray(int rows, int cols, int minValue, int maxValue) {
        Random random = new Random();
        Integer[][] array = new Integer[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }

        return array;
    }

}
