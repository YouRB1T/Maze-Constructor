package org.yurov.entities;

public class EdgeForPoint {
    private Point source;
    private Point destination;
    private int weight;

    public EdgeForPoint(Point source, Point destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Point getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public Point getSource() {
        return source;
    }

    public void setSource(Point source) {
        this.source = source;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
