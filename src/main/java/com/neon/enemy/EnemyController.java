/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.player.Player;
import com.neon.projectile.Projectile;

import static com.badlogic.gdx.math.MathUtils.PI;
import static com.neon.libary.VectorUtils.angle;

/**
 * @author Daniel
 */
public class EnemyController implements Controller {

    private final World world;
    private final ICollisionService collisionService;

    EnemyController(World world, GameData gameData) {
        this.collisionService = gameData.getService(ICollisionService.class);
        this.world = world;
    }

    private void updateEnemy(final Enemy enemy) {

        /* Don't Move if player is on target.
         * It is not necessary to calculate the actual distance, just the square of it. */
        // if ( distanceSquare(enemy.sprite.getPosition(), enemy.moveAbility.getTargetVector()) < 2)

        /* Calculate angle
         * https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors */

        enemy.damageTimer += Gdx.graphics.getDeltaTime();

        /* Move enemy toward player */
        for (Drawable entity : world.getEntities(Drawable.class)) {
            if (entity.getClass() == Player.class) {
                enemy.moveAbility.setTargetVector(entity.getSprite().getPosition());
            }
        }

        enemy.sprite.setRotation(angle(enemy.sprite.getPosition(), enemy.moveAbility.getTargetVector()) + PI);

        /* Remove enemy if it collides with player */
        for (Entity entity : collisionService.getCollisions(enemy.sprite)) {
            if (enemy.damageTimer >= 1 && entity.getClass() == Player.class) {
                enemy.hp -= 10;
                enemy.damageTimer = 0;
            } else if (entity.getClass() == Projectile.class) {
                enemy.hp -= 50;
                world.removeEntity(entity);
            }
        }

        if (enemy.hp <= 0) {
            world.removeEntity(enemy);
            return;
        }

        enemy.sprite.setTexture(enemy.textures[enemy.hp * (enemy.textures.length - 1) / enemy.maxHp]);
    }

    @Override
    public void update() {
        world.getEntities(Enemy.class).forEach(this::updateEnemy);
    }
}
