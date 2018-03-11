package com.neon.projectile;

import com.neon.libary.GameData;
import com.neon.libary.World;
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
        gameData.addController(new ProjectileController(world));
    }

    @Override
    public void stop() {

    }
}
