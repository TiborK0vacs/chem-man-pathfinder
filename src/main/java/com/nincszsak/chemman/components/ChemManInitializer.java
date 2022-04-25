package com.nincszsak.chemman.components;

import com.nincszsak.chemman.domain.ChemMan;

public class ChemManInitializer {
    public static ChemMan putChemManOnMap(final char[][] map) {
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                if (map[i][j] == 's') {
                    System.out.println("ChemMan found on i= " + i + " , j= " + j);
                    return new ChemMan(i, j);
                }
            }
        }
        throw new RuntimeException("There was no ChemMan on the map.");
    }
}
