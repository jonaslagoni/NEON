package com.wave;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.IWaveService;
import com.neon.libary.interfaces.Plugin;

/**
 * Created by sam on 15-03-2018.
 */
public class WavePlugin implements Plugin {

    private GameData gameData;
    private World world;

    public WavePlugin(World world, GameData gameData) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(IWaveService.class, new WaveService());
    }

    @Override
    public void stop() {
    }
}
