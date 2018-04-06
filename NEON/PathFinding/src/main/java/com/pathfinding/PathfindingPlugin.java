package com.pathfinding;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.IPathFindingService;
import com.library.interfaces.Plugin;

public class PathfindingPlugin implements Plugin {
    private GameData gameData;
    private World world;

    public PathfindingPlugin(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
//        gameData.addService(IPathFindingService.class, new PathFinder(world));
    }

    @Override
    public void stop() {

    }
}
