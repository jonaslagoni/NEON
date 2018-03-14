/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.neon.libary.GameData;
import com.neon.libary.PathFinder;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.libary.interfaces.IWaveService;
import com.neon.libary.vectors.Vector2i;
import com.neon.player.Player;
import com.neon.projectile.Projectile;
import com.neon.wave.Wave;

import java.util.List;

import static com.badlogic.gdx.math.MathUtils.PI;
import static com.neon.libary.vectors.VectorUtils.angle;
import static com.neon.libary.vectors.VectorUtils.distance;


public class EnemyController implements Controller {

    private final World world;
    private final ICollisionService collisionService;

    private List<Entity> enemyList;
    private float enemyCooldown;
    private float waveCooldown;
    private IWaveService iWaveService;
    private int enemyListPos;
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


        Vector2i start = World.gridProject(enemy.getSprite().getPosition()); // Find current grid position
        Vector2i end = new Vector2i(8, 0); // Find goal grid position

        if (start.equals(end)) {
            enemyDeathCount--;
            world.removeEntity(enemy);
            return;
        }

        if (enemy.path == null) {
            enemy.path = PathFinder.findPath(start, end, world);
        }


        // float difx = Math.abs(enemy.moveAbility.getTargetVector().x - Math.abs(enemy.getSprite().getPosition().x));
        // float dify = Math.abs(enemy.moveAbility.getTargetVector().y - Math.abs(enemy.getSprite().getPosition().y));
        //check if we should find a new position


        if (enemy.path.peek() != null && distance(enemy.sprite.getPosition(), enemy.path.peek()) < 2) {
            enemy.path.remove();
        }

        // Set target vector
        if (enemy.path.peek() != null) {
            enemy.moveAbility.setTargetVector(enemy.path.element());
        }
        enemy.moveAbility.setMove(true);
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
