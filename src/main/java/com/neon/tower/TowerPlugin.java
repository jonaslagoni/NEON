package com.neon.tower;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.ITowerService;
import com.neon.libary.interfaces.Plugin;
import com.neon.libary.vectors.Vector2f;

public class TowerPlugin implements Plugin {

    private World world;
    private GameData gameData;

    public TowerPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        gameData.addService(ITowerService.class, new TowerService());
        gameData.addController(new TowerController(world, gameData));
        TowerFactory factory = new TowerFactory();

        gameData.addPlaceable("laser-tower", factory);
        gameData.addPlaceable("melee-glaive-tower", factory);
        gameData.addPlaceable("pea-shooter", factory);
        gameData.addPlaceable("range-powerup", factory);
        gameData.addPlaceable("strenght-powerup", factory);
        gameData.addPlaceable("railgun-tower", factory);
        gameData.addPlaceable("rocket-tower", factory);
        gameData.addPlaceable("splash-tower", factory);
        
       
        int[] ints = {800, 925, 1024, 1185};
        for (int i : ints) {
            Tower drawable = (Tower) factory.build("laser-tower");
            world.setGridCell(new Vector2f(i, 1024), drawable);
        }
        
    }

    @Override
    public void stop() {
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }
}
