package com.tower;

import com.libary.GameData;
import com.libary.TowerType;
import com.libary.World;
import com.libary.interfaces.INeonService;
import com.libary.interfaces.ITowerService;
import com.libary.interfaces.Plugin;

public class TowerPlugin implements Plugin {

    private final World world;
    private final GameData gameData;

    public TowerPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        TowerService towerService = new TowerService(world, gameData.getService(INeonService.class));
        gameData.addService(ITowerService.class, towerService);
        gameData.addController(new TowerController(world));

        for (TowerType t : TowerType.values())
            gameData.addPlaceable(t);
    }

    @Override
    public void stop() {
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }
}
