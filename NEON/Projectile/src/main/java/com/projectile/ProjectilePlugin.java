package com.projectile;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.IProjectileService;
import com.neon.libary.interfaces.Plugin;

public class ProjectilePlugin implements Plugin {

    private World world;
    private GameData gameData;

    public ProjectilePlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        gameData.addController(new ProjectileController(world, gameData));
        gameData.addService(IProjectileService.class, new ProjectileService(world));
    }

    @Override
    public void stop() {
    }
}
