/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.GameData;
import com.neon.libary.MoveAbility;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.player.Player;

import static com.neon.libary.interfaces.Entity.typeIdentifier.PLAYER;

/**
 * @author Daniel
 */
public class EnemyController implements Controller {

    private World world;
    private ICollisionService collisionService;

    EnemyController(World world, GameData gameData) {
        this.collisionService = gameData.getService(ICollisionService.class);
        this.world = world;
    }

    private void updateEnemy(Enemy enemy) {

        /* Move enemy toward player */
        for (Drawable entity : world.getEntities(Drawable.class)) {
            if (entity.getType() == PLAYER) {
                Vector2 playerPosition = entity.getSprite().getPosition();
                MoveAbility ability = enemy.getMoveAbility();
                ability.setTargetVector(playerPosition);
                ability.setTarget(true);
            }
        }

        /* Remove enemy if it collides with player */
        for (Entity entities : collisionService.getCollisions(enemy.getSprite())) {
            if (entities.getClass() == Player.class) {
                world.removeEntity(enemy);
            }
        }
    }

    @Override
    public void update() {
        world.getEntities(Enemy.class).forEach(this::updateEnemy);
    }
}
