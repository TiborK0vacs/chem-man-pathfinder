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
    public void move(Coordinates coordinates, final char[][] map) {
        if (!this.direction.equals(coordinates.getDirection())) {
            turn(coordinates.getDirection());
        }
        if (shouldIClimbUp(coordinates, map)) {
            activityLog.append("U");
        } else if (shouldIClimbDown(coordinates, map)) {
            activityLog.append("D");
        } else {
            activityLog.append("F");
        }
        x = coordinates.getX();
        y = coordinates.getY();
    }

    private boolean shouldIClimbDown(Coordinates coordinates, char[][] map) {
        return map[this.x][this.y] == '#' &&
                (map[coordinates.getX()][coordinates.getY()] == '.' || map[coordinates.getX()][coordinates.getY()] == 'c');
    }

    private boolean shouldIClimbUp(Coordinates coordinates, char[][] map) {
        return map[coordinates.getX()][coordinates.getY()] == '#' &&
                (map[this.x][this.y] == '.' || map[this.x][this.y] == 'c');
    }

    private void turn(Direction newDirection) {
        activityLog.append(newDirection);
        direction = newDirection;
    }
}
