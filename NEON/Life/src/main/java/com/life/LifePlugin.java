package com.life;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.ILifeService;
import com.neon.libary.interfaces.Plugin;

public class LifePlugin implements Plugin {

    private final GameData gameData;
    @SuppressWarnings("FieldCanBeLocal")
    private final World world;

    public LifePlugin(World world, GameData gameData) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(ILifeService.class, new Life(20, gameData));
    }

    @Override
    public void stop() {
    }
}
