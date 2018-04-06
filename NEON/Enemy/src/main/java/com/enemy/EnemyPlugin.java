package com.enemy;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.Plugin;
import com.library.interfaces.WorldService;


public class EnemyPlugin implements Plugin {

    private WorldService world;
    private final GameData gameData;

    public EnemyPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }
    
    public void setWorld(World world) {
        this.world = world;
    }
    
    public void removeWorld(){
        this.world = null;
    }

    @Override
    public void start() {
  
    }

    @Override
    public void stop() {
        world.getEntities(Enemy.class).forEach(world::removeEntity);
    }
}
