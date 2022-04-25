package com.nincszsak.chemman;

import com.nincszsak.chemman.components.*;
import com.nincszsak.chemman.domain.ChemMan;
import com.nincszsak.chemman.domain.Coin;
import com.nincszsak.chemman.domain.Coordinates;

import java.util.List;

public class GameEngine {
    public static ChemMan startGame(final char[][] map) {
        ChemMan chemMan = ChemManInitializer.putChemManOnMap(map);
        List<Coin> coinList = CoinLocator.findCoins(map);

        System.out.println("There are " + coinList.size() + " coins on this map.");

        Coin target = TargetLocator.findNextTarget(coinList, chemMan.getCoordinates());
        while (!coinList.isEmpty()) {
            if (CoinLocator.hasThisLocationCoin(target, chemMan.getCoordinates())) {
                coinList.remove(target);
                if (coinList.isEmpty()) {
                    break;
                } else {
                    target = TargetLocator.findNextTarget(coinList, chemMan.getCoordinates());
                }
            }
            Coordinates nextMove = Driver.findDirection(coinList.get(0).getCoordinates(), chemMan.getCoordinates());
            chemMan.move(nextMove, map);
        }
        return chemMan;
    }

}
