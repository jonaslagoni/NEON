/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;
import com.neon.player.Player;
import com.neon.wave.Wave;
import com.neon.projectile.Projectile;

import static com.badlogic.gdx.math.MathUtils.PI;
import static com.neon.libary.VectorUtils.angle;

import java.util.List;


public class EnemyController implements Controller {

    private final World world;
    private final ICollisionService collisionService;
    private float enemyCooldown;
    private float waveCooldown;
    private IWaveService iWaveService;
    private int enemyListPos;
    List<Entity> enemyList;
    private int enemyDeathCount;

    EnemyController(World world, GameData gameData) {
        this.collisionService = gameData.getService(ICollisionService.class);
        this.world = world;
        this.iWaveService = new Wave(world, gameData);
        this.enemyList = iWaveService.createWave();
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
                enemy.hp -= 25;
                world.removeEntity(entity);
            }
        }

        if (enemy.hp <= 0) {
            enemyDeathCount--;
            world.removeEntity(enemy);
            return;
        }

        enemy.sprite.setTexture(enemy.textures[enemy.hp * (enemy.textures.length - 1) / enemy.maxHp]);
    }

    @Override
    public void update() {
        // Add enemies iterator here.


        enemyCooldown += Gdx.graphics.getDeltaTime();


        if (enemyCooldown > 1 && enemyListPos < enemyList.size()) {

            world.addEntity(enemyList.get(enemyListPos++));
            enemyCooldown = 0;
        }

        if (enemyDeathCount <= 0) {

            waveCooldown += Gdx.graphics.getDeltaTime();

            if (waveCooldown > 20) {

                enemyList = iWaveService.createWave();
                enemyDeathCount = enemyList.size();
                enemyListPos = 0;
                waveCooldown = 0;
            }
        }
        world.getEntities(Enemy.class).forEach(this::updateEnemy);
    }
}
