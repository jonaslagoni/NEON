package com.engine;

import com.library.TowerType;
import com.library.interfaces.IGameData;
import java.util.*;

public class GameData implements IGameData{

    /**
     * A multiplexer allows multiple input processors More information on
     * inputMultiplexer: https://github.com/libgdx/libgdx/wiki/Event-handling
     */
    private final List<TowerType> placeables = new ArrayList<>();

    @Override
    public void addPlaceable(TowerType title) {
        placeables.add(title);
    }

    @Override
    public List<TowerType> getPlaceables() {
        return placeables;
    }
}
