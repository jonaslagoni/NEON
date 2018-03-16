package com.neon.enemy;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;

public class EnemyPlugin implements Plugin {

    private World world;
    private GameData gameData;

    public EnemyPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        gameData.addController(new EnemyController(
                world, gameData,
                gameData.getService(ICollisionService.class),
                gameData.getService(INeonWallet.class),
                gameData.getService(IWaveService.class),
                gameData.getService(IPathFindingService.class)
        ));
    }

    @Override
    public void stop() {
        for (Enemy enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }
}
