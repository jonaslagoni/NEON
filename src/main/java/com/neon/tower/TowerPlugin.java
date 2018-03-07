package com.neon.tower;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Plugin;

public class TowerPlugin implements Plugin {

    private World world;
    private GameData gameData;

    public TowerPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        gameData.addPlaceable("laser-tower", new TowerFactory());
    }

    @Override
    public void stop() {
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }
}
