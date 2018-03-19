package com.neon.enemy;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;

public class EnemyPlugin implements Plugin {

    private final World world;
    private final GameData gameData;

    public EnemyPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        gameData.addController(new EnemyController(world,
                gameData.getService(ICollisionService.class),
                gameData.getService(INeonService.class),
                gameData.getService(IWaveService.class),
                gameData.getService(IPathFindingService.class)));
        gameData.addService(IEnemyService.class, new EnemyController(world,
                gameData.getService(ICollisionService.class),
                gameData.getService(INeonService.class),
                gameData.getService(IWaveService.class),
                gameData.getService(IPathFindingService.class)));
    }

    @Override
    public void stop() {
        world.getEntities(Enemy.class).forEach(world::removeEntity);
    }
}
