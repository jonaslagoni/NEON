package com.neon.weapon;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.libary.interfaces.IProjectileService;
import com.neon.libary.interfaces.ITargetingService;
import com.neon.libary.interfaces.Plugin;

public class WeaponPlugin implements Plugin {

    private GameData gameData;
    private World world;

    public WeaponPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        gameData.addController(new WeaponController(world,
                gameData.getService(ICollisionService.class),
                gameData.getService(IProjectileService.class),
                gameData.getService(ITargetingService.class)));
    }

    @Override
    public void stop() {

    }
}
