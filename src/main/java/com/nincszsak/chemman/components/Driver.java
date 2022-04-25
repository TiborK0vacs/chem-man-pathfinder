package com.nincszsak.chemman.components;

import com.nincszsak.chemman.domain.Coordinates;
import com.nincszsak.chemman.domain.Direction;

public class Driver {
    public static Coordinates findDirection(Coordinates targetCoordinates, Coordinates chemManCoordinates) {
//        System.out.println("targetCoordinates: (" + targetCoordinates.getX() + ", " + targetCoordinates.getY() + ")");
//        System.out.println("chemManCoordinates: (" + chemManCoordinates.getX() + ", " + chemManCoordinates.getY() + ")");
        Coordinates nextStep = null;
        if (targetCoordinates.getY() < chemManCoordinates.getY()) {
            nextStep = new Coordinates(chemManCoordinates.getX(), chemManCoordinates.getY() - 1, Direction.W);
        } else if (targetCoordinates.getY() > chemManCoordinates.getY()) {
            nextStep = new Coordinates(chemManCoordinates.getX(), chemManCoordinates.getY() + 1, Direction.E);
        } else if (targetCoordinates.getX() < chemManCoordinates.getX()) {
            nextStep = new Coordinates(chemManCoordinates.getX() - 1, chemManCoordinates.getY(), Direction.N);
        } else if (targetCoordinates.getX() > chemManCoordinates.getX()) {
            nextStep = new Coordinates(chemManCoordinates.getX() + 1, chemManCoordinates.getY(), Direction.S);
        } else {
            throw new RuntimeException("You are on the target.");
        }
        return nextStep;
    }
}
