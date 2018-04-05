/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.targeting;

import com.neon.libary.GameData;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.ITargetingService;
import com.neon.libary.vectors.Vector2f;
import com.neon.libary.vectors.VectorUtils;

/**
 * @author Daniel
 */
@SuppressWarnings("FieldCanBeLocal")
public class TargetingService implements ITargetingService {

    private final World world;
    private final GameData gameData;

    TargetingService(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;

    }

    @Override
    public Vector2f calculateTargetVector(Vector2f weaponPosition, Sprite enemy) {

        //gives you the direction
        float direction = VectorUtils.angle(enemy.getVelocity());


        //gives you the speed
        float speed = VectorUtils.magnitude(enemy.getVelocity());


        return weaponPosition;
    }


}
