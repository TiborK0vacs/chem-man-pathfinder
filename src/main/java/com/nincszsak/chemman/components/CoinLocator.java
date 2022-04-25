package com.nincszsak.chemman.components;

import com.nincszsak.chemman.domain.Coin;

import java.util.ArrayList;
import java.util.List;

public class CoinLocator {
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
}
