package com.nincszsak.chemman.domain;

import com.nincszsak.chemman.components.PathScoreCalculator;

import java.util.Objects;

public class Coin {
    private final int x;
    private final int y;

    private final StringBuilder path;

    public Coin(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.path = new StringBuilder();
    }

    public Coordinates getCoordinates() {
        return new Coordinates(x, y);
    }

    public Integer getPathScore() {
        return PathScoreCalculator.calculateScore(path.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return x == coin.x && y == coin.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void appendStep(String nextStep) {
        path.append(nextStep);
    }

    public String getPath() {
        return path.toString();
    }

}
