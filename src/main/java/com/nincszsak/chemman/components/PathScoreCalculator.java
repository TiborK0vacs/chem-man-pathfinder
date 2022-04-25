package com.nincszsak.chemman.components;

public class PathScoreCalculator {
    public static int calculateScore(String chemManPath) {
        int result = 0;
        for (char currentChar : chemManPath.toCharArray()) {
            if (currentChar == 'U' || currentChar == 'D') {
                result += 8;
            } else {
                result += 1;
            }
        }
        return result;
    }
}
