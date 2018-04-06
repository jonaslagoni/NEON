/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.targeting;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.Plugin;

/**
 * @author Daniel
 */
public class TargetingPlugin implements Plugin {

    private World world;
    private GameData gameData;

    public TargetingPlugin(World world, GameData gameData) {
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
//        gameData.addService(ITargetingService.class, new TargetingService(world, gameData));
    }

    @Override
    public void stop() {

    }

}
