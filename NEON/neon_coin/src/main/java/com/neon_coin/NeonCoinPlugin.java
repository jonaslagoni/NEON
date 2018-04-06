package com.neon_coin;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.INeonService;
import com.library.interfaces.Plugin;

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
//        gameData.addService(INeonService.class, new NeonWallet());
    }

    @Override
    public void stop() {
    }
}
