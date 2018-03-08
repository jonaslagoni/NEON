/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.math.Vector2;
import com.neon.collision.Collision;
import com.neon.libary.MoveAbility;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import static com.neon.libary.interfaces.Entity.typeIdentifier.PLAYER;
import java.util.ArrayList;

/**
 * @author Daniel
 */
public class EnemyController implements Controller {

    private World world;
    private Collision collision;

    EnemyController(World world, Collision collision) {
        this.collision = collision;
        this.world = world;
    }

    private void updateEnemy(Enemy enemy) {
        Iterable<Drawable> playerEntity = world.getEntities(Drawable.class);
        for(Drawable entity : playerEntity){
           if (entity.getType() == PLAYER){
               Vector2 playerPosition = entity.getSprite().getPosition();
                MoveAbility ability = enemy.getMoveAbility();
                ability.setTargetVector(playerPosition);
                ability.setTarget(true);
           }
           
        }  
        
        Iterable<Entity> collidingEntities = collision.getCollisions(enemy.getSprite());
            collidingEntities.forEach(System.out::println);
        for(Entity entities : collidingEntities){
            if (entities.getType() == PLAYER){
                System.out.println("helllo");
                world.removeEntity(enemy);
            }
        }
        
        
       
    }

    @Override
    public void update() {
        world.getEntities(Enemy.class).forEach(this::updateEnemy);
    }
}
