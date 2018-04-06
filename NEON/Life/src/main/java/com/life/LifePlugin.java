package com.life;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.Plugin;

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
    }

    @Override
    public void stop() {
    }
}
