package com.weapon;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.ICollisionService;
import com.library.interfaces.IProjectileService;
import com.library.interfaces.ITargetingService;
import com.library.interfaces.Plugin;

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
