package com.neon.neon_coin;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.INeonService;
import com.neon.libary.interfaces.Plugin;

/**
 * Created by sam on 15-03-2018.
 */
public class NeonCoinPlugin implements Plugin {

    private final GameData gameData;
    private final World world;

    public NeonCoinPlugin(World world, GameData gameData) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(INeonService.class, new NeonWallet());
    }

    @Override
    public void stop() {
    }
}
