package com.nincszsak.chemman;

import com.nincszsak.chemman.components.MapLoader;
import com.nincszsak.chemman.domain.ChemMan;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineTest {

    @Test
    public void chemManShouldContainClimbUpAndClimbDownLetters() throws URISyntaxException, IOException {
        String expectedPath = "WFFFFFFFSFEUDFFFFUDFFFFSFWFFFUFFDSFEFUDFUFFFDFWFUFFFDFUDFFFFFFSFEFFFFFFFFFUDSFWFFFFFUDFSUDEFFFUFFSDEFFFFUDFSF";
        char[][] map = MapLoader.readMap("simple.in");
        ChemMan actualResult = GameEngine.startGame(map);
        assertEquals(expectedPath, actualResult.getPath());
    }

}