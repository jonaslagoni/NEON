package com.library;

import java.util.*;

public class GameData {

    /**
     * A multiplexer allows multiple input processors More information on
     * inputMultiplexer: https://github.com/libgdx/libgdx/wiki/Event-handling
     */
    private final List<TowerType> placeables = new ArrayList<>();

    public void addPlaceable(TowerType title) {
        placeables.add(title);
    }

    public List<TowerType> getPlaceables() {
        return placeables;
    }
}
