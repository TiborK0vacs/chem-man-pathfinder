package com.nincszsak.chemman.domain;

public class ChemMan {

    private int x;
    private int y;

    private Direction direction;

    private final StringBuilder activityLog;

    public ChemMan(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = Direction.N;
        this.activityLog = new StringBuilder();
    }

    public Coordinates getCoordinates() {
        return new Coordinates(x, y);
    }

    public Direction getDirection() {
        return direction;
    }

    public String getPath() {
        return activityLog.toString();
    }

    // Operations
    public void move(Coordinates coordinates) {
        if (this.direction.equals(coordinates.getDirection())) {
            activityLog.append("F");
        } else {
            turn(coordinates.getDirection());
            activityLog.append("F");
        }
        x = coordinates.getX();
        y = coordinates.getY();
    }

    private void turn(Direction newDirection) {
        activityLog.append(newDirection);
        direction = newDirection;
    }

}
