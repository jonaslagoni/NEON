/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.math.Vector2;
import com.neon.main.Controller;
import com.neon.main.World;
import com.neon.player.Player;

/**
 *
 * @author Daniel
 */
public class EnemyController implements Controller{

    @Override
    public void update(World world) {
        
        for(Enemy enemies : world.getEntities(Enemy.class)){
            Player player;
            player = world.getEntities(Player.class).first();
            
           Vector2 playerPosition = player.getSprite().getPosition();
           
           enemies.getMoveAbility().setTargetVector(playerPosition);
           enemies.getMoveAbility().setTarget(true);
            
        }
                
    }
    
    
    
}
