package com.weapon;

import com.library.GameData;
import com.library.World;
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
    }

    @Override
    public void stop() {

    }
}
