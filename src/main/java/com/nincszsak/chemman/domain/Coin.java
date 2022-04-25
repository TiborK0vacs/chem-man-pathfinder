package com.nincszsak.chemman.domain;

import java.util.Objects;

public class Coin {
    private final int x;
    private final int y;

    public Coin(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(x, y);
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
}
