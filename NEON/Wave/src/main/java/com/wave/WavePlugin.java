package com.wave;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.IWaveService;
import com.library.interfaces.Plugin;

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
//        gameData.addService(IWaveService.class, new WaveService());
    }

    @Override
    public void stop() {
    }
}
