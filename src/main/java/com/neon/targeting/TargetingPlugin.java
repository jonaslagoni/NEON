/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.targeting;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.ITargetingService;
import com.neon.libary.interfaces.Plugin;

/**
 *
 * @author Daniel
 */
public class TargetingPlugin implements Plugin {
    
    World world;
    GameData gameData;
    
    public TargetingPlugin(World world, GameData gameData){
        this.gameData = gameData;
        this.world = world;
    }

    @Override
    public void start() {
        gameData.addService(ITargetingService.class, new TargetCalculation(world, gameData));
    }

    @Override
    public void stop() {
        
    }
    
}
