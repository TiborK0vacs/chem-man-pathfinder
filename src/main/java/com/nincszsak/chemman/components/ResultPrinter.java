package com.nincszsak.chemman.components;

import com.nincszsak.chemman.domain.ChemMan;

public class ResultPrinter {
    public static void showPath(ChemMan chemMan) {
        String chemManPath = chemMan.getPath();
        int score = PathScoreCalculator.calculateScore(chemManPath);
        System.out.println("---------------------------------------------");
        System.out.println(CoinLocator.getFoundCoins() + " coins were found.");
        System.out.println("ChemMan past this path : " + chemManPath);
        System.out.println("Score of the path: " + score);
    }
}
