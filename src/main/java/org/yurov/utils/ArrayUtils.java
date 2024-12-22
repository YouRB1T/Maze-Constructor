package org.yurov.utils;

import java.util.Arrays;
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

    public static String D2DArrayToString (Integer[][] array) {
        StringBuilder stringArray = new StringBuilder();

        for (Integer[] row : array) {
            stringArray.append(Arrays.toString(row));
            stringArray.append("\n");
        }

        return stringArray.toString();
    }

    public static void fillInIntegerArray(Integer[][] array) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                if (array[row][col] == null)
                    array[row][col] = 0;
            }
        }
    }

}
