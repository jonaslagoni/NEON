package com.neon.tower;

import com.neon.libary.GameData;
import com.neon.libary.TowerType;
import com.neon.libary.World;
import com.neon.libary.interfaces.INeonService;
import com.neon.libary.interfaces.ITowerService;
import com.neon.libary.interfaces.Plugin;

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

        for(TowerType t : TowerType.values())
        gameData.addPlaceable(t);

//        int[] ints = {800, 925, 1024, 1185};
//        for (int i : ints) towerService.placeTower(new Vector2f(i, 1024), "laser-tower");
    }

    @Override
    public void stop() {
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }
}
