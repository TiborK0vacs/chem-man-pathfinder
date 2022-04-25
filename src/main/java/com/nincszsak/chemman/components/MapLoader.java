package com.nincszsak.chemman.components;

import com.nincszsak.chemman.PathFinderApp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MapLoader {
    public static char[][] readMap(String filename) throws URISyntaxException, IOException {

        List<String> lines = Files.readAllLines(Paths.get(PathFinderApp.class.getResource("/mazes/" + filename).toURI()));

        String[] vectors = lines.get(0).split(" ");
        int n = Integer.parseInt(vectors[1]);
        int m = Integer.parseInt(vectors[0]);
        System.out.println("n = " + n);
        System.out.println("m = " + m);
        char[][] map = new char[n][m];

        for (int i = 1; i <= n; i++) {
            char[] row = lines.get(i).toCharArray();
            map[i - 1] = row;
        }
        System.out.println("The Map:");
        for (int i = 0; i < map.length; i++) {
            if (i < 10) {
                System.out.print("0" + i + ". row: ");
            } else {
                System.out.print(i + ". row: ");
            }
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
        return map;
    }

}
