package com.neon.life;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.ILifeService;
import com.neon.libary.interfaces.INeonService;
import com.neon.libary.interfaces.Plugin;
import com.neon.neon_coin.NeonWallet;

public class LifePlugin implements Plugin {

    private GameData gameData;
    private World world;

    public LifePlugin(World world, GameData gameData) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(ILifeService.class, new Life());
    }

    @Override
    public void stop() {
    }
}
