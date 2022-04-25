package com.nincszsak.chemman.components;

import com.nincszsak.chemman.domain.Coin;
import com.nincszsak.chemman.domain.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class CoinLocator {
    private static int foundCoins = 0;

    public static List<Coin> findCoins(final char[][] map) {
        List<Coin> result = new ArrayList<>();
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                if (map[i][j] == 'c') {
                    System.out.println("Coin found on i= " + i + " , j= " + j);
                    result.add(new Coin(i, j));
                }
            }
        }
        return result;
    }

    public static boolean hasThisLocationCoin(Coin coin, Coordinates coordinates1) {
        boolean result = false;
        if (coordinates1.equals(coin.getCoordinates())) {
            result = true;
            foundCoins++;
        }
        return result;
    }

    public static int getFoundCoins() {
        return foundCoins;
    }
}
