package com.nincszsak.chemman;

import com.nincszsak.chemman.components.MapLoader;
import com.nincszsak.chemman.components.ResultPrinter;
import com.nincszsak.chemman.domain.ChemMan;

import java.io.IOException;
import java.net.URISyntaxException;

public class PathFinderApp {

    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println("Program stated");

        char[][] map = MapLoader.readMap("level2.in");

        ChemMan chemMan = GameEngine.startGame(map);

        ResultPrinter.showPath(chemMan);

        System.out.println("Program ended");
    }
}
