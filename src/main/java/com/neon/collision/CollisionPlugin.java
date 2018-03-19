/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.collision;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.libary.interfaces.Plugin;

/**
 * @author Daniel
 */
public class CollisionPlugin implements Plugin {

    private final GameData gameData;
    private final World world;

    public CollisionPlugin(World world, GameData gameData) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(ICollisionService.class, new CollisionService(world));
    }

    @Override
    public void stop() {
    }
}
