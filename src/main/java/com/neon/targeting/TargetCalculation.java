/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.targeting;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.GameData;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.ITargetingService;
import com.neon.libary.vectors.Vector2f;
import com.neon.libary.vectors.VectorUtils;

/**
 *
 * @author Daniel
 */
public class TargetCalculation implements ITargetingService{
    
    World world;
    GameData gameData;

    public TargetCalculation(World world, GameData gameData){
        this.world = world;
        this.gameData = gameData;
       
    }
    @Override
    public Vector2f calculateTargetVector(Vector2f weaponPosition, Sprite enemy) {
        
        Vector2f targetVector = weaponPosition;
        
        //gives you the direction
        float direction = VectorUtils.angle(enemy.getVelocity());
        
            
        //gives you the speed
        float speed = VectorUtils.magnitude(enemy.getVelocity());
        
             
        
        return targetVector;
    }

   
}
