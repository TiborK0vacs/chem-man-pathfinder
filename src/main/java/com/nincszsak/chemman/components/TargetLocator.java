package com.nincszsak.chemman.components;

import com.nincszsak.chemman.domain.Coin;
import com.nincszsak.chemman.domain.Coordinates;

import java.util.List;

public class TargetLocator {

    public static Coin findNextTarget(List<Coin> coinList, Coordinates coordinates) {
        return coinList.get(0);
    }


}
