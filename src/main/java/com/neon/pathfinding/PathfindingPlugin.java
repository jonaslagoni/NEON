package com.neon.pathfinding;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.IPathFindingService;
import com.neon.libary.interfaces.Plugin;

public class PathfindingPlugin implements Plugin {
    private GameData gameData;
    private World world;

    public PathfindingPlugin(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(IPathFindingService.class, new PathFinder(world));
    }

    @Override
    public void stop() {

    }
}
