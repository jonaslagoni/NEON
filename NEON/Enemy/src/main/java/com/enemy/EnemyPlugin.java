package com.enemy;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.Plugin;


public class EnemyPlugin implements Plugin {

    private final World world;
    private final GameData gameData;

    public EnemyPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
  
    }

    @Override
    public void stop() {
        world.getEntities(Enemy.class).forEach(world::removeEntity);
    }
}
