package org.yurov.utils;

import org.yurov.entities.Point;

public class EdgeForPointUtils {
    public static int calculateWeight(Integer[][] array, Point from, Point to) {
        return Math.abs(array[to.getX()][to.getY()] - array[from.getX()][from.getY()]);
    }
}
