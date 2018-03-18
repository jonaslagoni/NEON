package com.neon.neon_coin;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.INeonWallet;
import com.neon.libary.interfaces.Plugin;

/**
 * Created by sam on 15-03-2018.
 */
public class NeonCoinPlugin implements Plugin {

    private GameData gameData;
    private World world;

    public NeonCoinPlugin(World world, GameData gameData) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(INeonWallet.class, new NeonWallet());
    }

    @Override
    public void stop() {
    }
}
