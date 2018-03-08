package com.neon.weapon;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.IWeaponService;
import com.neon.libary.interfaces.Plugin;

public class WeaponPlugin implements Plugin {

    private GameData gameData;
    private World world;

    public WeaponPlugin(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(IWeaponService.class, new WeaponService(world));
    }

    @Override
    public void stop() {

    }
}
