package com.nincszsak.chemman.components;

import com.nincszsak.chemman.domain.ChemMan;
import com.nincszsak.chemman.domain.Coin;
import com.nincszsak.chemman.domain.Coordinates;
import com.nincszsak.chemman.exceptions.ChemManArrivedException;

import java.util.Comparator;
import java.util.List;

public class TargetLocator {

    public static Coin findNextTarget(List<Coin> coinList, ChemMan chemMan, char[][] map) {
//        return coinList.get(0);
        calculatePathsToCoins(coinList, chemMan, map);
        printAllCoinDistance(coinList);
        return getClosestCoin(coinList);
    }

    private static void printAllCoinDistance(List<Coin> coinList) {
        for (int i = 0; i < coinList.size(); i++) {
            Coin currentCoin = coinList.get(i);
            System.out.println("The " + i + ". coin: (" + currentCoin.getCoordinates().getX() + ", " + currentCoin.getCoordinates().getY() + ") " +
                    "distance: " + currentCoin.getPathScore());
        }

    }

    private static void calculatePathsToCoins(List<Coin> coinList, ChemMan chemMan, char[][] map) {
        for (Coin coin : coinList) {
            ChemMan newChemMan = new ChemMan(chemMan.getCoordinates().getX(), chemMan.getCoordinates().getY(),
                    chemMan.getDirection());
            try {
                while (true) {
                    if (CoinLocator.hasThisLocationCoin2(coin, newChemMan.getCoordinates())) {
                        break;
                    }
                    Coordinates nextMove = Driver.findDirection(coin.getCoordinates(), newChemMan.getCoordinates());
                    newChemMan.move(nextMove, map);
                }
            } catch (ChemManArrivedException exception) {
                System.out.println("NewChemMan arrived.");
            }
            coin.appendStep(newChemMan.getPath());
        }
    }

    private static Coin getClosestCoin(List<Coin> coinList) {
        Coin closestCoin = coinList.stream().min(Comparator.comparing(Coin::getPathScore)).orElseThrow(RuntimeException::new);
        System.out.println("The Closest coin: (" + closestCoin.getCoordinates().getX() + ", " + closestCoin.getCoordinates().getY() + ") " +
                "distance: " + closestCoin.getPathScore());
        return closestCoin;
    }

}
