package com.tower;

import com.library.GameData;
import com.library.TowerType;
import com.library.World;
import com.library.interfaces.Plugin;

public class TowerPlugin implements Plugin {

    private final World world;
    private final GameData gameData;

    public TowerPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        for (TowerType t : TowerType.values()) {
            gameData.addPlaceable(t);
        }
    }

    @Override
    public void stop() {
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }
}
