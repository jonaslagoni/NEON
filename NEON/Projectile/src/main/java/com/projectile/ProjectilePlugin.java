package com.projectile;

import com.library.GameData;
import com.library.World
import com.library.interfaces.IProjectileService;
import com.library.interfaces.Plugin;

public class ProjectilePlugin implements Plugin {

    private World world;
    private GameData gameData;

    public ProjectilePlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }
}
