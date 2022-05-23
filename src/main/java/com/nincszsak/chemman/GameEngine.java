package com.nincszsak.chemman;

import com.nincszsak.chemman.components.ChemManInitializer;
import com.nincszsak.chemman.components.CoinLocator;
import com.nincszsak.chemman.components.Driver;
import com.nincszsak.chemman.components.TargetLocator;
import com.nincszsak.chemman.domain.ChemMan;
import com.nincszsak.chemman.domain.Coin;
import com.nincszsak.chemman.domain.Coordinates;

import java.util.List;

public class GameEngine {
    public static ChemMan startGame(final char[][] map) {
        ChemMan chemMan = ChemManInitializer.putChemManOnMap(map);
        List<Coin> coinList = CoinLocator.findCoins(map);

        System.out.println("There are " + coinList.size() + " coins on this map.");

        Coin target = TargetLocator.findNextTarget(coinList, chemMan, map);
        System.out.println("The first Coin is at (" + target.getCoordinates().getX() + "," + target.getCoordinates().getY() + ")");
        while (!coinList.isEmpty()) {
            if (CoinLocator.hasThisLocationCoin(target, chemMan.getCoordinates())) {
                System.out.println("Coin was found at (" + target.getCoordinates().getX() + "," + target.getCoordinates().getY() + ")");
                coinList.remove(target);
                if (coinList.isEmpty()) {
                    break;
                } else {
                    target = TargetLocator.findNextTarget(coinList, chemMan, map);
                }
            }
            Coordinates nextMove = Driver.findDirection(target.getCoordinates(), chemMan.getCoordinates());
            chemMan.move(nextMove, map);
        }
        return chemMan;
    }

}
