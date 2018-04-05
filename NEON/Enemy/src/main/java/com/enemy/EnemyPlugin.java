package com.enemy;

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
        EnemyController enemyController = new EnemyController(world,
                gameData.getService(INeonService.class),
                gameData.getService(IWaveService.class),
                gameData.getService(ILifeService.class),
                gameData.getService(IPathFindingService.class));
        gameData.addController(enemyController);
        gameData.addService(IEnemyService.class, enemyController);
    }

    @Override
    public void stop() {
        world.getEntities(Enemy.class).forEach(world::removeEntity);
    }
}
