package com.nincszsak.chemman;

import com.nincszsak.chemman.components.ChemManInitializer;
import com.nincszsak.chemman.components.CoinLocator;
import com.nincszsak.chemman.components.Driver;
import com.nincszsak.chemman.components.MapLoader;
import com.nincszsak.chemman.domain.ChemMan;
import com.nincszsak.chemman.domain.Coin;
import com.nincszsak.chemman.domain.Coordinates;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class PathFinderApp {

    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println("Program stated");

        char[][] map = MapLoader.readMap("simple.in");

        startGame(map);


        System.out.println("Program ended");
    }

    public static ChemMan startGame(final char[][] map) {
        int foundCoins = 0;
        ChemMan chemMan = ChemManInitializer.putChemManOnMap(map);
        List<Coin> coinList = CoinLocator.findCoins(map);

        System.out.println("There are " + coinList.size() + " coins on this map.");

        Coin target = findNextTarget(coinList, chemMan.getCoordinates());
        while (!coinList.isEmpty()) {
            if (hasThisLocationCoin(target, chemMan.getCoordinates())) {
                foundCoins++;
                showPath(chemMan, foundCoins);
                coinList.remove(target);
                if (coinList.isEmpty()) {
                    break;
                } else {
                    target = findNextTarget(coinList, chemMan.getCoordinates());
                }
            }
            Coordinates nextMove = Driver.findDirection(coinList.get(0).getCoordinates(), chemMan.getCoordinates());
            chemMan.move(nextMove, map);
        }

        showPath(chemMan, foundCoins);
        return chemMan;
    }

    private static Coin findNextTarget(List<Coin> coinList, Coordinates coordinates) {
        return coinList.get(0);
    }

    private static void showPath(ChemMan chemMan, int foundCoins) {
        System.out.println("---------------------------------------------");
        System.out.println(foundCoins + " coins were found.");
        System.out.println("ChemMan past this path : " + chemMan.getPath());
    }

    private static boolean hasThisLocationCoin(Coin coin, Coordinates coordinates1) {
        boolean result = false;
        if (coordinates1.equals(coin.getCoordinates())) {
            result = true;
        }
        return result;
    }


}
