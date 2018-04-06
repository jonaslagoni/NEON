package com.pathfinding;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.Plugin;

public class PathfindingPlugin implements Plugin {

    private final GameData gameData;
    private final World world;

    public PathfindingPlugin(GameData gameData, World world) {
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
