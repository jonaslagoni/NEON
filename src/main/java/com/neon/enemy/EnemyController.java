/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.neon.libary.GameData;
import com.neon.libary.MoveAbility;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.player.Player;

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


        enemy.damageTimer += Gdx.graphics.getDeltaTime();

        /* Move enemy toward player */
        for (Drawable entity : world.getEntities(Drawable.class)) {
            if (entity.getClass() == Player.class) {
                Vector2 playerPosition = entity.getSprite().getPosition();
                MoveAbility ability = enemy.getMoveAbility();
                ability.setTargetVector(playerPosition);
                ability.setTarget(true);
            }
        }

        /* Remove enemy if it collides with player */
        for (Entity entity : collisionService.getCollisions(enemy.getSprite())) {
            if (entity.getClass() == Player.class) {
                if (enemy.damageTimer >= 0.1) {
                    /* Apply damage */
                    enemy.hp -= 10;
                    /* Set texture based on hp */
                    enemy.sprite.setTexture(enemy.texture[enemy.hp * enemy.texture.length / enemy.maxHp]);
                    /* Reset collision timer */
                    enemy.damageTimer = 0;
                }
                if (enemy.hp <= 0) {
                    world.removeEntity(enemy);
                }
                break;
            }
        }


    }

    @Override
    public void update() {
        world.getEntities(Enemy.class).forEach(this::updateEnemy);
    }
}
