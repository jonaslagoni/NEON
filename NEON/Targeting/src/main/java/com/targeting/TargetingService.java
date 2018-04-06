/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.targeting;

import com.library.GameData;
import com.library.Sprite;
import com.library.World;
import com.library.interfaces.ITargetingService;
import com.library.vectors.Vector2f;
import com.library.vectors.VectorUtils;

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
