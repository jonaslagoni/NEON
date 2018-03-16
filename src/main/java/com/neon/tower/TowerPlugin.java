package com.neon.tower;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Drawable;
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
        
        world.setGridCell(new Vector2f(800, 1024), (Drawable) factory.build("laser-tower"));
        world.setGridCell(new Vector2f(925, 1024), (Drawable) factory.build("laser-tower"));
        world.setGridCell(new Vector2f(1024, 1024), (Drawable) factory.build("laser-tower"));
        world.setGridCell(new Vector2f(1185, 1024), (Drawable) factory.build("laser-tower"));
    }

    @Override
    public void stop() {
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }
}
